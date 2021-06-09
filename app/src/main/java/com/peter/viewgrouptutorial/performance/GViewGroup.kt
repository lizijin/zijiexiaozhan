package com.peter.viewgrouptutorial.performance

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout

class GViewGroup @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    init {
        println("inflatetest G init")
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        println("inflatetest G onFinishInflate")

    }
    override fun addView(child: View?, params: ViewGroup.LayoutParams?) {
        println("inflatetest G addView")

        super.addView(child, params)
    }

    override fun requestLayout() {
        println("inflatetest G requestLayout")

        super.requestLayout()
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        println("inflatetest G onLayout")

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        println("inflatetest G onMeasure")
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        println("inflatetest G onDraw")
    }

    override fun dispatchDraw(canvas: Canvas?) {
        println("inflatetest G dispatchDraw")

        super.dispatchDraw(canvas)

    }
}