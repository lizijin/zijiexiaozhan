package com.peter.viewgrouptutorial.dispatchevent

import android.os.Bundle
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.peter.viewgrouptutorial.R

/**
 * 依赖于别的View的view的Behavior的先执行
 */
class CoordinatorLayoutDependentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coorinator_layout_dependent)
        ((findViewById<ScrollView>(R.id.scroll_view).layoutParams as CoordinatorLayout.LayoutParams).behavior as MyBehavior).name="scrollView behavior"
    }
}