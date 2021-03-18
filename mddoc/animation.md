# 前言
本文主要通过以下几个方面来讲解RecyclerView的布局和动画原理：
1. 布局放置:RecyclerView#dispatchLayout()
2. 预布局阶段：RecyclerView#dispatchLayoutStep1()
3. 布局阶段：RecyclerView#dispatchLayoutStep2()
3. 开启动画阶段：RecyclerView#dispatchLayoutStep3()
---

# 背景知识
RecyclerView的Adapter有几个notify相关的方法：
- notifyDataSetChanged()
- notifyItemChanged(int)
- notifyItemInserted(int)
- notifyItemRemoved(int)
- notifyItemRangeChanged(int, int)
- notifyItemRangeInserted(int, int)
- notifyItemRangeRemoved(int, int)
- notifyItemMoved(int, int)

notifyDataSetChanged()与其他方法的区别：

1. 会导致整个列表刷新，其它几个方法则不会；
2. 不会触发RecyclerView的动画机制，其它几个方法则会触发各种不同类型的动画。

---

# 1. 布局放置

## 1.1 核心方法
RecyclerView#dispatchLayout()

## 1.2 作用

1. 将View放置到合适的位置
2. 记录布局阶段View的信息
3. 处理动画

RecyclerView的布局我们可以分成三个阶段，也可以精细分成五个阶段。
### 1.2.1 三个阶段
#### 1.2.1.1 预布局阶段
当需要做动画时，预布局阶段才会工作，否则没有实际意义，它对应dispatchLayoutStep1方法。动画有开始状态和结束状态，预布局完成后的RecyclerView是动画的开始状态。

#### 1.2.1.2 布局阶段
无论是否需要做动画，布局阶段都会工作，它对应dispatchLayoutStep2方法。布局完成后的状态是用户最终看到的状态，也是动画的结束状态。

#### 1.2.1.3 布局后阶段
布局完成后，需要执行动画操作，它对应的是dispatchLayoutStep3方法。当动画完成后，还会进行View回收操作。

### 1.2.2 五个阶段

#### 1.2.2.1 预布局前
在dispatchLayoutStep1方法调用onLayoutChildren方法之前。它会保存当前RecyclerView上所有子View的信息到ViewInfoStore中，FLAG增加FLAG_PRE。表示View在预布局前就显示在RecyclerView上。

#### 1.2.2.2 预布局中
在dispatchLayoutStep1方法调用onLayoutChildren方法时。它会根据算法，将RecyclerView上的子View重新排序，该阶段可能会添加新的子View。该阶段能够确定哪些View最终是不会展示给用户看的，FLAG增加FLAG_DISAPPEARED（例如：removed的View）。

#### 1.2.2.3 预布局后
在dispatchLayoutStep1方法调用onLayoutChildren方法之后，将预布局完成后的子View与预布局前的子View对比，将新增的View的FLAG增加FLAG_APPEAR（调用notifyItemRemoved后，新填充的View）。

#### 1.2.2.4 布局中
在dispatchLayoutStep2方法调用onLayoutChildren方法时。该阶段会把被挤出屏幕的View的FLAG增加FLAG_DISAPPEARED。

#### 1.2.2.5 布局后
在dispatchLayoutStep3方法中。会将最终的子View的FLAG增加FLAG_POST。

### 1.2.3 动画类型

#### 1.2.3.1 PERSISTENT
预布局前和布局后都存在的View所做的动画，位置有可能发生变化了，也有可能没有发生变化。

#### 1.2.3.2 REMOVED
在布局前对用户可见，布局后不可见，而且数据已经从数据源中删除掉了。

#### 1.2.3.3 ADDED
新增数据到数据源中，并且在布局后对用户可见。

#### 1.2.3.4 DISAPPEARING
数据一直都存在于数据源中，但是布局后从可见变成不可见状态（例如因为其它View插入操作，导致被挤出屏幕外了）。

#### 1.2.3.5 APPEARING
数据一直都存在于数据源中，但是布局后从不可见变成可见状态（例如因为其它View被删除，导致补位到屏幕内了）。

## 1.3 源码解析

### 1.3.1 RecyclerView#dispatchLayout()
1. dispatchLayoutStep1()执行预布局，记录ViewHolder位置信息；
2. dispatchLayoutStep2()执行布局，用户最终看到的效果；
3. dispatchLayoutStep3()执行动画操作。
![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/rv/animationpic1.png)

# 2. 预布局阶段

## 2.1 核心方法
1. RecyclerView#dispatchLayoutStep1()

2. RecyclerView#processAdapterUpdatesAndSetAnimationFlags()

3. LinearLayoutManager#onLayoutChildren()

4. LinearLayoutManager#updateAnchorInfoForLayout()

## 2.2 作用
1. 处理Adapter变化
2. 决定该执行哪种类型动画
3. 保存当前RecyclerView上的子View的信息
4. 如果需要执行动画，进行预布局

## 2.3 源码解析

### 2.3.1 RecyclerView#dispatchLayoutStep1()
1. 判断是否需要开启动画功能
2. 如果开启动画，将当前屏幕上的Item相关信息保存起来供后续动画使用
3. 如果开启动画，调用mLayout.onLayoutChildren方法预布局
4. 预布局后，与第二步保存的信息对比，将新出现的Item信息保存到Appeared中

![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/rv/animationpic2.png)

### 2.3.2 RecyclerView#processAdapterUpdatesAndSetAnimationFlags()
作用：判断是否需要开启动画
![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/rv/animationpic3.png)

### 2.3.3 LinearLayoutManager#onLayoutChildren()

以垂直方向的RecyclerView为例子，我们填充RecyclerView的方向有两种，从上往下填充和从下往上填充。开始填充的位置不是固定的，可以从RecyclerView的任意位置处开始填充。

1. 寻找填充的锚点(最终调用findReferenceChild方法）；
2. 移除屏幕上的Views(最终调用detachAndScrapAttachedViews方法）；
3. 从锚点处从上往下填充(调用fill和layoutChunk方法)；
4. 从锚点处从下往上填充(调用fill和layoutChunk方法)；
5. 如果还有多余的空间，继续填充(调用fill和layoutChunk方法)；
6. 布局完成后有可能产生GAP，需要修复GAP；
7. dispatchLayoutStep2阶段调用layoutForPredictiveAnimation将scrapList中多余的ViewHolder填充(调用fill和layoutChunk方法)。

![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/rv/animationpic4.png)

---
#### 2.3.3.1 寻找填充的锚点
1. 优先返回全部在屏幕内，未标记removed的View；
2. 次优先级返回不可见的View；
3. 最低优先级返回删掉的view。

![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/rv/animationpic5.png)

---

#### 2.3.3.2 移除屏幕上的Views

1. 调用notifyItemChanged(position)，position对应的ViewHolder会放入到mChangedScrap缓存中；
2. 否则会放入到mAttachedScrap缓存中
![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/rv/animationpic6.png)

----

#### 2.3.3.3 ~ 2.3.3.5 填充

调用LinearLayoutManager#fill()和LinearLayoutManager#layoutChunk()

1. 从缓存中获取View或者创建View
2. 如果是step1预布局阶段，调用addView()，将标记为removed的view放入到DISAPPEARED动画列表中
3. 如果是step2布局阶段，调用addDisappearingView()，将被挤出屏幕的view放入到DISAPPEARED动画列表中
4. 如果是removed的或者changed，不会记录消耗的填充量


![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/rv/animationpic7.png)

---

#### 2.3.3.6 修复GAP

通过mOrientationHelper.offsetChildren(gap)直接填补GAP

![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/rv/animationpic9.png)

---

#### 2.3.3.7 layoutForPredictiveAnimation

为了做动画，增加额外的Item

1. 不需要作动画，或者是预布局直接返回
2. 从mAttachedScrap中遍历到非removed的ViewHolder，但是返回的结果可能包含removed ViewHolder
3. 如果遍历找到了非Removed ViewHolder，填充View
![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/rv/animationpic8.png)


# 3. 布局阶段

## 3.1 核心方法
RecyclerView#dispatchLayoutStep2()

## 3.2 作用
1. 根据数据源中的数据进行布局，真正展示给用户看的最终界面
2. 如果开启动画，将被挤出屏幕的View的保存到消失动画列表中

## 3.3 源码解析

### 3.3.1 RecyclerView#dispatchLayoutStep2()
1. 将预布局模式改为false
2. 布局填充View
![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/rv/animationpic10.png)

### 3.3.2 LinearLayoutManager#layoutChunk()

布局阶段将被挤出屏幕的View放入到DISAPPEARED动画列表中
![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/rv/animationpic7.png)

### 3.3.3 LinearLayoutManager#addDisappearingView()

把Removed的View或被挤出屏幕的View添加到Disappearing动画列表
![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/rv/animationpic25.png)

### 3.3.4 ViewInfoStore#addToDisappearedInLayout()

加入到Disappeared动画列表
![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/rv/animationpic26.png)


# 4. 触发动画阶段

## 4.1 核心方法
RecyclerView#dispatchLayoutStep3()

## 4.2 作用
1. 清理工作
2. 保存布局后的view的信息
3. 触发动画

## 4.3 源码解析
### 4.3.1 RecyclerView#dispatchLayoutStep3()
![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/rv/animationpic11.png)


# 5. 场景篇

## 5.1 notifyItemRemoved场景

### 5.1.1 场景描述
1. 调用notifyItemRemoved相关的方法，模拟dispatchLayout过程
2. 假设Adapter数据有100条，屏幕上有Item1～Item6 6个View，删除Item1和Item2。
![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/dmall/recyclerviewpic1.png)

### 5.1.2 布局过程

1. 将Item1 Item2对应的ViewHolder设置为REMOVE状态
2. 将所有的Item对应的ViewHolder的mPreLayoutPosition字段赋值为当前的position


#### 5.1.2.1 dispatchLayoutStep1阶段

1. 寻找填充的锚点，寻找锚点的逻辑是，从上往下，找到第一个非remove状态的Item。在本Case中，找到Item3
![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/dmall/recyclerviewpic2.png)

2. 移除屏幕上的Views，将它们的ViewHolder放入到Recycler的mAttachedScrap缓存中，这个缓存的好处是如果position对应上了，无需重新绑定，直接拿来用。
![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/dmall/recyclerviewpic3.png)

3. 从锚点Item3处往下填充，mAttachedScrap只剩下ViewHolder2和ViewHolder1
![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/dmall/recyclerviewpic4.png)

4. 从锚点Item3处往上填充Item2 Item1，因为Item2，Imte1已经被remove掉了，它消耗的空间不会被记录，那么到步骤5的时候还可以填充
![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/dmall/recyclerviewpic7.png)

5. 还有多余的空间，继续填充，把Item7、Item8填充到屏幕中
![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/dmall/recyclerviewpic8.png)

6. 因为当前是预布局，直接返回

至此step1的layout结束

---
#### 5.1.2.2 dispatchLayoutStep2阶段

1. 寻找填充的锚点，寻找锚点的逻辑是，从上往下，找到第一个非remove状态的Item。在本Case中，找到Item3
![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/dmall/recyclerviewpic13.png)

2. 移除屏幕上的Views，将它们的ViewHolder放入到Recycler的mAttachedScrap缓存中
![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/dmall/recyclerviewpic14.png)

3. 从锚点Item3处往下填充，填充到Item6为止，就没有足够的距离了，mAttachedScrap只剩下ViewHolder8，ViewHolder7，ViewHolder2，ViewHolder1
![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/dmall/recyclerviewpic15.png)

4. 往上填充，虽然此时还有两个View的高度，但是此时，上边没有数据了，此处不填充
![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/dmall/recyclerviewpic15.png)

5. 此时还有两个View的高度，继续往下填充
![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/dmall/recyclerviewpic16.png)

**注意此时已经布局完成但是屏幕上部与第一个有GAP，会修复**
```
 if (getChildCount() > 0) {
            // because layout from end may be changed by scroll to position
            // we re-calculate it.
            // find which side we should check for gaps.
            if (mShouldReverseLayout ^ mStackFromEnd) {
                int fixOffset = fixLayoutEndGap(endOffset, recycler, state, true);
                startOffset += fixOffset;
                endOffset += fixOffset;
                fixOffset = fixLayoutStartGap(startOffset, recycler, state, false);
                startOffset += fixOffset;
                endOffset += fixOffset;
            } else {
                int fixOffset = fixLayoutStartGap(startOffset, recycler, state, true);
                startOffset += fixOffset;
                endOffset += fixOffset;
                fixOffset = fixLayoutEndGap(endOffset, recycler, state, false);
                startOffset += fixOffset;
                endOffset += fixOffset;
            }
        }
```
修复后效果如下
![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/dmall/recyclerviewpic17.png)

6. 当前不是预布局，但是因为ViewHolder1和ViewHolder2都是被Remove掉的，所以跳过
![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/dmall/recyclerviewpic17.png)


## 5.2 notifyItemInserted场景

### 5.2.1 场景描述

假设在Item1下面插入两条数据AddItem1，AddItem2

![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/dmall/recyclerviewpic1.png)
### 5.2.2 布局过程

#### 5.2.2.1 dispatchLayoutStep1阶段

1. 寻找锚点，找到Item1
![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/dmall/recyclerviewadd1.png)
2. 移除屏幕上的Views，放入到mAttachedScrap中
![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/dmall/recyclerviewadd2.png)
3. 锚点处从上往下填充
![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/dmall/recyclerviewadd3.png)
4. 锚点处从下往上填充,由上图可知，上面没有空间了，不填充
5. 判断是否还有剩余的空间，如果有在末尾填充，下面没空间了，不填充
6. 因为当前是预布局阶段，不填充

#### 5.2.2.2 dispatchLayoutStep2阶段
1. 寻找锚点，找到Item1
![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/dmall/recyclerviewadd1.png)
2. 移除屏幕上的Views，放入到mAttachedScrap中
![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/dmall/recyclerviewadd2.png)
3. 锚点处从上往下填充，此时将变化后的数据填充到屏幕上，addItem1和addItem2被填充到item1下面
![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/dmall/recyclerviewadd4.png)
4. 锚点处从下往上填充，由图可知，没有空间不填充
5. 判断是否还有剩余的空间，由图可知，没有空间不填充
6. 当前是layoutStep2阶段，会将mAttachScrap的内容，填充到屏幕末尾，ViewHolder5和ViewHolder6对应的ItemView被填充

![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/dmall/recyclerviewadd5.png)

#### 5.2.2.3 dispatchLayoutStep3阶段

开始动画，动画结束后，item5和item6会被回收掉，此时会被回收到mCachedViews缓存池中
![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/dmall/recyclerviewadd6.png)



# 6. 动画执行篇

## 6.1 场景总结

### 6.1.1 notifyItemRemoved场景

![删除场景](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/dmall/recyclerviewnew1.png)

### 6.1.1 notifyItemInserted场景


![增加场景](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/dmall/recyclerviewnew2.png)

我们定义四种布局状态
- 初始状态
- LayoutStep1
- LayoutStep2
- LayoutStep3


## 6.2 源码讲解
**1. RecyclerView的dispatchLayout**

通过源码我们了解到，在dispatchLayoutStep3中RecyclerView会执行动画，代码如下:

```
//From RecyclerView.java
private void dispatchLayoutStep3() {
  // Step 4: Process view info lists and trigger animations
  mViewInfoStore.process(mViewInfoProcessCallback);
}
```
**2. ViewInfoStore process方法**

跟进 process代码。源码位于ViewInfoStore.java文件中

![增加场景](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/dmall/recyclerviewnew4.png)

**3. ViewInfoStore$InfoRecord Flag**

Flags定义在ViewInfoStore$InfoRecord类中
```
static class InfoRecord {
      // disappearing list
      static final int FLAG_DISAPPEARED = 1;
      // appear in pre layout list
      static final int FLAG_APPEAR = 1 << 1;
      // pre layout, this is necessary to distinguish null item info
      static final int FLAG_PRE = 1 << 2;
      // post layout, this is necessary to distinguish null item info
      static final int FLAG_POST = 1 << 3;
      static final int FLAG_APPEAR_AND_DISAPPEAR = FLAG_APPEAR | FLAG_DISAPPEARED;
      static final int FLAG_PRE_AND_POST = FLAG_PRE | FLAG_POST;
      static final int FLAG_APPEAR_PRE_AND_POST = FLAG_APPEAR | FLAG_PRE | FLAG_POST;
}
```
- FLAG_DISAPPEARED：表示ViewHolder需要做消失动画
- FLAG_DISAPPEARED：表示ViewHolder需要做出现动画
- FLAG_PRE：表示该ViewHolder在初始状态显示在RV上
- FLAG_POST：表示该ViewHolder在LayoutStep3状态显示在RV上

这四种基本FLAG会衍生出以下几种：

- FLAG_APPEAR_AND_DISAPPEAR：表示先做Appear动画然后做DISAPPEAR动画，从源码的注释来看，这种动画毫无意义，忽略掉
- FLAG_PRE_AND_POST：表示ViewHolder在初始状态和LayoutStep3状态一直存在于RV上
- FLAG_APPEAR_PRE_AND_POST：**源码注释为Appeared in the layout but not in the adapter (e.g. entered the viewport)。我没太理解，也没有模拟出场景。有大神知道，请评论区告知。**

**4. 讲解process方法的功能**

1. 如果Flag含有FLAG_APPEAR_AND_DISAPPEAR，调用callback.unused(viewHolder)
2. 步骤1不成立，如果Flag为FLAG_DISAPPEARED，这里有两种情况

      2.1. 如果record.preInfo == null，初始状态时该ViewHolder不在RV上，消失动画无意义，调用callback.unused(viewHolder)

      2.2. 根据preInfo和postInfo执行消失动画

3. 上述不成立，如果Flag含有FLAG_APPEAR_PRE_AND_POST，调用callback.processAppeared(viewHolder, record.preInfo, record.postInfo)

4. 上述不成立，如果Flag含有FLAG_PRE_AND_POST，表示一直都在RV上，调用callback.processPersistent(viewHolder, record.preInfo, record.postInfo)

5. 上述不成立，如果Flag含有FLAG_PRE，表示初始有，step3没有，显然执行消失动画，调用callback.processDisappeared(viewHolder, record.preInfo, null)

6. 上述不成立，如果Flag含有FLAG_POST，表示初始没有，step3有，显然是新增加进来的，调用callback.processAppeared(viewHolder, record.preInfo, record.postInfo)

**5.ViewInfoStore$ProccessCallback**

![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/dmall/recyclerviewnew5.png)
哇呀，原来处理动画的方法只有四个，感觉so easy！

具体实现如下
![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/dmall/recyclerviewnew6.png)

讲解下processAppeard和unused两个方法
- unused不需要做任何动画，直接移除并且放入回收池(**此处引出了RecyclerView的回收策略，以后有空再写吧**)
- processAppeard方法相对processDisappeard方法复杂一些

**6. processAppeard方法**
```
void animateAppearance(ViewHolder itemHolder, ItemHolderInfo preLayoutInfo, ItemHolderInfo postLayoutInfo) {
    itemHolder.setIsRecyclable(false);
    if (mItemAnimator.animateAppearance(itemHolder, preLayoutInfo, postLayoutInfo)) {
        postAnimationRunner();
    }
}
```

此时兵分两路，ItemAnimator.animateAppearance和postAnimationRunner

**7. DefaultItemAnimator.animateAppearance**
![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/dmall/recyclerviewnew7.png)

原来Appeard动画此时一分为二

- 如果preLayoutInfo不为null，执行animateMove动画
- 反之执行animateAdd动画

**8. DefaultItemAnimator.animateMove**

![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/dmall/recyclerviewnew8.png)

**注意** 此步骤并没有真正执行动画，而是将MoveInfo保存到mPendingMoves，我们前面说到过兵分两路，mPendingMoves保存的动画数据，会在第二路，真正去执行

**9. DefaultItemAnimator.animateAdd**

```
@Override
public boolean animateAdd(final RecyclerView.ViewHolder holder) {
    resetAnimation(holder);
    holder.itemView.setAlpha(0);
    mPendingAdditions.add(holder);
    return true;
}
```
啊呀，原来当调用notifyItemInsert做的是一个淡入的动画

**注意** animateXXX返回boolean类型，如果返回false，动画将不会执行

**10. postAnimationRunner**

```
void postAnimationRunner() {
    if (!mPostedAnimatorRunner && mIsAttached) {
        ViewCompat.postOnAnimation(this, mItemAnimatorRunner);
        mPostedAnimatorRunner = true;
    }
}
```
```
 private Runnable mItemAnimatorRunner = new Runnable() {
      @Override
      public void run() {
          if (mItemAnimator != null) {
              mItemAnimator.runPendingAnimations();
          }
          mPostedAnimatorRunner = false;
      }
  };
```
最终调用到ItemAnimator.runPendingAnimations

**11. DefaultItemAnimator.runPendingAnimations**

![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/dmall/recyclerviewnew9.png)

代码有点长，逻辑很清晰简单，按照顺序执行动画

1. 首先执行Remove动画
2. 然后同时执行Move和Change动画
3. 最后执行Add动画

所以RV执行动画的总时长为removeDuration + Math.max(moveDuration, changeDuration) + addDuration。

至此，RecyclerView的动画原理已经讲解完毕，动画的执行原理，就是根据preLayout和postLayout，ViewHolder的位置来做动画的。但是我还是不明白，哪些ViewHolder执行哪种类型的动画。问题问到点子上了，既然这样，我们通过delte场景来讲解Item具体执行什么动画。

## 3.3 结合场景讲解动画类型

![删除场景](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/dmall/recyclerviewnew1.png)

该场景中一共有Item1~Item8 8个Item，最终显示给用户看的有Item3~Item8 6个Item，那么他们具体都执行了何种类型的动画呢？这里涉及到dispatchLayout和ViewInfoStore两个知识点

![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/dmall/recyclerviewnew10.jpg)

![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/dmall/recyclerviewnew13.png)

![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/dmall/recyclerviewnew12.png)

![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/dmall/recyclerviewnew14.png)

```
//该案例一定是在remove的场景下，从attachedScrap中拿ViewHolder去执行消失动画
private void addAnimatingView(ViewHolder viewHolder) {
    final View view = viewHolder.itemView;
    final boolean alreadyParented = view.getParent() == this;
    mRecycler.unscrapView(getChildViewHolder(view));
    if (viewHolder.isTmpDetached()) {
        // re-attach
        mChildHelper.attachViewToParent(view, -1, view.getLayoutParams(), true);
    } else if (!alreadyParented) {
        mChildHelper.addView(view, true);
    } else {
        mChildHelper.hide(view);
    }
}
```

结论很简单
Item1~Item2做消失动画、Item3~Item8做移动动画。但是这是比较简单的一种场景。


最后感谢美团盛书强同学提供的[代码生成图片工具](https://carbon.now.sh/)，让本文可以更顺利的写成。

如果大佬想鼓励我一下的话，分享，点赞，在看都随你了~


