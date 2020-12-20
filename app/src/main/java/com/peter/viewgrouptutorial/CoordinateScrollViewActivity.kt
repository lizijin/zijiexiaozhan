package com.peter.viewgrouptutorial

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ScrollView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.coordinatorlayout.widget.CoordinatorLayout

class CoordinateScrollViewActivity : AppCompatActivity() {
    lateinit var mScrollView: ScrollView
    lateinit var mCoordinatorLayout: CoordinatorLayout
    lateinit var mTextView:TextView
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coordinate_scroll_view)
        mScrollView = findViewById(R.id.scroll_view)
        mCoordinatorLayout = findViewById(R.id.coordinator_layout)
        mTextView = findViewById(R.id.scroll_text)
//        View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY
        mScrollView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            mCoordinatorLayout.dispatchDependentViewsChanged(mScrollView)
            println("zijiexiaozhan scrollY is  $scrollY ${mScrollView.bottom} ${mTextView.height}")

        }
    }

}