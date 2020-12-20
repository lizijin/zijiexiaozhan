package com.peter.viewgrouptutorial.nestedscroll

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.annotation.RequiresApi
import androidx.core.view.NestedScrollingChild2
import androidx.core.view.NestedScrollingChildHelper
import androidx.core.view.ViewCompat
import com.peter.viewgrouptutorial.dispatchevent.MyView

class ScrollingChildView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : MyView(context, attrs, defStyleAttr), NestedScrollingChild2 {
    private val scrollingChildHelper = NestedScrollingChildHelper(this)
//    var startScroll: Boolean = false
    var mLastMotionY =0

    fun setNestScrollingEnabled(enable: Boolean) {
        scrollingChildHelper.isNestedScrollingEnabled = enable
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onTouchEvent(event: MotionEvent): Boolean {
        var superResult = super.onTouchEvent(event)
        if (event.action == MotionEvent.ACTION_DOWN && scrollingChildHelper.isNestedScrollingEnabled) {
            this.startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL, ViewCompat.TYPE_TOUCH)
        }
        if ((event.action == MotionEvent.ACTION_CANCEL) or (event.action == MotionEvent.ACTION_UP)) {
            this.stopNestedScroll(ViewCompat.TYPE_TOUCH)
        }
        if (event.action == MotionEvent.ACTION_MOVE) {

        }
        return superResult
    }

    ////为何这个还要返回值
    override fun dispatchNestedScroll(
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        offsetInWindow: IntArray?,
        type: Int
    ): Boolean {
        Log.d("zijiexiaozhan", "$name dispatchNestedScroll")
        scrollingChildHelper.dispatchNestedScroll(
            dxConsumed,
            dyConsumed,
            dxUnconsumed,
            dyUnconsumed,
            offsetInWindow,
            type
        )
        return true
    }

    override fun dispatchNestedPreScroll(
        dx: Int,
        dy: Int,
        consumed: IntArray?,
        offsetInWindow: IntArray?,
        type: Int
    ): Boolean {
        Log.d("zijiexiaozhan", "$name dispatchNestedPreScroll")
        scrollingChildHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow, type)

        return true
    }

    override fun stopNestedScroll(type: Int) {
        scrollingChildHelper.stopNestedScroll(type)
        Log.d("zijiexiaozhan", "$name stopNestedScroll")
    }

    override fun hasNestedScrollingParent(type: Int): Boolean {
        Log.d("zijiexiaozhan", "$name stopNestedScroll")
        return scrollingChildHelper.hasNestedScrollingParent(type)
    }

    //为何这个还要返回值
    override fun startNestedScroll(axes: Int, type: Int): Boolean {
        Log.d("zijiexiaozhan", "$name startNestedScroll")
        return scrollingChildHelper.startNestedScroll(axes, type)
    }
}