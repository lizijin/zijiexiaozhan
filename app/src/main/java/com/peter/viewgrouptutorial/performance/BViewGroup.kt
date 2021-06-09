package com.peter.viewgrouptutorial.performance

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout

class BViewGroup @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    init {
        println("inflatetest B init")
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        println("inflatetest B onFinishInflate")

    }
    override fun addView(child: View?, params: ViewGroup.LayoutParams?) {
        println("inflatetest B addView")

        super.addView(child, params)
    }
    override fun requestLayout() {
        println("inflatetest B requestLayout")

        super.requestLayout()
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        println("inflatetest B onLayout")
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        println("inflatetest B onMeasure")
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        println("inflatetest B onDraw")
    }
    override fun dispatchDraw(canvas: Canvas?) {
        println("inflatetest B dispatchDraw")

        super.dispatchDraw(canvas)

    }
}