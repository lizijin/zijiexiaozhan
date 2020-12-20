package com.peter.viewgrouptutorial.measure

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout

class MeasureRelativeLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {
    var name = ""
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        println(
            "zijiexiaozhan RL $name onMeasure widthSpec ${MeasureSpec.getMode(widthMeasureSpec) } ${MeasureSpec.getSize(
                widthMeasureSpec
            )} heightSpec ${MeasureSpec.getMode(heightMeasureSpec)} ${MeasureSpec.getSize(
                heightMeasureSpec
            )} "
        )
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
}