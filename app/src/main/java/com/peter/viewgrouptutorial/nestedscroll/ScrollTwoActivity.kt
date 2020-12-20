package com.peter.viewgrouptutorial.nestedscroll

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.peter.viewgrouptutorial.R
import com.peter.viewgrouptutorial.dispatchevent.MyScrollView

class ScrollTwoActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll_one)
        var outerScroll = findViewById<MyScrollView>(R.id.outerScroll)
        outerScroll.name = "Outer scroll"
        var innerScroll = findViewById<MyScrollView>(R.id.innerScroll)
        innerScroll.name = "Inner scroll"
        outerScroll.isNestedScrollingEnabled = false
    }
}