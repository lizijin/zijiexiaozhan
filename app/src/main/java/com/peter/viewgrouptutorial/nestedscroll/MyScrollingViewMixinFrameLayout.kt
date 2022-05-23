package com.peter.viewgrouptutorial.nestedscroll

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.widget.FrameLayout
import androidx.core.view.*
import kotlin.math.abs

class MyScrollingViewMixinFrameLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), Log,NestedScrollingParent2,NestedScrollingChild2 {
    var name = ""
    private var mLastMotionY: Float = 0f
    private var mTouchSlop: Int = 0
    private var mNestedScrollingChildHelper = NestedScrollingChildHelper(this)
    private var mNestedScrollingParentHelper = NestedScrollingParentHelper(this)

    init {
        mTouchSlop = ViewConfiguration.get(getContext()).scaledTouchSlop
        mNestedScrollingChildHelper.isNestedScrollingEnabled = true
        isVerticalScrollBarEnabled = true
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val y = event.y;
        val action = event.action
        if (action == MotionEvent.ACTION_DOWN) {
            mLastMotionY = y
            mNestedScrollingChildHelper.startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL,ViewCompat.TYPE_TOUCH)
            return true
        }
        if (action == MotionEvent.ACTION_MOVE) {
            var diff = y - mLastMotionY
            if (abs(diff) > mTouchSlop) {
                println("zijiexiaozhan diff $diff")
                scrollBy(0, -diff.toInt())
            }
        }

        mLastMotionY = y
        return false
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        val superResult = super.onInterceptTouchEvent(ev)
        if (superResult) {
            return true
        }


        if (ev?.action == MotionEvent.ACTION_MOVE) {
            val y = ev?.y
            var diff = y - mLastMotionY
            if (abs(diff) > mTouchSlop&& mNestedScrollingParentHelper.nestedScrollAxes and View.SCROLL_AXIS_VERTICAL == 0) {
                println("zijiexiaozhan onInterceptTouchEvent diff $diff")
                return true
            }
            mLastMotionY = y

        } else if (ev?.action == MotionEvent.ACTION_DOWN) {
            mLastMotionY = ev.y
            mNestedScrollingChildHelper.startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL,ViewCompat.TYPE_TOUCH)
        }
        return false
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        println("zijiexiaozhan onMeasure $measuredHeight")

    }

    override fun measureChildWithMargins(
        child: View?,
        parentWidthMeasureSpec: Int,
        widthUsed: Int,
        parentHeightMeasureSpec: Int,
        heightUsed: Int
    ) {
        val newHeightSpec = View.MeasureSpec.makeMeasureSpec(
            View.MeasureSpec.getSize(parentHeightMeasureSpec),
            View.MeasureSpec.UNSPECIFIED
        )

        super.measureChildWithMargins(
            child,
            parentWidthMeasureSpec,
            widthUsed,
            newHeightSpec,
            heightUsed
        )
    }

    override fun getLogName(): String {
        return name
    }

    override fun onNestedScrollAccepted(child: View, target: View, axes: Int, type: Int) {
        mNestedScrollingParentHelper.onNestedScrollAccepted(child, target, axes, type)
    }

    override fun onStartNestedScroll(child: View, target: View, axes: Int, type: Int): Boolean {
        return axes and View.SCROLL_AXIS_VERTICAL != 0
    }

    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
//        TODO("Not yet implemented")
    }

    override fun onNestedScroll(
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int
    ) {
        scrollBy(0, dyUnconsumed)
    }

    override fun onStopNestedScroll(target: View, type: Int) {
        mNestedScrollingParentHelper.onStopNestedScroll(target, type)
    }

    override fun dispatchNestedScroll(
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        offsetInWindow: IntArray?,
        type: Int
    ): Boolean {
       return mNestedScrollingChildHelper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow, type)
    }

    override fun dispatchNestedPreScroll(
        dx: Int,
        dy: Int,
        consumed: IntArray?,
        offsetInWindow: IntArray?,
        type: Int
    ): Boolean {
        return mNestedScrollingChildHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow, type)
    }

    override fun stopNestedScroll(type: Int) {
        mNestedScrollingChildHelper.stopNestedScroll(type)
    }

    override fun hasNestedScrollingParent(type: Int): Boolean {
       return mNestedScrollingChildHelper.hasNestedScrollingParent(type)
    }

    override fun startNestedScroll(axes: Int, type: Int): Boolean {
       return mNestedScrollingChildHelper.startNestedScroll(axes, type)
    }

    fun setNestedScrollingEnable(enable:Boolean){
        mNestedScrollingChildHelper.isNestedScrollingEnabled = enable
    }
}