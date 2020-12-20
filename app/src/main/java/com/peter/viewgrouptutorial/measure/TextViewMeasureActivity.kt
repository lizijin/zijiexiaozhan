package com.peter.viewgrouptutorial.measure

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.peter.viewgrouptutorial.R

class TextViewMeasureActivity : AppCompatActivity() {
    lateinit var linearLayout: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_view_measure)
        linearLayout = findViewById(R.id.root_linear_layout)
    }

    //1. without text
    fun measureZero(view: View) {
        val view = TextView(this)
        val widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        val heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        println("zijiexiaozhan textView widthMeasureSpec:$widthMeasureSpec heightMeasureSpec:$heightMeasureSpec")
        view.measure(
            widthMeasureSpec,
            heightMeasureSpec
        )
        println("zijiexiaozhan textView result measuredWidth: ${view.measuredWidth}  measuredHeight: ${view.measuredHeight}")
    }

    //2. with text
    fun measureOne(view: View) {
        val view = TextView(this)
        view.text = "Hello world"
        val widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        val heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        println("zijiexiaozhan textView widthMeasureSpec:$widthMeasureSpec heightMeasureSpec:$heightMeasureSpec")
        view.measure(
            widthMeasureSpec,
            heightMeasureSpec
        )
        println("zijiexiaozhan textView result measuredWidth: ${view.measuredWidth}  measuredHeight: ${view.measuredHeight}")

        val view2 = TextView(this)

        //长文本  宽度将会突破屏幕宽度
        view2.text =
            "Hello world Hello world Hello world Hello world Hello world Hello world Hello world Hello world Hello world Hello world Hello world Hello world"
        view2.measure(
            widthMeasureSpec,
            heightMeasureSpec
        )
        println("zijiexiaozhan  长文本 textView result measuredWidth: ${view2.measuredWidth}  measuredHeight: ${view2.measuredHeight}")




    }

    fun measureTwo(view: View) {
        val textView3 = TextView(this)
        textView3.text =
            "Hello world Hello world Hello world Hello world Hello world Hello world Hello world Hello world Hello world Hello world Hello world Hello world"

        linearLayout.addView(textView3)
        textView3.post {
            println("zijiexiaozhan  长文本 textView result measuredWidth: ${textView3.measuredWidth}  measuredHeight: ${textView3.measuredHeight}")
        }
    }
    fun measureThree(view: View) {}
}