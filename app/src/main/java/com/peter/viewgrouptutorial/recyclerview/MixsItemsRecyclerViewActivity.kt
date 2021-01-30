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
 * 测试mixItem时动画
 */
class MixsItemsRecyclerViewActivity : AppCompatActivity() {
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mCheckBox: CheckBox
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_mix_item)
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
            println("RecyclerView 测试mixItem时动画 onCreateViewHolder ")
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_item, parent, false)
            return object : RecyclerView.ViewHolder(view) {}
        }

        override fun getItemCount(): Int {
            return mStrings.size
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            println("RecyclerView 测试mixItem时动画 onBindViewHolder $position ${mStrings[position]}")
            val textView = holder.itemView as TextView
            textView.layoutParams.height = (resources.displayMetrics.density * 100).toInt()
            textView.text = mStrings[position]
        }

        override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
            println("RecyclerView 测试mixItem时动画 发生回收 " + (holder.itemView as TextView).text)
            super.onViewRecycled(holder)
        }
    }


    fun addMiddleItems(view: View) {
        val topPosition = mRecyclerView.getChildAdapterPosition(mRecyclerView.getChildAt(0))
        val lastPosition =
            mRecyclerView.getChildAdapterPosition(mRecyclerView.getChildAt(mRecyclerView.childCount - 1))
        val deletePosition = (topPosition + lastPosition) / 2
//        (mRecyclerView.adapter as MyAdapter).mStrings.removeAt(deletePosition)
//        (mRecyclerView.adapter as MyAdapter).notifyItemRemoved(deletePosition)
        (mRecyclerView.adapter as MyAdapter).mStrings.add(deletePosition, "addItem1")
        (mRecyclerView.adapter as MyAdapter).mStrings.add(deletePosition + 1, "addItem2")
        (mRecyclerView.adapter as MyAdapter).mStrings.add(deletePosition + 2, "addItem3")
        (mRecyclerView.adapter as MyAdapter).mStrings.add(deletePosition + 3, "addItem4")

        if (!mCheckBox.isChecked) {
            (mRecyclerView.adapter as MyAdapter).notifyItemRangeInserted(deletePosition, 6)
            (mRecyclerView.adapter as MyAdapter).mStrings.set(deletePosition + 4, "UpdateItem1")
            (mRecyclerView.adapter as MyAdapter).mStrings.set(deletePosition + 5, "UpdateItem2")
            (mRecyclerView.adapter as MyAdapter).notifyItemRangeChanged(deletePosition + 4, 2)

        } else {
            (mRecyclerView.adapter as MyAdapter).notifyDataSetChanged()

        }


    }

    fun addTailItems(view: View) {
        val lastPosition =
            mRecyclerView.getChildAdapterPosition(mRecyclerView.getChildAt(mRecyclerView.childCount - 2))
        (mRecyclerView.adapter as MyAdapter).mStrings.add(lastPosition, "added Item2")
        (mRecyclerView.adapter as MyAdapter).mStrings.add(lastPosition, "added Item1")
        if (!mCheckBox.isChecked) {
            (mRecyclerView.adapter as MyAdapter).notifyItemRangeInserted(lastPosition, 2)
        } else {
            (mRecyclerView.adapter as MyAdapter).notifyDataSetChanged()

        }
    }

    fun addHeadItems(view: View) {
        val topPosition = mRecyclerView.getChildAdapterPosition(mRecyclerView.getChildAt(0))
        (mRecyclerView.adapter as MyAdapter).mStrings.add(topPosition, "added Item2")
        (mRecyclerView.adapter as MyAdapter).mStrings.add(topPosition, "added Item1")
        if (!mCheckBox.isChecked) {
            (mRecyclerView.adapter as MyAdapter).notifyItemRangeInserted(topPosition, 2)
        } else {
            (mRecyclerView.adapter as MyAdapter).notifyDataSetChanged()

        }
    }
}