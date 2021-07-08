package com.peter.viewgrouptutorial.jetpack.lifecycle

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.*
import com.peter.viewgrouptutorial.R
import kotlinx.coroutines.*

class LifeCycleActivity : AppCompatActivity() {
    private val myObserver = MyObserver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life_cycle)
        println("lifecycle life onCreate")



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
        lifecycle.addObserver(myObserver)

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

    inner class MyObserver : LifecycleEventObserver {
        override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
            println("MyObserver onStateChanged $event")
        }

    }
    private val PERMISSIONS_STORAGE = arrayOf(
        "android.permission.READ_EXTERNAL_STORAGE",
        "android.permission.WRITE_EXTERNAL_STORAGE"
    )
    private val REQUEST_EXTERNAL_STORAGE = 1


    fun clickButton2(view: View) {
//        lifecycleScope.cancel()
//        lifecycleScope.launch {
//            val string = withContext(Dispatchers.IO) {
//                Thread.sleep(1000L)
//                "after 10s"
//            }
//            Toast.makeText(
//                this@LifeCycleActivity,
//                string + "${Thread.currentThread().name}",
//                Toast.LENGTH_SHORT
//            ).show()
//        }
//        Dialog(this).show()
        val result =
            ActivityCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE");
        if (result != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE)
        }
    }
}