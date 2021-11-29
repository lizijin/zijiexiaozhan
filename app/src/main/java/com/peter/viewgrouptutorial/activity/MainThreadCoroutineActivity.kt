package com.peter.viewgrouptutorial.activity

import android.os.Bundle
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.peter.viewgrouptutorial.R
import java.util.concurrent.Executors

class MainThreadCoroutineActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_thread_coroutine)
        val myRunnable = MyRunnable(object : MyCallBack {
            override fun handle(result: String) {
                println("MainThreadCoroutineActivity in ${Thread.currentThread()}")
                findViewById<FrameLayout>(R.id.root_view).addView(TextView(this@MainThreadCoroutineActivity)
                    .apply {
                        text = result
                    })
            }
        })
        Executors.newFixedThreadPool(3).submit(
            myRunnable
        )

    }

    class MyRunnable(private val callBack: MyCallBack) : Runnable {
        override fun run() {
            Thread.sleep(3000)
            callBack.handle("data from network")
        }

    }

    interface MyCallBack {
        fun handle(result: String)
    }
}