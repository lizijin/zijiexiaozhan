package com.peter.viewgrouptutorial.recyclerview

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.peter.viewgrouptutorial.R
import java.util.*

class RecyclerViewNestedActivity : AppCompatActivity() {

    private lateinit var mRecyclerView: RecyclerView
    private val TYPE_1 = 1
    private val TYPE_2 = 2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_nested)
        mRecyclerView = findViewById(R.id.recyclerView)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.isNestedScrollingEnabled = true
        mRecyclerView.setItemViewCacheSize(0)
        mRecyclerView.layoutManager =
            LinearLayoutManager(this).apply {
                orientation = LinearLayoutManager.VERTICAL
                isItemPrefetchEnabled = false
            }
        val list: MutableList<String> =
            ArrayList()
        repeat(10) {
            list.add("item $it")
        }
        mRecyclerView.adapter = MyAdapter(list)
    }

    inner class MyAdapter(val mStrings: MutableList<String>, val normal: Boolean = false) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return if (viewType == TYPE_1) {
                println("RecyclerViewNestedActivity type1 onCreateViewHolder ")
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_item, parent, false)
                object : RecyclerView.ViewHolder(view) {}
            } else {
                println("RecyclerViewNestedActivity type1 onCreateViewHolder ")
                val rv = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recycler_view_item, parent, false) as RecyclerView

                object : RecyclerView.ViewHolder(rv) {}
            }
        }

        override fun getItemCount(): Int {
            return mStrings.size
        }

        override fun getItemViewType(position: Int): Int {
            if (normal) return TYPE_1;
            if (position == mStrings.size-1) return TYPE_2
            return TYPE_1
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            println("RecyclerViewNestedActivity onBindViewHolder $position ")
            if (holder.itemView is RecyclerView) {
                val rv = holder.itemView as RecyclerView
//                rv.layoutParams =
//                    RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, 400)

//                rv.setHasFixedSize(true)
                rv.layoutManager = LinearLayoutManager(this@RecyclerViewNestedActivity).apply {
                    orientation = LinearLayoutManager.VERTICAL
                    isItemPrefetchEnabled = false
                }
                val list: MutableList<String> =
                    ArrayList()
                repeat(10) {
                    list.add("NestItem $it")
                }
//                rv.layoutParams =
//                    RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, 400)
                rv.adapter = MyAdapter(list, true)
                return
            }
            val textView = holder.itemView as TextView
            textView.layoutParams.height = (resources.displayMetrics.density * 100).toInt()
            textView.text = mStrings[position]
            if (!normal) {
                textView.setTextColor(Color.RED)
            }
        }

        override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
            println("RecyclerView 场景一 发生回收 " + holder.itemView)
            super.onViewRecycled(holder)
        }

    }
}