package com.peter.viewgrouptutorial.measure

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout

class MeasureLinearLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    var name=""
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        println("zijiexiaozhan $name onMeasure widthSpec ${MeasureSpec.getMode(widthMeasureSpec)} ${MeasureSpec.getSize(widthMeasureSpec)} heightSpec ${MeasureSpec.getMode(heightMeasureSpec)} ${MeasureSpec.getSize(heightMeasureSpec)} ")
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        println("zijiexiaozhan $name zonLayout")
    }
}