package com.peter.viewgrouptutorial.dispatchevent

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class MyScrollViewActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_my_scroll_view)
        val scrollView = MyScrollView(this)
        val linearLayout: LinearLayout = LinearLayout(this)
        linearLayout.orientation = LinearLayout.VERTICAL
        scrollView.addView(linearLayout)
        repeat(50) {
            val myView = MyView(this)
            myView.setOnLongClickListener {
                Toast.makeText(this, "LongClick", Toast.LENGTH_SHORT).show()
                true
            }
            myView.name = "myView$it"
            myView.mOnTouchValue = true
            myView.setBackgroundColor(if (it % 2 == 0) Color.RED else Color.GREEN)
            linearLayout.addView(myView, ViewGroup.LayoutParams.MATCH_PARENT, 100)
        }
        setContentView(scrollView)
    }
}