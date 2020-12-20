package com.peter.viewgrouptutorial.coordinatorlayout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.peter.viewgrouptutorial.R
import com.peter.viewgrouptutorial.dispatchevent.MyFrameLayout

class CoordinatorEventActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coordinator_event1)
        val layoutParams =
            findViewById<MyFrameLayout>(R.id.frameLayout).layoutParams as CoordinatorLayout.LayoutParams
        layoutParams.behavior = EventBehavior().apply {
            name = "不拦截behavior"
            intercept = false
            touch = true
        }
        findViewById<MyFrameLayout>(R.id.frameLayout).apply {
            name = "MyFrameLayout"
        }
    }
}