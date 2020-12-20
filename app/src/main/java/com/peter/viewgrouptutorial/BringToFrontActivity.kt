package com.peter.viewgrouptutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast

class BringToFrontActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bring_front)
        layoutInflater.inflate(R.layout.activity_bottom_navigation,null)
        val bringButton = findViewById<Button>(R.id.bring_button)
        val greenTextView = findViewById<TextView>(R.id.green_text)
        val redTextView = findViewById<TextView>(R.id.red_text)
        val root = findViewById<FrameLayout>(R.id.root_frameLayout)
        var frontTextView: TextView = redTextView;
        bringButton.setOnClickListener {
            root.bringChildToFront(frontTextView)
            frontTextView = if (frontTextView == greenTextView) {
                redTextView
            } else {
                greenTextView
            }
        }
        val views = arrayListOf<View>()
        root.addTouchables(views)

        val outViews = arrayListOf<View>()
        root.findViewsWithText(outViews,"click",View.FIND_VIEWS_WITH_CONTENT_DESCRIPTION)
        Toast.makeText(this,"touchableViews.size() ${views.size} outViews.size() ${outViews.size}",Toast.LENGTH_SHORT).show()

    }
}