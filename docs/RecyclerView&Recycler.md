# 前言

本文主要通过如下几个方面讲解RecyclerView缓存机制：

1. ViewHolder
2. 缓存架构
3. Recycler.recycleViewHolderInternal(ViewHolder holder)
4. Recycler.tryGetViewHolderForPositionByDeadline(int position, boolean dryRun, long deadlineNs)

# 1. ViewHolder

## 1.1 作用
ViewHolder是对RecyclerView上的ItemView的封装，它是RecyclerView缓存的载体。它封装了以下属性：
1. View itemView：对应RecyclerView的子View
2. int mPosition： View当前对应数据在数据源中的位置
3. int mOldPosition： View上次绑定的数据在数据源中的位置
4. long mItemId：可以判断ViewHolder是否需要重新绑定数据
5. int mItemViewType：itemView对应的类型
6. int mPreLayoutPosition： 在预布局阶段ViewHolder对应数据在数据源中的位置
7. int mFlags：ViewHolder对应的标记位
8. List\<Ojbect> mPayloads：实现局部刷新

9. Recycler mScrapContainer：如果不为空，表示ViewHolder是存放在Scrap缓存中

## 1.2 flag
1. FLAG_BOUND：ViewHolder对应的View已经绑定好了数据，无需重新绑定
2. FLAG_UPDATE：数据发生了变化，View需要重新绑定
3. FLAG_INVALID：数据失效了，View需要重新绑定
4. FLAG_REMOVED：数据从数据源中删除，View在消失动画中仍然有用
5. FLAG_NOT_RECYCLABLE：ViewHolder不能被回收，ViewHolder对应ItemView做动画时需要保证ViewHolder不能被回收掉
6. FLAG_RETURNED_FROM_SCRAP：从scrap缓存中获取到的ViewHolder
7. FLAG_IGNORE：如果回收该类型的ViewHolder会报错
8. FLAG_TMP_DETACHED：表示ItemView从RecyclerView上DETACHED了，detach和remove的区别是，remove会将View从ViewGroup的children数组中删除并且刷新ViewGroup，detach只会删除不会触发刷新

9. FLAG_ADAPTER_FULLUPDATE：表示ViewHolder需要全量更新，如果没有设置该标志位，则是局部更新
10. FLAG_MOVED：当ViewHolder的位置发生变化，做动画时需要使用

11. FLAG_APPEARED_IN_PRE_LAYOUT：ViewHolder出现在预布局中，需要做APPEARED动画

# 2. 缓存架构
## 2.1 四级缓存
1. ArrayList\<ViewHolder> mAttachedScrap & ArrayList<ViewHolder> mChangedScrap
2. ArrayList\<ViewHolder> mCachedViews
3. ViewCacheExtension mViewCacheExtension
4. RecycledViewPool mRecyclerPool

![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/dmall/recyclerview_cachepic1.png)

![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/rv/cachepic1.png)

## 2.2 scrap缓存
scrap缓存由mAttachedScrap和mChangedScrap两个缓存组成，在RecyclerView调用dispatchLayout时会使用该缓存，保存RecyclerView上的子View。

##### 两部分组成
1. mAttachedScrap
2. mChangedScrap

##### 缓存特性
1. 对应的数据结构是ArrayList\<ViewHolder>
2. 缓存大小没有限制，大小等于RecyclerView子View的个数
3. 该缓存中的ViewHolder无需重新绑定，只要ViewHolder的position和数据源中的position对应上了
4. 调用notifyItemRemoved、notifyItemMoved、notifyItemInserted方法，ViewHolder放入mAttachedScrap中
5. 调用notifyItemChanged(int position, Object payload)，如果payload!=null ViewHolder放入mAttachedScrap中,否则ViewHolder放入mChangedScrap中
6. 调用notifyDataSetChanged()时，如果Adapter.hasStableIds返回true，ViewHolder放入mAttachedScrap中，否则会将ViewHolder回收到非scrap缓存中
7. LinearLayoutManager.layoutForPredictiveAnimations()阶段，mAttachedScrap数组剩下的ViewHolder是被挤出屏幕的

## 2.3 mCachedViews缓存

##### 缓存特性
1. 对应的数据结构是ArrayList\<ViewHolder>
2. 缓存大小有限制，默认缓存大小为2，可以修改默认缓存大小。如果使用GridLayoutManager建议设置为列的个数
3. 该缓存中的ViewHolder无需重新绑定，只要ViewHolder的position和数据源中的position和itemType对应上了
4. 该缓存的特性是FIFO
5. ViewHolder mFlag如果有FLAG_INVALID、FLAG_REMOVED、FLAG_UPDATE、FLAG_ADAPTER_POSITION_UNKNOWN之一，不会放入该缓存
6. 当RecyclerView滑动时会将ViewHolder放入该缓存或者从该缓存获取ViewHolder

## 2.4 ViewCacheExtension
该接口只提供了get方法，没提供put方法。

## 2.5 RecyclerViewPool

##### 缓存特性
1. 对应的数据结构是SparseArray\<ScrapData>，根据itemType将缓存分组，组的数据结构是ScrapData
2. ScrapData对应的数据结构是ArrayList\<ViewHolder>，每个itemType对应的ScrapData的缓存大小默认值是5，可以修改缓存大小
3. 该缓存中的ViewHolder需要重新绑定数据
4. 可以提供给多个RecyclerView共享

# 3. Recycler.recycleViewHolderInternal(ViewHolder holder)

## 3.1 调用时机
1. 调用notifyItemRemoved()，ViewHolder动画结束后
2. 被挤出屏幕的ViewHolder在动画结束后
3. 滑出屏幕的ViewHolder需要回收掉
4. 调用notifyDataSetChanged()，而且Adapter.hasStableIds()返回false
5. 缓存中获取到的ViewHolder校验失败，需要回收
6. 调用setAdapter()时

## 3.2 回收逻辑

### 3.2.1 回收流程图
![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/rv/cachepic3.jpg)

### 3.2.2 源码分析
1. 如果当前mCachedViews容量>=缓存最大数量，将存放最久的ViewHolder回收到RecyclerViewPool中，直到mCachedViews size=mViewCacheMax-1
2. 将ViewHolder缓存到mCachedViews最后一个槽位上
3. 如果缓存到mCachedViews失败，则缓存到RecyclerViewPool中
#### 3.2.2.1 Recycler.recycleViewHolderInternal()

![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/rv/cachepic4.png)

#### 3.2.2.2 Recycler.recycleCachedViewAt(int cachedViewIndex)
![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/rv/cachepic5.png)

#### 3.2.2.3 RecyclerViewPool.putRecycledView(ViewHolder scrap)
![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/rv/cachepic6.png)

# 4. Recycler.tryGetViewHolderForPositionByDeadline(int position, boolean dryRun, long deadlineNs)

## 4.1 调用时机(以LinearLayoutManager为例)
dispatchLayoutStep1()、dispatchLayoutStep2()、滑动RecyclerView时会发生复用

1. LinearLayoutManager.onLayoutChildren() -> LinearLayoutManager.fill() -> LinearLayoutManager.layoutChunk() -> LinearLayoutManager.LayoutState.next()-> RecyclerView.Recycler.getViewForPosition()
2. LinearLayoutManager.layoutForPredictiveAnimations()-> LinearLayoutManager.fill() -> LinearLayoutManager.layoutChunk() -> LinearLayoutManager.LayoutState.next()-> RecyclerView.Recycler.getViewForPosition()
3. LinearLayoutManager.scrollBy()-> LinearLayoutManager.fill() -> LinearLayoutManager.layoutChunk() -> LinearLayoutManager.LayoutState.next()-> RecyclerView.Recycler.getViewForPosition()

## 4.2 获取ViewHolder流程
![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/rv/cachepic8.jpg)

### 4.2.1 Recycler.getChangedScrapViewForPosition(int position)
预布局过程中从mChangedScrap缓存中获取ViewHolder。获取逻辑如下：

1. 线性遍历 mChangedScrap，position == ViewHolder.mPreLayoutPosition，返回该ViewHolder，否则走逻辑2
2. Adapter.hasStableIds()返回false，返回null，否则走逻辑3
3. 线性遍历 mChangedScrap，mAdapter.getItemId(offsetPosition) == holder.getItemId，返回该ViewHolder，否则走逻辑4
4. 上述都没有获取到，返回null。

![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/rv/cachepic9.png)


### 4.2.2 Recycler.getScrapOrHiddenOrCachedHolderForPosition(int position)
##### 4.2.2.1 该方法从三个地方获取缓存，获取后还需要验证是否合法，如果验证失败会调用Recycler.recycleViewHolderInternal()
1. mAttachedScrap
2. hidden list中，ChildHelper通过位图算法，逻辑隐藏的ViewHolder
3. mCachedViews

##### 4.2.2.2 从mAttachedScrap中获取，必须满足以下所有条件：
1. holder.getLayoutPosition() == position，即预布局 viewHolder.mPreLayoutPosition == position，布局中 viewHolder.mPosition == position
2. !holder.isInvalid()
3. 非预布局情况下，不可返回removed的ViewHolder

##### 4.2.2.3 从hidden list中获取ViewHolder
1. 调用调用ChildHelper.findHiddenNonRemovedView(position)获取View
2. 调用ChildHelper.unhide(view)，不隐藏View
3. 调用mChildHelper.detachViewFromParent(layoutIndex)，轻量删除View，从RecyclerView children数组中删除，但是不会重新绘制RecyclerView
4. 放入scrap缓存中

##### 4.2.2.4 从mCachedViews中获取ViewHolder
1. !holder.isInvalid()
2. holder.getLayoutPosition() == position
3. 如果满足会将ViewHolder从mCachedViews中移除

![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/rv/cachepic10.png)


### 4.2.3 Recycler.getScrapOrCachedViewForId(long id, int type, boolean dryRun)
1. 首先从attachedScrap中查找，如果id和type都匹配上了，返回该ViewHolder，并且在非预布局情况下，将removed的设置成update
2. 如果id匹配上了 type没匹配上，将该ViewHolder回收掉
3. 其次从mCachedViews中查找，如果id和type都匹配上了，返回并移除掉该ViewHolder
4. 如果id匹配上了 type没匹配上，将该ViewHolder回收掉
![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/rv/cachepic12.png)

### 4.2.4 getRecycledViewPool().getRecycledView(int type)
根据viewType从SparseArray中获取相应的ViewHolder，该缓存中获取的ViewHolder需要重新绑定
![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/rv/cachepic13.png)


### 4.2.5 Adapter.createViewHolder(RecyclerView recyclerView, int type)

![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/rv/cachepic14.png)

### 4.2.6 Recycler.tryBindViewHolderByDeadline(RecyclerView recyclerView, int type)
1. 调用Adapter.bindViewHolder()方法
2. 将ViewHolder flag设置为FLAG_BOUND，清除FLAG_UPDATE、FLAG_INVALID、FLAG_ADAPTER_POSITION_UNKNOWN
3. 重写onBindViewHolder(VH holder, int position, List\<Object> payloads)，通过payloads可以实现局部刷新
4. 
![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/rv/cachepic15.png)


