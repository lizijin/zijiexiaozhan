package com.peter.viewgrouptutorial.expandtouch

import android.graphics.Rect
import android.os.Bundle
import android.view.TouchDelegate
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.peter.viewgrouptutorial.R

class ExpandTouchActivity3 : AppCompatActivity() {
    private lateinit var mContentLinearLayout: LinearLayout
    private lateinit var mBlueButton: Button
    private lateinit var mRedButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expand_touch3)
        mBlueButton = findViewById(R.id.blue_click_me)
        mRedButton = findViewById(R.id.red_click_me)
        mContentLinearLayout = findViewById(R.id.linear_layout_content)
        mBlueButton.setOnClickListener {
            Toast.makeText(this, "click blue button", Toast.LENGTH_SHORT).show()
        }
        mRedButton.setOnClickListener {
            Toast.makeText(this, "click red button", Toast.LENGTH_SHORT).show()
        }
        mContentLinearLayout.post {
            val blueRect = Rect()
            ViewGroupUtils.getDescendantRect(mContentLinearLayout, mBlueButton, blueRect)
            blueRect.inset(-500, -500)
            mContentLinearLayout.touchDelegate = TouchDelegate(blueRect, mBlueButton)

            val redRect = Rect()
            ViewGroupUtils.getDescendantRect(mContentLinearLayout, mRedButton, redRect)
            redRect.inset(-500, -500)
            mContentLinearLayout.touchDelegate = TouchDelegate(redRect, mRedButton)
        }


    }
}