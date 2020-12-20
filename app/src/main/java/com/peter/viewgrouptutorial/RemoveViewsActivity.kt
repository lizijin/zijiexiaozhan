package com.peter.viewgrouptutorial

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity

class RemoveViewsActivity : AppCompatActivity() {
    private lateinit var frameLayout:FrameLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remove_views)
        frameLayout = findViewById(R.id.frameLayout2)

    }

    fun changeButton(view: View) {
        frameLayout.offsetTopAndBottom(-200)
        frameLayout.removeAllViews();
        val view = View(this)
        view.setBackgroundColor(Color.BLUE);
        frameLayout.addView(view)
        frameLayout.offsetTopAndBottom(-200)
    }
}