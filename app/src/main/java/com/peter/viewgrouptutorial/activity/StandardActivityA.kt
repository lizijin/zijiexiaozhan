package com.peter.viewgrouptutorial.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.peter.viewgrouptutorial.R

class StandardActivityA : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_standard)
    }

    fun gotoAnotherSingleTaskActivity(view: View) {
        startActivity(Intent(this, SingleTaskActivityB::class.java))

    }
}