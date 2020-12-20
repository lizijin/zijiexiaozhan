package com.peter.viewgrouptutorial

import android.os.Bundle
import android.view.View
import android.view.animation.LayoutAnimationController
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class LayoutAnimationActivity : AppCompatActivity() {
    lateinit var layoutAnimationController:LayoutAnimationController
    lateinit var root:LinearLayout
    lateinit var button:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_animation)
        root = findViewById(R.id.linear_layout)
        button = findViewById(R.id.animate_button)
        val animation = TranslateAnimation(0f,100f,0f,100f)
        animation.duration=1000
        layoutAnimationController = LayoutAnimationController(animation)
        layoutAnimationController.order=LayoutAnimationController.ORDER_RANDOM
        root.layoutAnimation = layoutAnimationController
    }

    fun startAnimation(view: View) {
//        root.layoutAnimation = layoutAnimationController
        root.requestLayout()
        val animation = TranslateAnimation(0f,100f,0f,500f)
        animation.fillAfter = true
        animation.fillBefore=true
        animation.duration=5000
        view.startAnimation(animation)
        val animation2 = TranslateAnimation(0f,100f,0f,500f)
        animation2.fillAfter = true
        animation2.fillBefore=false
        animation2.duration=5000
        button.startAnimation(animation2)
//        layoutAnimationController.start()

    }
}