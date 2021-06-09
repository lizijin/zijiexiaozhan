package com.peter.viewgrouptutorial

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class ChoreographerActivity : AppCompatActivity() {
    lateinit var root: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choreogrpaher)
        root = findViewById<View>(R.id.root)
        Handler().looper.setMessageLogging { println("ChoreographerActivity $it") }
        root.postDelayed(object : Runnable {
            override fun run() {
//                root.requestLayout()
                println("ChoreographerActivity  reqeustLayout")
                root.postDelayed(this, 200)
            }
        }, 200)
        root.setOnClickListener {
            startActivity(Intent(this, BringToFrontActivity::class.java))
        }
    }

    override fun onStop() {
        super.onStop()
        root.postDelayed(object : Runnable {
            override fun run() {
                root.requestLayout()
                println("ChoreographerActivity  reqeustLayout")
            }
        }, 2000)
    }
}