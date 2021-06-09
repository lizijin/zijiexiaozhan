package com.peter.viewgrouptutorial.performance

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.peter.viewgrouptutorial.R

class InflateCustomView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    init {
        setBackgroundColor(Color.RED)
        LayoutInflater.from(context).inflate(R.layout.item_inflate_test, this)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        println("inflatetest custom onFinishInflate")
    }
}