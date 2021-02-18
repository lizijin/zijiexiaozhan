package com.peter.viewgrouptutorial.offsetproblem

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton

class OffsetButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatButton(context, attrs, defStyleAttr) {
    override fun onDraw(canvas: Canvas?) {
        println("OffsetButton onDraw")
        super.onDraw(canvas)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        println("OffsetButton onMeasure")
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        println("OffsetButton onLayout")
        super.onLayout(changed, left, top, right, bottom)
    }

    override fun offsetTopAndBottom(offset: Int) {
        println("OffsetButton offsetTopAndBottom")

        super.offsetTopAndBottom(offset)
    }
}