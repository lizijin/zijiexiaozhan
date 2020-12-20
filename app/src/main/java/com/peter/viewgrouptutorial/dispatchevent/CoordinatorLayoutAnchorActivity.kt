package com.peter.viewgrouptutorial.dispatchevent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.peter.viewgrouptutorial.R

/**
 * Layout Anchor 不影响事件分发的顺序
 */
class CoordinatorLayoutAnchorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coordinator_layout_anchor)
        val child1 = findViewById<MyFrameLayout>(R.id.child1).apply { name = "child1" }
        val child2 = findViewById<MyFrameLayout>(R.id.child2).apply { name = "child2" }
        val child3 = findViewById<MyFrameLayout>(R.id.child3).apply { name = "child3" }
        val child4 = findViewById<MyFrameLayout>(R.id.child4).apply { name = "child4" }
        (child1.layoutParams as CoordinatorLayout.LayoutParams).apply {
            behavior = MyBehavior().apply {
                name = "child1 behavior"
            }
        }
        (child2.layoutParams as CoordinatorLayout.LayoutParams).apply {
            behavior = MyBehavior().apply {
                name = "child2 behavior"
            }
        }
        (child3.layoutParams as CoordinatorLayout.LayoutParams).apply {
            behavior = MyBehavior().apply {
                name = "child3 behavior"
            }
        }
        (child4.layoutParams as CoordinatorLayout.LayoutParams).apply {
            behavior = MyBehavior().apply {
                name = "child4 behavior"
            }
        }
    }
}