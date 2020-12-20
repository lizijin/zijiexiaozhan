package com.peter.viewgrouptutorial.textview

import android.graphics.Rect
import android.os.Bundle
import android.text.method.TransformationMethod
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.peter.viewgrouptutorial.R

class TransformationTextViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transformation_text_view)
        val textView: TextView = findViewById(R.id.text_view)
        textView.transformationMethod = object :TransformationMethod{
            override fun onFocusChanged(
                view: View?,
                sourceText: CharSequence?,
                focused: Boolean,
                direction: Int,
                previouslyFocusedRect: Rect?
            ) {
            }

            override fun getTransformation(source: CharSequence?, view: View?): CharSequence {
                return  "$source transformation"
            }

        }
    }
}