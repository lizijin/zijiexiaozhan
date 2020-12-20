package com.peter.viewgrouptutorial.dispatchevent

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout

class CoordinatorLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val coordinatorLayout = MyCoordinatorLayout(this)


        val layoutParams1 = CoordinatorLayout.LayoutParams(-1, -1)
        var myBehavior1 = MyBehavior()
        myBehavior1.name = "vp1 behavior"
        layoutParams1.behavior = myBehavior1

        val layoutParams2 = CoordinatorLayout.LayoutParams(-1, -1)
        var myBehavior2 = MyBehavior()
        myBehavior2.name = "vp2 behavior"
        layoutParams2.behavior = myBehavior2
        val vp1 = MyLayout(this)
        vp1.name = "vp1"


        val vp2 = MyLayout(this)
        vp2.name = "vp2"
        coordinatorLayout.addView(vp1, layoutParams1)
        coordinatorLayout.addView(vp2, layoutParams2)

        setContentView(coordinatorLayout)
    }

    inner class MyCoordinatorLayout @JvmOverloads constructor(
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

    inner class MyBehavior : CoordinatorLayout.Behavior<View>() {
        var name = ""

        @RequiresApi(Build.VERSION_CODES.KITKAT)
        override fun onInterceptTouchEvent(
            parent: CoordinatorLayout,
            child: View,
            ev: MotionEvent
        ): Boolean {
            Log.d(
                "zijiexiaozhan",
                "$name onInterceptTouchEvent ${MotionEvent.actionToString(ev.action)}"
            )

            if(ev.action==MotionEvent.ACTION_MOVE)
            return true
            return super.onInterceptTouchEvent(parent, child, ev)
        }

        @RequiresApi(Build.VERSION_CODES.KITKAT)
        override fun onTouchEvent(
            parent: CoordinatorLayout,
            child: View,
            ev: MotionEvent
        ): Boolean {
            Log.d(
                "zijiexiaozhan",
                "$name onTouchEvent ${MotionEvent.actionToString(ev.action)}"
            )
//            return super.onTouchEvent(parent, child, ev)
            return true
        }
    }

    inner class MyLayout @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
    ) : FrameLayout(context, attrs, defStyleAttr) {
        var name: String = ""

        @RequiresApi(Build.VERSION_CODES.KITKAT)
        override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
            Log.d(
                "zijiexiaozhan",
                "$name onInterceptTouchEvent ${MotionEvent.actionToString(ev.action)}"
            )
            return super.onInterceptTouchEvent(ev)
        }

        @RequiresApi(Build.VERSION_CODES.KITKAT)
        override fun onTouchEvent(ev: MotionEvent): Boolean {
            Log.d(
                "zijiexiaozhan",
                "$name onTouchEvent ${MotionEvent.actionToString(ev.action)}"
            )
//            return super.onTouchEvent(ev)
            return true

        }
    }
}