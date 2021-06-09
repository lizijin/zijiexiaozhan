package com.peter.viewgrouptutorial.performance

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.peter.viewgrouptutorial.R

class LayoutInflaterActivity : AppCompatActivity() {
    lateinit var mLinearLayoutRoot:LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_inflater)
        mLinearLayoutRoot = findViewById(R.id.frame_layout_root)
    }
    // root null, attach true
    fun case1(view: View){
        val itemText = layoutInflater.inflate(R.layout.item_text,null,true)
        println("itemText $itemText")
        (itemText as TextView).text = "This is case1"
        mLinearLayoutRoot.removeAllViews()
        mLinearLayoutRoot.addView(itemText)
    }
    // root null, attach false

    fun case2(view: View) {
        val itemText = layoutInflater.inflate(R.layout.item_text,null,false)
        println("itemText $itemText")
        (itemText as TextView).text = "This is case2"
        mLinearLayoutRoot.removeAllViews()
        mLinearLayoutRoot.addView(itemText)
    }

    // root not null, attach true
    fun case3(view: View) {
        mLinearLayoutRoot.removeAllViews()
        val itemText = layoutInflater.inflate(R.layout.item_text,mLinearLayoutRoot,true)
        (itemText.findViewById<TextView>(R.id.textView) ).text = "This is case3"

        println("itemText $itemText")
    }

    // root not null, attach false
    fun case4(view: View) {
        val itemText = layoutInflater.inflate(R.layout.item_text,mLinearLayoutRoot,false)
        println("itemText $itemText")
        (itemText as TextView).text = "This is case4"

        mLinearLayoutRoot.removeAllViews()
        mLinearLayoutRoot.addView(itemText)
    }
}