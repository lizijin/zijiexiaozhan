package com.peter.viewgrouptutorial.textview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.peter.viewgrouptutorial.R

class AutoSizeTextViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_size_text_view)
    }

    var count = 0
    fun appendText(view: View) {
        view as TextView
        var text = "Hello World"
        repeat(count){
            text +=" Hello word ${count}"
        }
        count++
        view.text = text
        Toast.makeText(this,""+(view.textSize/resources.displayMetrics.density),Toast.LENGTH_SHORT).show()
    }
}