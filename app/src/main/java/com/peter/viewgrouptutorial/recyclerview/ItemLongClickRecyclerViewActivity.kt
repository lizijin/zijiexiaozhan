package com.peter.viewgrouptutorial.recyclerview

import android.os.Bundle
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.peter.viewgrouptutorial.R
import java.util.*

/**
 * ItemLongClick RecyclerView
 */
class ItemLongClickRecyclerViewActivity : AppCompatActivity() {
    private lateinit var mRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_item_long_click)
        mRecyclerView = findViewById(R.id.recyclerview)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager =
            LinearLayoutManager(this).apply {
                orientation = LinearLayoutManager.VERTICAL
                isItemPrefetchEnabled = false
            }
        val list: MutableList<String> =
            ArrayList()
        repeat(100) {
            list.add("item $it")
        }
        mRecyclerView.adapter = MyAdapter(list)
        mRecyclerView.addOnItemTouchListener(MyItemTouchListener())
    }

    inner class MyItemTouchListener : RecyclerView.OnItemTouchListener {
        val gestureDetector = GestureDetector(this@ItemLongClickRecyclerViewActivity,
            object : GestureDetector.SimpleOnGestureListener() {
                override fun onLongPress(e: MotionEvent?) {
                    super.onLongPress(e)
                    Toast.makeText(
                        this@ItemLongClickRecyclerViewActivity,
                        " onLongPress ",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        )

        override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
            gestureDetector.onTouchEvent(e)
        }

        override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
            gestureDetector.onTouchEvent(e)
            return false
        }

        override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
        }

    }

    inner class MyAdapter(val mStrings: MutableList<String>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            println("RecyclerView 测试增加Item时动画 onCreateViewHolder ")
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_item, parent, false)
            return object : RecyclerView.ViewHolder(view) {}
        }

        override fun getItemCount(): Int {
            return mStrings.size
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            println("RecyclerView 测试增加Item时动画 onBindViewHolder $position ${mStrings[position]}")
            val textView = holder.itemView as TextView
            textView.layoutParams.height = (resources.displayMetrics.density * 100).toInt()
            textView.text = mStrings[position]
            textView.setOnClickListener {
                Toast.makeText(
                    this@ItemLongClickRecyclerViewActivity,
                    "on Click ",
                    Toast.LENGTH_SHORT
                ).show()
            }
            textView.setOnLongClickListener { v ->
                Toast.makeText(
                    this@ItemLongClickRecyclerViewActivity,
                    "setOnLongClickListener ",
                    Toast.LENGTH_SHORT
                ).show()
                true
            }
        }

        override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
            println("RecyclerView 测试增加Item时动画 发生回收 " + (holder.itemView as TextView).text)
            super.onViewRecycled(holder)
        }
    }

}