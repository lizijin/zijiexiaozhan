package com.peter.viewgrouptutorial.dispatchevent

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ScrollView
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class MyScrollView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ScrollView(context, attrs, defStyleAttr) {
    init {
        isNestedScrollingEnabled = false
    }
    var name = ""
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        Log.d("NestScrollingTest"," ")
        Log.d("NestScrollingTest"," ")
        Log.d("NestScrollingTest", "MyScrollView $name onInterceptTouchEvent before ${MotionEvent.actionToString(ev.action)}")
        val intercept = super.onInterceptTouchEvent(ev)
        Log.d("NestScrollingTest", "MyScrollView $name onInterceptTouchEvent after ${MotionEvent.actionToString(ev.action)} $intercept")
        return intercept
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onTouchEvent(ev: MotionEvent): Boolean {
        Log.d("NestScrollingTest"," ")
        Log.d("NestScrollingTest"," ")


        Log.d("NestScrollingTest","MyScrollView $name onTouchEvent before ${MotionEvent.actionToString(ev.action)} ")

        val touch = super.onTouchEvent(ev)
        Log.d("NestScrollingTest","MyScrollView $name onTouchEvent after ${MotionEvent.actionToString(ev.action)} $touch")
        return touch
    }

    override fun dispatchNestedScroll(
         dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, offsetInWindow: IntArray?
    ): Boolean {
        Log.d("NestScrollingTest", "MyScrollView $name dispatchNestedScroll")
        return super.dispatchNestedScroll(
            dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow
        )
    }

    override fun dispatchNestedPreFling(velocityX: Float, velocityY: Float): Boolean {
        Log.d("NestScrollingTest", "MyScrollView $name dispatchNestedPreFling")
        return super.dispatchNestedPreFling(velocityX, velocityY)
    }

    override fun dispatchNestedFling(
        velocityX: Float, velocityY: Float, consumed: Boolean
    ): Boolean {
        Log.d("NestScrollingTest", "MyScrollView $name dispatchNestedFling")

        return super.dispatchNestedFling(velocityX, velocityY, consumed)
    }

    override fun dispatchNestedPreScroll(
        dx: Int, dy: Int, consumed: IntArray?, offsetInWindow: IntArray?
    ): Boolean {
        Log.d("NestScrollingTest", "MyScrollView $name dispatchNestedPreScroll dx:$dx dy:$dy")
        return super.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow)
    }

    override fun onStopNestedScroll(target: View?) {
        Log.d("NestScrollingTest", "MyScrollView $name onStopNestedScroll")
        super.onStopNestedScroll(target)
    }

    override fun onStartNestedScroll(child: View?, target: View?, nestedScrollAxes: Int): Boolean {
        Log.d("NestScrollingTest", "MyScrollView $name onStartNestedScroll")
        return super.onStartNestedScroll(child, target, nestedScrollAxes)
    }

    override fun onNestedPreScroll(target: View?, dx: Int, dy: Int, consumed: IntArray?) {
        Log.d("NestScrollingTest", "MyScrollView $name onNestedPreScroll")
        super.onNestedPreScroll(target, dx, dy, consumed)
    }

    override fun onNestedScroll(
        target: View?, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int
    ) {
        Log.d("NestScrollingTest", "MyScrollView $name onNestedScroll")
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed)
    }


}