package com.peter.viewgrouptutorial.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.peter.viewgrouptutorial.R

class SingleInstanceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_task)
    }
}