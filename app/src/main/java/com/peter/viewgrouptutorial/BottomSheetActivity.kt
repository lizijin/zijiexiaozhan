package com.peter.viewgrouptutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetBehavior

class BottomSheetActivity : AppCompatActivity() {
    lateinit var behavior: BottomSheetBehavior<TextView>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_sheet)
//        val bottomTextView = findViewById<TextView>(R.id.text_bottom)
//        behavior = BottomSheetBehavior.from(bottomTextView)


    }

    fun setHalf(view: View) {
        behavior.halfExpandedRatio = 0.9f
        behavior.isFitToContents = false
        view.requestLayout()
        behavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
//        behavior.isFitToContents = false
    }

    fun setCollapsed(view: View) {
        behavior.state = BottomSheetBehavior.STATE_COLLAPSED

    }
    fun setExpanded(view: View) {
        behavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED

    }
    fun setHidden(view: View) {
        behavior.isHideable = true
        behavior.state = BottomSheetBehavior.STATE_HIDDEN
    }
}