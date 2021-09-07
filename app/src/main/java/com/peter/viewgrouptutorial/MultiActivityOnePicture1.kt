package com.peter.viewgrouptutorial

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MultiActivityOnePicture1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multi_one_picture1)
     println("MultiActivityOnePicture ${findViewById<View>(R.id.jump_button).background} ${R.drawable.splash_logo1}")
    }

    fun jumpNew(view: View) {
        startActivity(Intent(this,MultiActivityOnePicture2::class.java))
    }
}