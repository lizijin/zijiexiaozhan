package com.peter.viewgrouptutorial.performance

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.peter.viewgrouptutorial.R

class InflateMoreTagActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inflate_more_tag)
        Looper.getMainLooper().setMessageLogging { println("jiangbin "+it) }
        var runnable: Runnable = object : Runnable {
            override fun run() {
                println("jiangbin runnable ")
                Thread.sleep(4000)
                Handler().postDelayed(this, 200)
            }

        }
        Handler().postDelayed(runnable, 200)

        findViewById<EditText>(R.id.edit_text1).also {
            it.setText("tag " + it.getTag(R.id.tag1))
        }

        findViewById<EditText>(R.id.edit_text2).also {
            it.setText("tag " + it.getTag(R.id.tag1))
        }
    }
}