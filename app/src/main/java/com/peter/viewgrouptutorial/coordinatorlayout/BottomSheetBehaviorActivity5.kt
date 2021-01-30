package com.peter.viewgrouptutorial.coordinatorlayout

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.peter.viewgrouptutorial.R

/**
 * 固定BottomSheetBehavior的高度
 */
class BottomSheetBehaviorActivity5 : AppCompatActivity() {
    lateinit var bottomBehaviorLayout: LinearLayout
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    lateinit var coordinatorLayout: CoordinatorLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_sheet_behavior5)
        coordinatorLayout = findViewById(R.id.coordinator_layout)
        bottomBehaviorLayout = findViewById(R.id.bottom_behavior_layout)
        bottomSheetBehavior = BottomSheetBehavior.from(bottomBehaviorLayout)
        coordinatorLayout.post {
            //将isFitToContents设置为false
            bottomSheetBehavior.isFitToContents = false
            //设置peekHeight=parentHeight-200dp
            bottomSheetBehavior.peekHeight =
                coordinatorLayout.height - (resources.displayMetrics.density * 200).toInt()
            //那么根据博客所述 collapsedOffset走case4
            //collapsedOffset = parentHeight - peekHeight = 200dp
            //要固定BottomSheetBehavior的高度expandedOffset=collapsedOffset
            bottomSheetBehavior.setExpandedOffset((resources.displayMetrics.density * 200).toInt())
        }
    }
}