package com.peter.viewgrouptutorial.ams

import android.app.Service
import android.content.Intent
import android.os.IBinder

class AnrService : Service() {

    override fun onCreate() {
        super.onCreate()
        Thread.sleep(25 * 1000)
        println("AnrService onCreate")
    }
    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}
