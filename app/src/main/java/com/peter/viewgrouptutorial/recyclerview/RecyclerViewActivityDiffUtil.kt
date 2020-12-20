package com.peter.viewgrouptutorial.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.peter.viewgrouptutorial.R
import java.util.*

class RecyclerViewActivityDiffUtil : AppCompatActivity() {
    private lateinit var mRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_diff_util)
        mRecyclerView = findViewById(R.id.recyclerview)
        mRecyclerView.setHasFixedSize(true)
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

    inner class MyAdapter(val mStrings: MutableList<String>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        fun update(newStrings: List<String>) {
            val diffcb = DiffCb(mStrings, newStrings)
            val result = DiffUtil.calculateDiff(diffcb)
            this.mStrings.clear()
            this.mStrings.addAll(newStrings);
            result.dispatchUpdatesTo(this)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            println("RecyclerView diff onCreateViewHolder ")
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_item, parent, false)
            return object : RecyclerView.ViewHolder(view) {}
        }

        override fun getItemCount(): Int {
            return mStrings.size
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            println("RecyclerView diff onBindViewHolder $position ")
            val textView = holder.itemView as TextView
            textView.layoutParams.height = (resources.displayMetrics.density * 100).toInt()
            textView.text = mStrings[position]
        }

        override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
            println("RecyclerView diff 发生回收 " + (holder.itemView as TextView).text)
            super.onViewRecycled(holder)
        }

    }

    inner class DiffCb(val mOldStrings: List<String>, val mNewStrings: List<String>) :
        DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
//            TODO("Not yet implemented")
            println("areItemsTheSame oldItemPosition $oldItemPosition newItemPosition $newItemPosition")
            return true
        }

        override fun getOldListSize(): Int {
            return mOldStrings.size;
        }

        override fun getNewListSize(): Int {
            return mNewStrings.size
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            println("areContentsTheSame oldItemPosition $oldItemPosition newItemPosition $newItemPosition")

            return mOldStrings[oldItemPosition] == mNewStrings[newItemPosition]
        }

    }

    fun scroll120(view: View) {
        mRecyclerView.scrollBy(0, (resources.displayMetrics.density * 120).toInt())
    }

    fun scroll60(view: View) {
        mRecyclerView.scrollBy(0, (resources.displayMetrics.density * 60).toInt())

    }
var index =0
    fun scroll40(view: View) {
        val list: MutableList<String> =
            ArrayList()
        list.add("item 0")
        list.add("item 1")
        list.add("item 2")
        list.add("item 3")
        list.add("item 4")
        list.add("item 5")
        list.add("item 7")


        (mRecyclerView.adapter as MyAdapter).update(list)
//        mRecyclerView.scrollBy(0, (resources.displayMetrics.density * 40).toInt())

    }
}