package com.peter.viewgrouptutorial.performance

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.asynclayoutinflater.view.AsyncLayoutInflater
import com.peter.viewgrouptutorial.R

class AsyncLayoutInflaterActivity : AppCompatActivity() {
    private lateinit var mLayoutContent: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async_layout_inflater)
        mLayoutContent = findViewById(R.id.layout_content)
    }

    fun addViewNormal(view: View) {
        val itemHeaderView = layoutInflater.inflate(R.layout.item_header, mLayoutContent)
//        mLayoutContent.addView(
//            itemHeaderView,
//            LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT
//            )
//        )
//        (itemHeaderView as TextView).text = "inflate normal"
        val startTime = System.currentTimeMillis()
        layoutInflater.inflate(R.layout.item_inflate_test, mLayoutContent)
        val endTime = System.currentTimeMillis()
        println("inflateTest cost ${endTime - startTime}")

    }

    fun addViewAsync(view: View) {
        val asyncLayoutInflater: AsyncLayoutInflater = AsyncLayoutInflater(this)
        asyncLayoutInflater.inflate(R.layout.item_header, mLayoutContent) { view, resid, parent ->
            view as TextView
            view.text = "inflate async"
            mLayoutContent.addView(
                view,
                LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
            )
        }
    }

    fun addMerge(view: View) {}
}