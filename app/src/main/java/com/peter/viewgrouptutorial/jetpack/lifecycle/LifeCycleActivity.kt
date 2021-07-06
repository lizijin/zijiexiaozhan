package com.peter.viewgrouptutorial.jetpack.lifecycle

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.lifecycleScope
import com.peter.viewgrouptutorial.R
import kotlinx.coroutines.*

class LifeCycleActivity : AppCompatActivity() {
    private val myObserver = MyObserver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life_cycle)
        println("lifecycle life onCreate")

        lifecycle.addObserver(myObserver)


        lifecycleScope.launchWhenResumed {
            Toast.makeText(
                this@LifeCycleActivity,
                "string" + "${Thread.currentThread().name}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onStart() {
        super.onStart()
        println("lifecycle life onStart")

    }

    override fun onResume() {
        super.onResume()
        println("lifecycle life onResume")

    }

    override fun onPause() {
        super.onPause()
        println("lifecycle life onPause")

    }

    override fun onStop() {
        super.onStop()
        println("lifecycle life onStop")

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        println("lifecycle life onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        println("lifecycle life onRestoreInstanceState")

    }

    override fun onDestroy() {
        super.onDestroy()
        println("lifecycle life onDestroy")

        lifecycle.removeObserver(myObserver)
    }

    inner class MyObserver : LifecycleObserver {

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        fun connectListener() {
            println("OnLifecycleEvent ON_RESUME")
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        fun disconnectListener() {
            println("OnLifecycleEvent ON_PAUSE")

        }
    }

    fun clickButton2(view: View) {
//        lifecycleScope.cancel()
        lifecycleScope.launch {
            val string = withContext(Dispatchers.IO) {
                Thread.sleep(1000L)
                "after 10s"
            }
            Toast.makeText(
                this@LifeCycleActivity,
                string + "${Thread.currentThread().name}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}