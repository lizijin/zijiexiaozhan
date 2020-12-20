package com.peter.viewgrouptutorial

import android.animation.Animator
import android.animation.LayoutTransition
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get

class LayoutTransitionActivity : AppCompatActivity() {
    lateinit var animateLayout: LinearLayout
    lateinit var noAnimateLayout: LinearLayout
    var i = 0
    var mVisible =true
    lateinit var showOrHideButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_transition)
        animateLayout = findViewById(R.id.animate_layout)
//        animateLayout.layoutTransition.disableTransitionType(LayoutTransition.APPEARING)
        noAnimateLayout = findViewById(R.id.no_animate_layout)
//        animateLayout.layoutTransition =object :LayoutTransition(){
//            override fun getAnimator(transitionType: Int): Animator? {
//
//                when (transitionType) {
//                    CHANGE_APPEARING -> return super.getAnimator(transitionType)
//                    CHANGE_DISAPPEARING -> return super.getAnimator(transitionType)
//                    CHANGING -> return super.getAnimator(transitionType)
//                    APPEARING -> return   ObjectAnimator.ofFloat(null, "translationX", 100f, 10f);
//                    DISAPPEARING -> return super.getAnimator(transitionType)
//                }
//                // shouldn't reach here
//                return null
//            }
//        }
       var layoutTransition =  animateLayout.layoutTransition
        layoutTransition.setAnimator(LayoutTransition.APPEARING,ObjectAnimator.ofFloat(null, "translationX", 100f, 10f))
//       var filed =  LayoutTransition::class.java.getDeclaredField("defaultFadeIn")
//        filed.isAccessible = true
//        filed.set(null, ObjectAnimator.ofFloat(null, "translationX", 100f, 10f))

        showOrHideButton = findViewById(R.id.show_hide_button)
    }

    fun addView(view: View) {

        animateLayout.addView(TextView(this).apply {
//            this.layoutParams.height = 100
            text = "Hello Text $i"
        })
        noAnimateLayout.addView(TextView(this).apply {
//            this.layoutParams.height = 100
            text = "Hello Text $i"
        })

        if (animateLayout.childCount > 5) {
            showOrHideButton.visibility = View.VISIBLE
        }

    }

    fun removeView(view: View) {
        animateLayout.removeViewAt(animateLayout.childCount - 1)
        noAnimateLayout.removeViewAt(noAnimateLayout.childCount - 1)
        if (animateLayout.childCount <= 5) {
            showOrHideButton.visibility = View.GONE
        }
    }

    fun showOrHide(view: View) {
        if (mVisible) {
            for(i in 0 until animateLayout.childCount){
                animateLayout[i].visibility =View.INVISIBLE
            }
            for(i in 0 until noAnimateLayout.childCount){
                noAnimateLayout[i].visibility =View.INVISIBLE
            }
        } else {
            for(i in 0 until animateLayout.childCount){
                animateLayout[i].visibility =View.VISIBLE
            }
            for(i in 0 until noAnimateLayout.childCount){
                noAnimateLayout[i].visibility =View.VISIBLE
            }
        }
        mVisible =!mVisible
    }
}