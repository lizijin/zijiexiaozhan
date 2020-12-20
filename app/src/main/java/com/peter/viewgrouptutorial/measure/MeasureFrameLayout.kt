package com.peter.viewgrouptutorial.measure

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

class MeasureFrameLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    var name=""
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        name.let {  }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        println("zijiexiaozhan FL $name onMeasure widthSpec ${MeasureSpec.getMode(widthMeasureSpec)} ${MeasureSpec.getSize(widthMeasureSpec)} heightSpec ${MeasureSpec.getMode(heightMeasureSpec)} ${MeasureSpec.getSize(heightMeasureSpec)} ")

    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        println("zijiexiaozhan FL $name onLayout")
    }
}