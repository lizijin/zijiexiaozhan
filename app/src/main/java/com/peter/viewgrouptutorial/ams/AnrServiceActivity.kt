package com.peter.viewgrouptutorial.ams

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.peter.viewgrouptutorial.R

class AnrServiceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anr_service)
        startService(Intent(this, AnrService::class.java))
    }
}