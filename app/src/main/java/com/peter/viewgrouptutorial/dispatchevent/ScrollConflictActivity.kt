package com.peter.viewgrouptutorial.dispatchevent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.peter.viewgrouptutorial.R

/**
 * 滑动冲突Activity
 */
class ScrollConflictActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll_conflict)
        findViewById<MyScrollView>(R.id.outer_scroll_view).name="outerScrollView"
        findViewById<MyScrollView>(R.id.inner_scroll_view).name="innerScrollView"

    }
}