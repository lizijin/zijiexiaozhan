package com.peter.viewgrouptutorial.dispatchevent

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout

/**
 * 场景四、vp2 Behavior onIntercept返回true onTouch返回true
 */
class CoordinatorLayoutEventFourActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val coordinatorLayout: CoordinatorLayout = MyCoordinatorLayout(this)
//        coordinatorLayout.setOnTouchListener { v, event ->
//            Log.d(
//                LogTag.tag,
//                "MyCoordinatorLayout onTouchListener ${MotionEvent.actionToString(event.action)}"
//            )
//            false
//        }
//        coordinatorLayout.setOnClickListener { v ->
//            Log.d(
//                LogTag.tag,
//                "MyCoordinatorLayout setOnClickListener }"
//            )
//            false
//        }
        //vp1 vp2 vp3 默认不拦截事件 默认不分发事件
        val vp1 = MyFrameLayout(this)
        vp1.name = "VP1"
        val vp1Params = CoordinatorLayout.LayoutParams(
            CoordinatorLayout.LayoutParams.MATCH_PARENT,
            CoordinatorLayout.LayoutParams.MATCH_PARENT
        )
        vp1Params.behavior = MyBehavior().apply {
            name = "VP1 Behavior"
        }
        coordinatorLayout.addView(vp1, vp1Params)
        val vp2 = MyFrameLayout(this)
        vp2.name = "VP2"
        val vp2Params = CoordinatorLayout.LayoutParams(
            CoordinatorLayout.LayoutParams.MATCH_PARENT,
            CoordinatorLayout.LayoutParams.MATCH_PARENT
        )
        vp2Params.behavior = MyBehavior().apply {
            name = "VP2 Behavior"
            interceptValue = true
            touchValue = true
        }
        coordinatorLayout.addView(vp2, vp2Params)
        val vp3 = MyFrameLayout(this)
        vp3.name = "VP3"
        val vp3Params = CoordinatorLayout.LayoutParams(
            CoordinatorLayout.LayoutParams.MATCH_PARENT,
            CoordinatorLayout.LayoutParams.MATCH_PARENT
        )
        vp3Params.behavior = MyBehavior().apply {
            name = "VP3 Behavior"
        }
        coordinatorLayout.addView(vp3, vp3Params)
        vp1.addView(MyView(this).apply { name = "view1" })
        vp1.addView(MyView(this).apply { name = "view2" })
        vp1.addView(MyView(this).apply { name = "view3" })

        vp2.addView(MyView(this).apply { name = "view4" })
        vp2.addView(MyView(this).apply { name = "view5" })
        vp2.addView(MyView(this).apply { name = "view6" })

        val vp4 = MyFrameLayout(this).apply { name = "VP4" }

        vp3.addView(vp4)

        vp4.addView(MyView(this).apply { name = "view7" })
        vp4.addView(MyView(this).apply { name = "view8" })
        vp4.addView(MyView(this).apply { name = "view9" })
        setContentView(coordinatorLayout)
    }
}