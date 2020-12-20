package com.peter.viewgrouptutorial

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class ScrollActivity : AppCompatActivity() {
    lateinit var mScrollText: TextView
    lateinit var mScrollResultText: TextView
    lateinit var mLinearLayout: ViewGroup
    var mScrollX = 10
    var mScrollY = 10
    var mOffset = 10
    lateinit var mButton1: Button
    lateinit var mButton2: Button
    lateinit var mButton3: Button

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll)
        mScrollText = findViewById<TextView>(R.id.scroll_text)
        mScrollText.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            mScrollResultText.text =
                "scrollX = $scrollX, scrollY=$scrollY"
        }
        mScrollText.setOnTouchListener { v, event ->
            Toast.makeText(this@ScrollActivity, "${event.x} ${event.y}", Toast.LENGTH_SHORT).show()
            true
        }
        mScrollResultText = findViewById<TextView>(R.id.scroll_result_text)
        mScrollResultText.text =
            "mScrollText.scrollX = ${mScrollText.scrollX} mScrollText.scrollY = ${mScrollText.scrollY} "
        mLinearLayout = findViewById(R.id.linear_layout)
        mButton1 = findViewById(R.id.button1)
        mButton2 = findViewById(R.id.button2)
        mButton3 = findViewById(R.id.button3)
//        mButton2.bringToFront()
    }

    fun scrollTo(view: View) {

        mScrollText.scrollTo(mScrollX, mScrollY)
        mLinearLayout.scrollTo(mScrollX, mScrollY)
        mScrollX += 10
        mScrollY += 10

    }

    fun offsetTopAndBottom(view: View) {
//        mScrollText.offsetTopAndBottom(mOffset).apply {
//            mOffset += 10
//        }
        mLinearLayout.offsetTopAndBottom(mOffset).apply {
            mButton2.offsetTopAndBottom(-mOffset)

            mOffset -= 10
        }
        println("mLinearLayout.top:${mLinearLayout.top} button1.top ${mButton1.top} button2.top ${mButton2.top} button3.top ${mButton3.top}")

        var locationInWindow: IntArray = IntArray(2)
        mButton1.getLocationInWindow(locationInWindow)

        var locationInScreen = IntArray(2)
        mButton1.getLocationOnScreen(locationInScreen)


        println("mLinearLayout.top:${mLinearLayout.top} ${locationInWindow[0]} X ${locationInScreen[1]}  ${locationInScreen[0]} X ${locationInWindow[1]}")

    }

    fun scrollTextOnclick(view: View) {

    }

    inner class MyLinearLayout @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
    ) : LinearLayout(context, attrs, defStyleAttr) {
        init {
            isChildrenDrawingOrderEnabled = true
        }

        override fun getChildDrawingOrder(childCount: Int, i: Int): Int {
            if (i == 1) return 2
            if (i == 2) return 1
            return super.getChildDrawingOrder(childCount, i)
        }
    }
}