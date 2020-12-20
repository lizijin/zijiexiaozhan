package com.peter.viewgrouptutorial.dispatchevent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.peter.viewgrouptutorial.R

class TouchThreeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_touch_three)
        //vp3 dispatchTouchEvent 直接return true。其他都不分发事件

        val view1 = MyView(this)
        val view2 = MyView(this)
        val view3 = MyView(this)
        val vp2 = MyFrameLayout(this)
        vp2.addView(view1)
        vp2.addView(view2)
        vp2.addView(view3)


        val view4 = MyView(this)
        val view5 = MyView(this)
        val view6 = MyView(this)

        val vp3 = MyFrameLayout(this)
        vp3.addView(view4)
        vp3.addView(view5)
        vp3.addView(view6)

        val view7 = MyView(this)
        val view8 = MyView(this)
        val view9 = MyView(this)

        val vp4 = MyFrameLayout(this)
        vp4.addView(view7)
        vp4.addView(view8)
        vp4.addView(view9)

        val vp1 = MyFrameLayout(this)
        vp1.addView(vp2)
        vp1.addView(vp3)
        vp1.addView(vp4)

        setContentView(vp1)
        view1.name = "view1"
        view2.name = "view2"
        view3.name = "view3"
        view4.name = "view4"
        view5.name = "view5"
        view6.name = "view6"
        view7.name = "view7"
        view8.name = "view8"
        view9.name = "view9"
        vp1.name ="vp1"
        vp2.name ="vp2"
        vp3.name ="vp3"
        vp4.name ="vp4"
        vp3.mDispatchValueSuper = false
    }
}