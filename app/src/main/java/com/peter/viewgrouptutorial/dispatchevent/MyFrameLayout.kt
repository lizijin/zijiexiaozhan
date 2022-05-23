package com.peter.viewgrouptutorial.dispatchevent

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.FrameLayout
import androidx.annotation.RequiresApi

open class MyFrameLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr),
    com.peter.viewgrouptutorial.nestedscroll.Log {
    open var name: String = ""
    var mOnTouchValue = false//是否分发事件
    var mDispatchValueSuper = true //是否调用super.dispatchTouchEvent,如果false 直接return true
    var mRegionInterceptor = false//在某个特定区域 拦截事件

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {

        return if (mDispatchValueSuper) {
            super.dispatchTouchEvent(ev)
        } else {
            true
        }
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        Log.d("zijiexiaozhan", "$name onInterceptTouchEvent ${MotionEvent.actionToString(ev.action)}")

        if (mRegionInterceptor) {
            val touchY = ev?.y
            if (touchY != null) {
                if (touchY > measuredHeight / 2) {
                    return true
                }

            }
        }
        return super.onInterceptTouchEvent(ev)
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onTouchEvent(event: MotionEvent): Boolean {
        Log.d("zijiexiaozhan", "$name onTouchEvent ${MotionEvent.actionToString(event.action)}")
        if (mOnTouchValue) {
            return true
        }
        return super.onTouchEvent(event)
    }

    override fun getLogName(): String = name

}

