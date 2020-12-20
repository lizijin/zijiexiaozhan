package com.peter.viewgrouptutorial

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class ColorStateListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_state_list)
       val button =  findViewById<Button>(R.id.color_state_list_button)
        button.currentTextColor
       val colorStateList =  button.textColors

        button.setOnClickListener{
            v: View? ->
            println(colorStateList.getColorForState(intArrayOf(android.R.attr.state_pressed,android.R.attr.state_enabled), Color.YELLOW))

        }
    }
}