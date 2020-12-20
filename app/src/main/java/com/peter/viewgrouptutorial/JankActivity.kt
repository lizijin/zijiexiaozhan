package com.peter.viewgrouptutorial

import android.content.Context
import android.graphics.Canvas
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Trace
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import androidx.annotation.RequiresApi
import java.util.concurrent.TimeUnit

/**
 * 模拟卡顿
 */
class JankActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jank)
    }

    fun doLongWork(view: View) {
        val startTime = System.currentTimeMillis()
        var i = 0
        view as Button

        while (System.currentTimeMillis() < (startTime + 5000)) {
            view.text = "$i++"
        }
        view.text = "主线程做耗时操作"
    }

    fun drawLongWork(view: View) {
        val overDrawView = findViewById<View>(R.id.over_draw_view)
        overDrawView.visibility = if (overDrawView.visibility == View.VISIBLE) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    internal class OverDrawView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
    ) : View(context, attrs, defStyleAttr) {
        @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
        override fun onDraw(canvas: Canvas?) {
            Trace.beginSection("zijiexiaozhantest")
            super.onDraw(canvas)
            TimeUnit.MILLISECONDS.sleep(40)//睡眠40ms
            invalidate()//故意的
            Trace.endSection()
        }
    }

    internal class NormalDrawView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
    ) : View(context, attrs, defStyleAttr) {
        @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
        override fun onDraw(canvas: Canvas?) {
            Trace.beginSection("NormalDrawView")
            super.onDraw(canvas)
            invalidate()//故意的

            Trace.endSection()
        }
    }

    fun showNormalView(view: View) {
        val overDrawView = findViewById<View>(R.id.normal_draw_view)
        overDrawView.visibility = if (overDrawView.visibility == View.VISIBLE) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }
}