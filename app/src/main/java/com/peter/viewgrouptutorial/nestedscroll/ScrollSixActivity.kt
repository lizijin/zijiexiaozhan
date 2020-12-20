package com.peter.viewgrouptutorial.nestedscroll

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.peter.viewgrouptutorial.R

class ScrollSixActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling_five)
        findViewById<MyScrollingViewMixinFrameLayout>(R.id.inner_scroll_view).setNestedScrollingEnable(false)

//        var outerScroll = findViewById<MyScrollView>(R.id.outerScroll)
//        outerScroll.name = "Outer scroll"
//        outerScroll.isNestedScrollingEnabled = false
//        var innerScroll = findViewById<MyScrollView>(R.id.innerScroll)
//        innerScroll.name = "Inner scroll"
//        innerScroll.isNestedScrollingEnabled = false

    }
}