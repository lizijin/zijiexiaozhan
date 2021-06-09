package com.peter.viewgrouptutorial.performance

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.peter.viewgrouptutorial.R

class InflateCustomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inflate_custom)
//        findViewById<View>(R.id.button).setOnClickListener{
//            findViewById<View>(R.id.cview).requestLayout()
//        }
    }

    fun doRequestLayout(view: View) {
        findViewById<View>(R.id.cview).requestLayout()
//        findViewById<View>(R.id.cview).setBackgroundColor(Color.BLUE)

    }
}