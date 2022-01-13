package com.peter.viewgrouptutorial.activity

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class RedTextView : AppCompatTextView {
    constructor(context: Context) : super(context) { initialize() }

    constructor(context: Context, attrs: AttributeSet?) :
            super(context, attrs) { initialize() }

    constructor(context: Context, attr: AttributeSet?, defStyleAttr: Int) :
            super(context, attr, defStyleAttr) { initialize() }

    private fun initialize() { setBackgroundColor(Color.RED) }
}