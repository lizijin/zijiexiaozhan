package com.peter.viewgrouptutorial.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.peter.viewgrouptutorial.R

class BActivity : AppCompatActivity() {
    private lateinit var mRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_custom_cache)
        mRecyclerView = findViewById(R.id.recyclerview)
        //省略很多RecyclerView的常规操作比如setAdapter和LayoutManager
        mRecyclerView.setViewCacheExtension(object : RecyclerView.ViewCacheExtension() {
            override fun getViewForPositionAndType(
                recycler: RecyclerView.Recycler,
                position: Int,
                type: Int
            ): View? {
                //从AActivity的缓存中拿View，Demo实例，实际业务可以写的更优雅
                if (AActivity.sCustomViewCaches.size != 0) {
                    val view = DashboardActivity.sCustomViewCaches.removeFirst()
                    println("custom cache view remove $position $view")
                    if (position == 0) {
                        println("attention $position $view")
                    }
                    return view
                }
                return null
            }
        })
    }
}
