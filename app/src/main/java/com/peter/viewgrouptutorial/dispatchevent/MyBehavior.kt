package com.peter.viewgrouptutorial.dispatchevent

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.annotation.RequiresApi
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.peter.viewgrouptutorial.LogTag

open class MyBehavior :
    CoordinatorLayout.Behavior<View> {
    constructor() : super()
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)


    var interceptValue = false
    var touchValue = false
    var name = ""

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onInterceptTouchEvent(
        parent: CoordinatorLayout,
        child: View,
        ev: MotionEvent
    ): Boolean {
        Log.d(LogTag.tag, "$name onInterceptTouchEvent " + MotionEvent.actionToString(ev.action))
        return interceptValue
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onTouchEvent(parent: CoordinatorLayout, child: View, ev: MotionEvent): Boolean {
        Log.d(LogTag.tag, "$name onTouchEvent " + MotionEvent.actionToString(ev.action))
        return touchValue
    }
}