package com.peter.viewgrouptutorial.coordinatorlayout

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.peter.viewgrouptutorial.R

class BottomSheetBehaviorActivity : AppCompatActivity() {
    lateinit var bottomBehaviorLayout: LinearLayout
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_sheet_behavior)
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

    fun setCollapsed(view: View) {
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    fun setExpanded(view: View) {
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED

    }

    fun setHalf(view: View) {
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
    }

    fun setHidden(view: View) {
        bottomSheetBehavior.isHideable = true
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

    }

    fun findCoordinatorLayout(view: View): CoordinatorLayout? {
        if (view !is ViewGroup) return null
        if (view is CoordinatorLayout) return view
        for (i in 0 until view.childCount) {
            val v = findCoordinatorLayout(view.getChildAt(i))
            if (v != null) return v
        }
        return null
    }

    fun openDialog(view: View) {
        val dialog = BottomSheetDialog(this)

        dialog.setContentView(R.layout.layout_for_bottom_sheet_dialog)

        val decorView = dialog.window.decorView

        decorView.findViewById<View>(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);

        var coordinatorLayout = findCoordinatorLayout(decorView)
        val nested = dialog.findViewById<View>(R.id.nested)
//        nested?.setOnClickListener{
//            dialog.behavior.isHideable = true
//            dialog.behavior.state= BottomSheetBehavior.STATE_HIDDEN
//        }
        nested?.viewTreeObserver?.addOnGlobalLayoutListener {
            val outRect = Rect()
            ViewGroupUtils.getDescendantRect(coordinatorLayout, nested, outRect)
            dialog.behavior.isFitToContents = false;
            dialog.behavior.setExpandedOffset(outRect.top)
            dialog.behavior.isHideable = false
            dialog.behavior.peekHeight = (resources.displayMetrics.density*300).toInt()
            println("zijiexiaozhan ${outRect.top}")
        }

        dialog.behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    println("zijiexiaozhan ")

                }
            }
        })

        dialog.show()
    }

    fun openDialog2(view: View) {
        val dialog = BottomSheetDialog(this);
        dialog.setContentView(R.layout.layout_for_bottom_sheet_dialog)
        val decorView = dialog.window.decorView
        var coordinatorLayout = findCoordinatorLayout(decorView)
        val nested = dialog.findViewById<View>(R.id.nested)

        dialog.behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    println("zijiexiaozhan ")

                }
            }
        })

        dialog.show()
    }

    fun onRedClick(view: View) {
        Toast.makeText(this,"On click",Toast.LENGTH_SHORT).show()
    }
}