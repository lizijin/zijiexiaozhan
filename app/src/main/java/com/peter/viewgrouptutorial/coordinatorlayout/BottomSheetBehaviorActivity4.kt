package com.peter.viewgrouptutorial.coordinatorlayout

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.peter.viewgrouptutorial.R
class BottomSheetBehaviorActivity4 : AppCompatActivity() {
    lateinit var bottomBehaviorLayout: LinearLayout
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_sheet_behavior4)
        bottomBehaviorLayout = findViewById(R.id.bottom_behavior_layout)
        bottomSheetBehavior = BottomSheetBehavior.from(bottomBehaviorLayout)
        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                println("zijiexiaozhan onSlide $slideOffset")
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                var stateStr = when (newState) {
                    BottomSheetBehavior.STATE_HIDDEN -> {
                        "STATE_HIDDEN"
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        "STATE_COLLAPSED"
                    }
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        "STATE_EXPANDED"
                    }
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                        "STATE_HALF_EXPANDED"
                    }
                    else -> "Other"
                }
                println("zijiexiaozhan onStateChanged $stateStr")
            }
        })
    }

    fun onFrameClick(view: View) {
        Toast.makeText(this, "TOAST", Toast.LENGTH_SHORT).show()
    }
}