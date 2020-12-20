package com.peter.viewgrouptutorial.textview

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.peter.viewgrouptutorial.R
import kotlinx.android.synthetic.main.activity_promise_text_view.*

class PromiseTextViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promise_text_view)
        promise_textview1.setText("品牌直发","三只松鼠")
        promise_textview2.setText("品牌直发",10,"三只松鼠")
    }

    fun changeTextSize(view: View) {
        promise_textview2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12f)
        promise_textview2.setText("品牌直发",40,"三只松鼠三只松鼠三只松鼠三只松鼠三只松鼠三只松鼠三只松鼠三只松鼠三只松鼠三只松鼠三只松鼠")
    }
}