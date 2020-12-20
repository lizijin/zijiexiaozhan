package com.peter.viewgrouptutorial.measure

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MeasureFrameLayoutTreeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_measure_tree)
        val view1 = MeasureView(this)
        val view2 = MeasureView(this)
        val view3 = MeasureView(this)
        val vp2 = MeasureFrameLayout(this)
        vp2.addView(view1)
        vp2.addView(view2)
        vp2.addView(view3)
        view3.setOnClickListener {
//            vp2.requestLayout()
            println("zijiexiaozhan onclick")
//            view3.layoutParams.height = 100
            vp2.addView(MeasureView(this).apply { name = "View added" })
        }


        val view4 = MeasureView(this)
        val view5 = MeasureView(this)
        val view6 = MeasureView(this)

        val vp3 = MeasureFrameLayout(this)
        vp3.addView(view4)
        vp3.addView(view5)
        vp3.addView(view6)

        val view7 = MeasureView(this)
        val view8 = MeasureView(this)
        val view9 = MeasureView(this)

        val vp4 = MeasureFrameLayout(this)
        vp4.addView(view7)
        vp4.addView(view8)
        vp4.addView(view9)

        val vp1 = MeasureFrameLayout(this)
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
        vp1.name = "vp1"
        vp2.name = "vp2"
        vp3.name = "vp3"
        vp4.name = "vp4"
    }
}