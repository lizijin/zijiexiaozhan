package com.peter.viewgrouptutorial.measure

import android.content.Context
import android.util.AttributeSet
import android.view.View

class MeasureView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    var name = ""
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        println(
            "zijiexiaozhan $name onMeasure widthSpec ${MeasureSpec.getMode(widthMeasureSpec) } ${MeasureSpec.getSize(
                widthMeasureSpec
            )} heightSpec ${MeasureSpec.getMode(heightMeasureSpec) } ${MeasureSpec.getSize(
                heightMeasureSpec
            )} "
        )
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        println("zijiexiaozhan $name zonLayout ")
    }
}