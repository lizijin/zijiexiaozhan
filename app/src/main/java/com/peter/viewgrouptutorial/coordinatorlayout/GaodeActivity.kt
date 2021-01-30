package com.peter.viewgrouptutorial.coordinatorlayout

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.peter.viewgrouptutorial.R
import com.peter.viewgrouptutorial.coordinatorlayout.jd.ViewOffsetHelper

/**
 * 仿高德地图效果
 */
class GaodeActivity : AppCompatActivity() {
    lateinit var mFrameLayout: FrameLayout
    lateinit var mYellowLayout: View
    lateinit var mRedLayout: View
    lateinit var mBottomSheetBehavior: BottomSheetBehavior<FrameLayout>
    lateinit var mViewOffsetHelper: ViewOffsetHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gaode)
        mFrameLayout = findViewById(R.id.frame_layout)
        mYellowLayout = findViewById(R.id.yellow_layout)
        mViewOffsetHelper = ViewOffsetHelper(mYellowLayout)

        mRedLayout = findViewById(R.id.red_layout)
        mBottomSheetBehavior = BottomSheetBehavior.from(mFrameLayout)
//        mBottomSheetBehavior.isFitToContents = false
//        mBottomSheetBehavior.setExpandedOffset((resourcese.displayMetrics.density * 600).toInt())
        mBottomSheetBehavior.peekHeight = (resources.displayMetrics.density * 200).toInt()
        mBottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                if (slideOffset > 0) {
                    mViewOffsetHelper.topAndBottomOffset= (resources.displayMetrics.density * 100).toInt()+((resources.displayMetrics.density * 100).toInt()*slideOffset).toInt()

                }
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
            }
        })
    }
}