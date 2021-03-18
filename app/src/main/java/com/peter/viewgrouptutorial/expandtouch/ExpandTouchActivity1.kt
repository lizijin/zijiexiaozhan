package com.peter.viewgrouptutorial.expandtouch

import android.graphics.Rect
import android.os.Bundle
import android.view.TouchDelegate
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.peter.viewgrouptutorial.R

class ExpandTouchActivity1 : AppCompatActivity() {
    private lateinit var mClickMeButton: Button
    private lateinit var mContentFrameLayout: FrameLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expand_touch1)
        mClickMeButton = findViewById(R.id.btn_click_me)
        mContentFrameLayout = findViewById(R.id.frame_layout_content)
        mClickMeButton.setOnClickListener {
            Toast.makeText(this, "You click me!", Toast.LENGTH_SHORT).show()
        }
        mClickMeButton.post {
            val rect = Rect()
            ViewGroupUtils.getDescendantRect(mContentFrameLayout, mClickMeButton, rect)
            rect.inset(-500, -500)
            mContentFrameLayout.touchDelegate = TouchDelegate(rect, mClickMeButton)
        }
    }
}