package com.peter.viewgrouptutorial.offsetproblem

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.widget.LinearLayout

class OffsetLinearLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    override fun onDraw(canvas: Canvas?) {
        println("OffsetLinearLayout layout onDraw")
        super.onDraw(canvas)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        println("OffsetLinearLayout layout onMeasure")
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        println("OffsetLinearLayout layout onLayout")
        super.onLayout(changed, left, top, right, bottom)
    }
}