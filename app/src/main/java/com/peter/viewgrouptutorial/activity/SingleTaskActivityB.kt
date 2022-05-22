package com.peter.viewgrouptutorial.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.peter.viewgrouptutorial.R

class SingleTaskActivityB : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_taskb)
    }

    fun jumpToActivity(view: View) {
        startActivity(Intent(this,StandardActivityB::class.java))
    }
}