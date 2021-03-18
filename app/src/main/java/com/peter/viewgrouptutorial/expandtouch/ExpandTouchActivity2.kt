package com.peter.viewgrouptutorial.expandtouch

import android.graphics.Rect
import android.os.Bundle
import android.view.TouchDelegate
import android.widget.Button
import android.widget.CheckBox
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.peter.viewgrouptutorial.R
import kotlinx.android.synthetic.main.activity_expand_touch2.*

class ExpandTouchActivity2 : AppCompatActivity() {
    private lateinit var mClickMeButton: Button
    private lateinit var mContentFrameLayout: FrameLayout
    private lateinit var mLeftButton: Button
    private lateinit var mRightButton: Button
    private lateinit var mTopButton: Button
    private lateinit var mBottomButton: Button
    private lateinit var mCheckBox: CheckBox


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expand_touch2)
        mClickMeButton = findViewById(R.id.btn_click_me)
        mLeftButton = findViewById(R.id.left_button)
        mRightButton = findViewById(R.id.right_button)
        mTopButton = findViewById(R.id.top_button)
        mBottomButton = findViewById(R.id.bottom_button)

        mLeftButton.setOnClickListener {
            Toast.makeText(this, "click left button", Toast.LENGTH_SHORT).show()
        }
        mRightButton.setOnClickListener {
            Toast.makeText(this, "click right button", Toast.LENGTH_SHORT).show()
        }
        mTopButton.setOnClickListener {
            Toast.makeText(this, "click top button", Toast.LENGTH_SHORT).show()
        }
        mBottomButton.setOnClickListener {
            Toast.makeText(this, "click bottom button", Toast.LENGTH_SHORT).show()
        }
        mCheckBox = findViewById(R.id.checkbox)
        mCheckBox.isChecked = true
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

        checkbox.setOnCheckedChangeListener { _, isChecked ->

            mLeftButton.isClickable = isChecked
            mRightButton.isClickable = isChecked
            mTopButton.isClickable = isChecked
            mBottomButton.isClickable = isChecked
        }
    }
}