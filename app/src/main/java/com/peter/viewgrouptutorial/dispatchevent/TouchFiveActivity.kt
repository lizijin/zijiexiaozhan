package com.peter.viewgrouptutorial.dispatchevent

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.peter.viewgrouptutorial.R

class TouchFiveActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_touch_two)

        //只有两层 MyFrameLayout 和MyView。myView的区域比较小，而且分发down事件
        val view1 = MyView(this)
        view1.name = "view1"
//        view1.mOnTouchValue = true
        view1.setOnClickListener { Toast.makeText(this,"Onclick",Toast.LENGTH_SHORT).show() }
        val vp1 = MyFrameLayout(this)
        view1.setBackgroundColor(Color.RED)
        vp1.addView(view1, -1, 100)
        setContentView(vp1)
        view1.name = "view1"
        vp1.name = "vp1"

    }
}