package com.peter.viewgrouptutorial

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class WindowAnimationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_window_animation)
    }

    fun translateAnimation(view: View) {
        val subActivity = Intent(this,AnimatedSubActivity::class.java)
        val translateBundle = ActivityOptions.makeCustomAnimation(this,R.anim.slide_in_left,0).toBundle()
        startActivity(subActivity,translateBundle)
    }
    fun scaleAnimation(view: View) {
        val subActivity = Intent(this,AnimatedSubActivity::class.java)
        val scaleBundle = ActivityOptions.makeScaleUpAnimation(view,0,0,view.width,view.height).toBundle()
        startActivity(subActivity,scaleBundle)

    }
}