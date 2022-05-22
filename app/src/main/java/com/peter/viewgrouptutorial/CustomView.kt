package com.peter.viewgrouptutorial

import android.content.Context
import android.util.AttributeSet
import android.view.View

class CustomView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    init {
        val a = context.obtainStyledAttributes(
            attrs, R.styleable.CustomView, defStyleAttr, 0
        )
        println("jiangbin the indexCount is ${a.indexCount}")
        for (index in 0 until a.indexCount) {
            val valueIndex  = a.getIndex(index)
            println("jiangbin the valueIndex $valueIndex")
            when (valueIndex) {
                R.styleable.CustomView_customView_attr1 -> println("jiangbin the index CustomView_customView_attr1 ${a.getDrawable(valueIndex)}")
                R.styleable.CustomView_customView_attr2 -> println("jiangbin the index CustomView_customView_attr2 ${a.getText(valueIndex)}")
                R.styleable.CustomView_customView_attr3 -> println("jiangbin the index CustomView_customView_attr3")
                R.styleable.CustomView_customView_attr4 -> println("jiangbin the index CustomView_customView_attr4 ${a.getDimension(valueIndex,0.0f)}")
                R.styleable.CustomView_customView_attr5 -> println("jiangbin the index CustomView_customView_attr5")
                R.styleable.CustomView_customView_attr6 -> println("jiangbin the index CustomView_customView_attr6")

            }
        }
        a.recycle()
    }
}