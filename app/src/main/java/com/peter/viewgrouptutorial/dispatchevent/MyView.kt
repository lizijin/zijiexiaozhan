package com.peter.viewgrouptutorial.dispatchevent

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.annotation.RequiresApi
import com.peter.viewgrouptutorial.nestedscroll.Naming

open class MyView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), Naming {
    var name: String = ""
    var mOnTouchValue = false//是否分发事件

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onTouchEvent(event: MotionEvent): Boolean {
//        return super.onTouchEvent(event)
        Log.d("zijiexiaozhan", "$name onTouchEvent ${MotionEvent.actionToString(event.action)}")

        if (mOnTouchValue) {
            return true
        }
        return super.onTouchEvent(event)
    }

    override fun getNaming(): String = name

}