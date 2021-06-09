# RecyclerView的测量机制
如下图，将RecyclerView的onMeasure方法分成三部分。
1. 如果mLayout == null，调用defaultOnMeasure(widthSpec, heightSpec)
2. mLayout.isAutoMeasureEnabled()返回true时
3. mLayout.isAutoMeasureEnabled()返回false时
![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/rv/measurepic1.png)

# RecyclerView.defaultOnMeasure()
defaultOnMeasure()会调用LayoutManager.chooseSize()方法。而LayoutManager.chooseSize()与View的resolveSizeAndState()方法高度相似。
说明defaultOnMeasure()沿用了View的测量机制：
1. 如果specMode为MeasureSpec.EXACTLY，测量值为父布局给的大小，MeasureSpec.getSize(measureSpec)。
2. 如果specMode为MeasureSpec.AT_MOST，测量值为RecyclerView实际的大小，但是不能超过父布局给的大小。
3. 如果specMode为MeasureSpec.UNSPECIFIED，测量值为RecyclerView实际的大小，可以超过父布局给的大小。

![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/rv/measurepic2.png)

![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/rv/measurepic3.png)

# LayoutManager.isAutoMeasureEnabled()
1. LinearLayoutManager.isAutoMeasureEnabled() 返回true
2. GridLayoutManager.isAutoMeasureEnabled() 返回true
2. StaggeredGridLayoutManager.isAutoMeasureEnabled() 有可能返回true，也有可能返回false

# 如果isAutoMeasureEnabled()返回true
当isAutoMeasureEnabled()返回true时，不建议重写LayoutManager的onMeasure()，它默认会调用RecyclerView的defaultOnMeasure()。
![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/rv/measurepic4.png)

测量逻辑如下：
1. 首先调用LayoutManager的onMeasure方法，如果 widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY 或者
mAdapter == null时。测量结束。这里很好理解，因为是MeasureSpec.EXACTLY，它会使用父布局给的宽高，作为RecyclerView的宽高。如果mAdapter == null
表示数据还没准备好，测量完毕。
2. 如果上述条件不成立，则会根据内容，对子View进行布局。根据RecyclerView的子View宽度，高度确定RecyclerView的宽高