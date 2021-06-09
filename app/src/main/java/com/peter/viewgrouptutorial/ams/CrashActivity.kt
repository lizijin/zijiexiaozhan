package com.peter.viewgrouptutorial.ams

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.peter.viewgrouptutorial.R

class CrashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crash)
        println("hello ${1 / 0}")
    }
}