package com.peter.viewgrouptutorial

import android.R.attr
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.transition.TransitionManager


class TransitionManagerActivity : AppCompatActivity() {
    var _visible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transition_manager)
        val transitionsContainer: ViewGroup =
            findViewById(R.id.transitions_container)
        val text =
            transitionsContainer.findViewById<View>(R.id.text) as TextView
        val button: Button =
            transitionsContainer.findViewById<View>(R.id.button) as Button
        transitionsContainer.viewTreeObserver.addOnPreDrawListener {
            Toast.makeText(this,"onPreDrawListener",Toast.LENGTH_SHORT).show()
            true
        }
        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                TransitionManager.beginDelayedTransition(transitionsContainer)
                _visible = !_visible
                text.visibility = if (!_visible) View.VISIBLE else View.GONE
            }
        })
    }
}