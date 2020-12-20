package com.peter.viewgrouptutorial.nestedscroll

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.peter.viewgrouptutorial.dispatchevent.MyFrameLayout
import com.peter.viewgrouptutorial.dispatchevent.MyView

class ScrollFourActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //ViewGroup1 {View1,View2,View3}
        //ViewGroup2 {ScrollingChild1,View4,View5}
        //ScrollingParent2 {ScrollingChild2,View6,View7}
        //ScrollingParent1 {ViewGroup1,ViewGroup2,ScrollingParent2}

        val view1 = MyView(this).apply {
            name = "View1"
        }
        val view2 = MyView(this).apply {
            name = "View2"
        }
        val view3 = MyView(this).apply {
            name = "View3"
        }

        val viewGroup1 = MyFrameLayout(this).apply {
            name = "ViewGroup1"
            addView(view1)
            addView(view2)
            addView(view3)
        }

        val view4 = MyView(this).apply {
            name = "View4"
        }
        val view5 = MyView(this).apply {
            name = "View5"
        }
        val scrollingChild1 = ScrollingChildView(this).apply {
            name = "ScrollingChild1"
            setNestScrollingEnabled(true)
        }

        val viewGroup2 = MyFrameLayout(this).apply {
            name = "ViewGroup2"
            addView(scrollingChild1)
            addView(view4)
            addView(view5)
        }


        val view6 = MyView(this).apply {
            name = "View6"
        }
        val view7 = MyView(this).apply {
            name = "View7"
        }
        val scrollingChild2 = ScrollingChildView(this).apply {
            name = "ScrollingChild2"
            setNestScrollingEnabled(true)
            mOnTouchValue = true
        }

        val scrollingParent2 = ScrollingParentFrameLayout(this).apply {
            name = "ScrollingParent2"
            addView(scrollingChild2)
            addView(view6)
            addView(view7)
        }

        val scrollingParent1 = ScrollingParentFrameLayout(this).apply {
            name = "ScrollingParent1"
            addView(viewGroup1)
            addView(viewGroup2)
            addView(scrollingParent2)
        }

        setContentView(scrollingParent1)    }
}