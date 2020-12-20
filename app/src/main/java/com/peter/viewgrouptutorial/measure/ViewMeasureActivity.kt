package com.peter.viewgrouptutorial.measure

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.peter.viewgrouptutorial.R

class ViewMeasureActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_measure)
    }

    fun measureZero(view: View) {
        val view = View(this)
        val widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        val heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        println("zijiexiaozhan widthMeasureSpec:$widthMeasureSpec heightMeasureSpec:$heightMeasureSpec")
        view.measure(
            widthMeasureSpec,
            heightMeasureSpec
        )
        println("zijiexiaozhan result measuredWidth: ${view.measuredWidth}  measuredHeight: ${view.measuredHeight}")

    }

    fun measureOne(view: View) {
        val view = View(this)
        val widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(100, View.MeasureSpec.UNSPECIFIED)
        val heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(100, View.MeasureSpec.UNSPECIFIED)
        println("zijiexiaozhan widthMeasureSpec:$widthMeasureSpec heightMeasureSpec:$heightMeasureSpec")
        view.measure(
            widthMeasureSpec,
            heightMeasureSpec
        )
        println("zijiexiaozhan result measuredWidth: ${view.measuredWidth}  measuredHeight: ${view.measuredHeight}")
    }

    fun measureTwo(view: View) {
        val view = View(this)
        val widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(100, View.MeasureSpec.AT_MOST)
        val heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(100, View.MeasureSpec.AT_MOST)
        println("zijiexiaozhan widthMeasureSpec:$widthMeasureSpec heightMeasureSpec:$heightMeasureSpec")
        view.measure(
            widthMeasureSpec,
            heightMeasureSpec
        )
        println("zijiexiaozhan result measuredWidth: ${view.measuredWidth}  measuredHeight: ${view.measuredHeight}")
    }

    fun measureThree(view: View) {
        val view = View(this)
        val widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(100, View.MeasureSpec.EXACTLY)
        val heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(100, View.MeasureSpec.EXACTLY)
        println("zijiexiaozhan widthMeasureSpec:$widthMeasureSpec heightMeasureSpec:$heightMeasureSpec")
        view.measure(
            widthMeasureSpec,
            heightMeasureSpec
        )
        println("zijiexiaozhan result measuredWidth: ${view.measuredWidth}  measuredHeight: ${view.measuredHeight}")
    }
}