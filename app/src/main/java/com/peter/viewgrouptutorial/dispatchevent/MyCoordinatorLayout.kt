package com.peter.viewgrouptutorial.dispatchevent

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.annotation.RequiresApi
import androidx.coordinatorlayout.widget.CoordinatorLayout

class MyCoordinatorLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : CoordinatorLayout(context, attrs, defStyleAttr) {
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        Log.d(
            "zijiexiaozhan",
            "MyCoordinatorLayout onInterceptTouchEvent ${MotionEvent.actionToString(ev.action)}"
        )

        return super.onInterceptTouchEvent(ev)
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onTouchEvent(ev: MotionEvent): Boolean {
        Log.d(
            "zijiexiaozhan",
            "MyCoordinatorLayout onTouchEvent ${MotionEvent.actionToString(ev.action)}"
        )
        return super.onTouchEvent(ev)
    }
}