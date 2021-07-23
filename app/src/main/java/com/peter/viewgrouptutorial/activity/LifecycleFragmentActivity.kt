package com.peter.viewgrouptutorial.activity

import android.content.Context
import android.location.LocationManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.peter.viewgrouptutorial.R

class LifecycleFragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("lifecycle activity onCreate")
        setContentView(R.layout.activity_lifecycle_fragment)
    }

    override fun onStart() {
        super.onStart()
        println("lifecycle activity onStart")

        lifecycle.addObserver(object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
            fun onCreateEvent() {
                println("zijiex onCreateEvent")
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_START)
            fun onStartEvent() {
                println("zijiex onStartEvent")
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
            fun onResumeEvent() {
                println("zijiex onResumeEvent")
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
            fun onPauseEvent() {
                println("zijiex onPauseEvent")
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
            fun onStopEvent() {
                println("zijiex onStopEvent")
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun onDestroyEvent() {
                println("zijiex onDestroyEvent")

                lifecycle.removeObserver(this)
            }

        })
    }

    override fun onResume() {
        super.onResume()
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
//        locationManager.requestLocationUpdates(provider, 5000, 1, locationListener);
        println("lifecycle activity onResume")
    }

    override fun onPause() {
        super.onPause()
        println("lifecycle activity onPause")
    }

    override fun onStop() {
        super.onStop()
        println("lifecycle activity onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("lifecycle activity onDestroy")

    }
}