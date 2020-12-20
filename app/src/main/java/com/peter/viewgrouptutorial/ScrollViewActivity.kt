package com.peter.viewgrouptutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class ScrollViewActivity : AppCompatActivity() {
    lateinit var smoothScrollCb: CheckBox
    lateinit var pageUp: Button
    lateinit var pageDown: Button
    lateinit var mScrollView: ScrollView
    lateinit var mSeekBar: SeekBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll_view)
        smoothScrollCb = findViewById(R.id.smooth_scroll_cb)
        pageUp = findViewById(R.id.page_up_button)
        pageDown = findViewById(R.id.page_down_button)
        mScrollView = findViewById(R.id.scroll_view)
        mSeekBar = findViewById(R.id.seek_bar)
        findViewById<TextView>(R.id.text_view).requestFocus()
        smoothScrollCb.setOnCheckedChangeListener { _, isChecked ->

            mScrollView.isSmoothScrollingEnabled = isChecked

        }
        smoothScrollCb.isChecked = true
        pageUp.setOnClickListener {
            mScrollView.pageScroll(View.FOCUS_UP)
        }
        pageDown.setOnClickListener {
            mScrollView.pageScroll(View.FOCUS_DOWN)
        }
    }

    fun fullUp(view: View) {
        mScrollView.fullScroll(View.FOCUS_UP)
    }

    fun fullDown(view: View) {
        mScrollView.fullScroll(View.FOCUS_DOWN)

    }

    fun arrowUp(view: View) {
        mScrollView.arrowScroll(View.FOCUS_UP)

    }

    fun arrowDown(view: View) {
        mScrollView.arrowScroll(View.FOCUS_DOWN)

    }

    fun fling(view: View) {
        if (mScrollView.scrollY == 0) {
            mScrollView.fling(mSeekBar.progress * 100)
        } else {
            mScrollView.fling(-mSeekBar.progress * 100)
        }
    }

    fun setScrollMode(view: View) {
        mScrollView.overScrollMode = ScrollView.OVER_SCROLL_NEVER
    }
}