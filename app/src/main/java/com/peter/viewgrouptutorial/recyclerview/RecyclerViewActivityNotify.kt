package com.peter.viewgrouptutorial.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.peter.viewgrouptutorial.R
import java.util.*

class RecyclerViewActivityNotify : AppCompatActivity() {
    private lateinit var mRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_nofity)
        mRecyclerView = findViewById(R.id.recyclerview)
        mRecyclerView.setHasFixedSize(true)
//        mRecyclerView.setItemViewCacheSize(0)
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
    }

    inner class MyAdapter(val mStrings: MutableList<String>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            println("RecyclerView notify onCreateViewHolder ")
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_item, parent, false)
            return object : RecyclerView.ViewHolder(view) {}
        }

        override fun getItemCount(): Int {
            return mStrings.size
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            println("RecyclerView notify onBindViewHolder $position ")
            val textView = holder.itemView as TextView
            textView.layoutParams.height = (resources.displayMetrics.density * 100).toInt()
            textView.text = mStrings[position]
        }

        override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
            println("RecyclerView notify 发生回收 " + (holder.itemView as TextView).text)
            super.onViewRecycled(holder)
        }

    }


    fun callNotifyDataSetChanged(view: View) {
        mRecyclerView.adapter?.notifyDataSetChanged()
    }

    fun callNotifyItemChanged(view: View) {
        (mRecyclerView.adapter as MyAdapter).mStrings[1] = "changed 1"
        mRecyclerView.adapter?.notifyItemChanged(1)

    }

    fun callNotifyItemRangeChanged(view: View) {
        mRecyclerView.adapter?.notifyItemRangeChanged(1, 10)

    }

    fun callNotifyItemMoved(view: View) {
        mRecyclerView.adapter?.notifyItemMoved(1, 2)
    }

    fun callNotifyItemInserted(view: View) {
        mRecyclerView.adapter?.notifyItemInserted(1)

    }

    fun callNotifyItemRangeInserted(view: View) {
        mRecyclerView.adapter?.notifyItemRangeInserted(1, 2)

    }

    fun callNotifyItemRemoved(view: View) {
        mRecyclerView.adapter?.notifyItemRemoved(1)

    }

    fun callNotifyItemRangeRemoved(view: View) {
        mRecyclerView.adapter?.notifyItemRangeRemoved(1, 2)
    }
}