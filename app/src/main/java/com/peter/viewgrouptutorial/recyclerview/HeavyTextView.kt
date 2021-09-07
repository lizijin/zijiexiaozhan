package com.peter.viewgrouptutorial.recyclerview

import android.content.Context
import android.util.AttributeSet

class HeavyTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : androidx.appcompat.widget.AppCompatTextView(context, attrs, defStyleAttr) {
    init {
        println("heavy view init")
//        Thread.sleep(100L)
    }
}