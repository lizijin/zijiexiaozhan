package com.peter.viewgrouptutorial.performance

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout

class AViewGroup @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    init {
        println("inflatetest A init")
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        println("inflatetest A onFinishInflate")

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        println("inflatetest A onMeasure")
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        println("inflatetest A onDraw")
    }
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        println("inflatetest A onLayout")

    }
    override fun addView(child: View?, params: ViewGroup.LayoutParams?) {
        println("inflatetest A addView")

        super.addView(child, params)
    }

    override fun requestLayout() {
        println("inflatetest A requestLayout")

        super.requestLayout()
    }

    override fun dispatchDraw(canvas: Canvas?) {
        println("inflatetest A dispatchDraw")

        super.dispatchDraw(canvas)

    }
}