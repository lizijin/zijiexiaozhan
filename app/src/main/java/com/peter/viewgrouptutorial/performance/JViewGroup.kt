package com.peter.viewgrouptutorial.performance

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout

class JViewGroup @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    init {
        println("inflatetest J init")
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        println("inflatetest J onFinishInflate")

    }

    override fun addView(child: View?, params: ViewGroup.LayoutParams?) {
        println("inflatetest J addView")

        super.addView(child, params)
    }

    override fun requestLayout() {
        println("inflatetest J requestLayout")

        super.requestLayout()
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        println("inflatetest J onLayout")

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        println("inflatetest J onMeasure")
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        println("inflatetest J onDraw")
    }

    override fun dispatchDraw(canvas: Canvas?) {
        println("inflatetest J dispatchDraw")

        super.dispatchDraw(canvas)

    }
}