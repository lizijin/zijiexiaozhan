package com.peter.viewgrouptutorial

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.peter.viewgrouptutorial.constraintlayout.ConstraintLayoutActivity
import com.peter.viewgrouptutorial.coordinatorlayout.CoordinatorLayoutActivity
import com.peter.viewgrouptutorial.dispatchevent.MyScrollViewActivity
import com.peter.viewgrouptutorial.stickyheader.MyRecyclerViewActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(this,"refreshRate ${windowManager.defaultDisplay.refreshRate}",Toast.LENGTH_SHORT).show()
    }

    fun goBringToFront(view: View) {
        startActivity(Intent(this, BringToFrontActivity::class.java))
    }

    fun clipChildren(view: View) {
        startActivity(Intent(this, ClipChildrenActivity::class.java))

    }

    fun onVisibilityChanged(view: View) {
        startActivity(Intent(this, VisibilityChangedActivity::class.java))
    }

    fun goScrollActivity(view: View) {
        startActivity(Intent(this, ScrollActivity::class.java))

    }

    fun goLayoutTransition(view: View) {
        startActivity(Intent(this, LayoutTransitionActivity::class.java))

    }

    fun goLayoutAnimation(view: View) {
        startActivity(Intent(this, LayoutAnimationActivity::class.java))

    }

    fun goScrollViewActivity(view: View) {
        startActivity(Intent(this, ScrollViewActivity::class.java))

    }

    fun goCoordinateLayoutActivity(view: View) {
        startActivity(Intent(this, CoordinateLayoutActivity::class.java))

    }

    fun goCoordinateScrollViewActivity(view: View) {
        startActivity(Intent(this, CoordinateScrollViewActivity::class.java))

    }

    fun goCoordinatorConsumeActivity(view: View) {
        startActivity(Intent(this, CoordinateConsumeActivity::class.java))

    }

    fun goColorStateListActivity(view: View) {
        startActivity(Intent(this, ColorStateListActivity::class.java))

    }

    fun goDialogActivity(view: View) {
        startActivity(Intent(this, DialogActivity::class.java))

    }

    fun goBottomSheet(view: View) {
        startActivity(Intent(this, BottomSheetActivity::class.java))

    }

    fun goBottomNavigation(view: View) {
        startActivity(Intent(this, BottomNavigation::class.java))

    }

    fun goTransition(view: View) {
        startActivity(Intent(this, TransitionManagerActivity::class.java))

    }


    fun goWindowAnimation(view: View) {
        startActivity(Intent(this,WindowAnimationActivity::class.java))
    }

    fun goSceneActivity(view: View) {
        startActivity(Intent(this,SceneActivity::class.java))

    }

    //演示利用ActivityOptions实现Activity之间的过度动画
    fun gotoActivityOptionsActivity(view: View) {
        startActivity(Intent(this,ActivityOptionsActivity::class.java))

    }

    fun gotoGhostActivity(view: View) {
        startActivity(Intent(this,GhostActivity::class.java))

    }

    fun gotoListViewActivity(view: View) {
//        startActivity(Intent(this,PhotoListActivity::class.java))

    }

    fun gotoListViewScene(view: View) {
//        startActivity(Intent(this,ListViewSceneActivity::class.java))

    }

    fun gotoJankActivity(view: View) {
        startActivity(Intent(this,JankActivity::class.java))

    }

    fun gotoExpandListViewActivity(view: View) {
//        startActivity(Intent(this,ExpandableListViewActivity::class.java))

    }


    fun gotoConstraintLayoutActivity(view: View) {
        startActivity(Intent(this,
            ConstraintLayoutActivity::class.java))
    }

    fun gotoScrollViewActivity(view: View) {
        startActivity(Intent(this,
            MyScrollViewActivity::class.java))
    }


    fun gotostickyActivity(view: View) {
        startActivity(Intent(this,
            MyRecyclerViewActivity::class.java))
    }



    fun gotoCoordinator(view: View) {
        startActivity(Intent(this,
            CoordinatorLayoutActivity::class.java))
    }

    fun gotoRemoveViews(view: View) {
        startActivity(Intent(this,
            RemoveViewsActivity::class.java))
    }
}