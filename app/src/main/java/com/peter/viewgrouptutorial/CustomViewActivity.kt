package com.peter.viewgrouptutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Selection
import android.text.SpannableString
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_transition_manager.*

class CustomViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_view)
        val textView = findViewById<TextView>(R.id.text_view_custom)
//        Selection.setSelection(textView.text,0,)
        val text = SpannableString("Hello World ")
        Selection.setSelection(text,2)
        textView.text = text
    }
}