package com.peter.viewgrouptutorial.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.peter.viewgrouptutorial.R
import java.util.*

/**
 * 测试删除Item时动画
 */
class UpdateItemsRecyclerViewActivity : AppCompatActivity() {
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mCheckBox: CheckBox
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_update_item)
        mRecyclerView = findViewById(R.id.recyclerview)
        mCheckBox = findViewById(R.id.checkbox)
        mRecyclerView.setHasFixedSize(true)
//        mRecyclerView.setItemViewCacheSize(4)
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
            println("RecyclerView 测试updateItem时动画 onCreateViewHolder ")
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_item, parent, false)
            return object : RecyclerView.ViewHolder(view) {}
        }

        override fun getItemCount(): Int {
            return mStrings.size
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            println("RecyclerView 测试updateItem时动画 onBindViewHolder $position ${mStrings[position]}")
            val textView = holder.itemView as TextView
            textView.layoutParams.height = (resources.displayMetrics.density * 100).toInt()
            textView.text = mStrings[position]
        }

        override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
            println("RecyclerView 测试updateItem时动画 发生回收 " + (holder.itemView as TextView).text)
            super.onViewRecycled(holder)
        }
    }


    fun addMiddleItems(view: View) {
        val topPosition = mRecyclerView.getChildAdapterPosition(mRecyclerView.getChildAt(0))
        val lastPosition =
            mRecyclerView.getChildAdapterPosition(mRecyclerView.getChildAt(mRecyclerView.childCount - 1))
        val deletePosition = (topPosition + lastPosition) / 2
        (mRecyclerView.adapter as MyAdapter).mStrings.set(deletePosition, "update item1")
        (mRecyclerView.adapter as MyAdapter).mStrings.set(deletePosition+1, "update item2")
        if (!mCheckBox.isChecked) {
            (mRecyclerView.adapter as MyAdapter).notifyItemRangeChanged(deletePosition, 2)
        } else {
            (mRecyclerView.adapter as MyAdapter).notifyDataSetChanged()

        }
    }

    fun addTailItems(view: View) {
        val lastPosition =
            mRecyclerView.getChildAdapterPosition(mRecyclerView.getChildAt(mRecyclerView.childCount - 1))
        (mRecyclerView.adapter as MyAdapter).mStrings.set(lastPosition, "update　item1")
        (mRecyclerView.adapter as MyAdapter).mStrings.set(lastPosition+1, "update　item2")
        if (!mCheckBox.isChecked) {
            (mRecyclerView.adapter as MyAdapter).notifyItemRangeChanged(lastPosition, 2)
        } else {
            (mRecyclerView.adapter as MyAdapter).notifyDataSetChanged()

        }
    }

    fun addHeadItems(view: View) {
        val topPosition = mRecyclerView.getChildAdapterPosition(mRecyclerView.getChildAt(0))
        (mRecyclerView.adapter as MyAdapter).mStrings[topPosition] = "update item1"
        (mRecyclerView.adapter as MyAdapter).mStrings[topPosition + 1] = "update item2"
        if (!mCheckBox.isChecked) {
            (mRecyclerView.adapter as MyAdapter).notifyItemRangeChanged(topPosition, 2)
        } else {
            (mRecyclerView.adapter as MyAdapter).notifyDataSetChanged()

        }
    }
}