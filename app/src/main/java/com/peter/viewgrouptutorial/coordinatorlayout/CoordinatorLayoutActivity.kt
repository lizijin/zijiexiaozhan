package com.peter.viewgrouptutorial.coordinatorlayout

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.peter.viewgrouptutorial.R
import com.peter.viewgrouptutorial.coordinatorlayout.jd.JdStickyHeaderAppBarLayoutActivity

class CoordinatorLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coordinator_layout_list)
    }

    fun gotoAppBarLayoutAndScrollView(view: View) {
        startActivity(Intent(this, AppbarLayoutExampleActivity::class.java))

    }

    fun gotoCollapsing(view: View) {
        startActivity(Intent(this, JdStickyHeaderAppBarLayoutActivity::class.java))

    }


    fun gotoLayoutAnchor(view: View) {
        startActivity(Intent(this, CoordinatorLayoutAnchorActivity::class.java))

    }
}