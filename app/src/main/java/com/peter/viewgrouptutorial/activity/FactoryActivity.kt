package com.peter.viewgrouptutorial.activity

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.peter.viewgrouptutorial.R

class FactoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val layoutInflater = LayoutInflater.from(this)
        layoutInflater.factory2 = object : LayoutInflater.Factory2 {
            override fun onCreateView(parent: View?,
                                      name: String,
                                      context: Context,
                                      attrs: AttributeSet
            ): View? {
                if (name == "TextView") {
                    return RedTextView(context, attrs)
                }
                return delegate.createView(parent,name,context,attrs)
            }

            override fun onCreateView(name: String,
                                      context: Context,
                                      attrs: AttributeSet): View? {
                return onCreateView(null, name, context, attrs)
            }
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_factory)
        findViewById<Button>(R.id.button).setOnClickListener {
            (it as Button).text = it.javaClass.name
        }

    }
}