package com.peter.viewgrouptutorial.coordinatorlayout

import android.os.Build
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.annotation.RequiresApi
import androidx.coordinatorlayout.widget.CoordinatorLayout

class EventBehavior  :
    CoordinatorLayout.Behavior<View>() {
    var name = ""
    var intercept = false
    var touch = false

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onInterceptTouchEvent(
        parent: CoordinatorLayout,
        child: View,
        ev: MotionEvent
    ): Boolean {
        Log.d("zijiexiaozhan", "$name onInterceptTouchEvent ${MotionEvent.actionToString(ev.action)}")
        return intercept
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onTouchEvent(parent: CoordinatorLayout, child: View, ev: MotionEvent): Boolean {
        Log.d("zijiexiaozhan", "$name onTouchEvent ${MotionEvent.actionToString(ev.action)}")
        return touch
    }
}