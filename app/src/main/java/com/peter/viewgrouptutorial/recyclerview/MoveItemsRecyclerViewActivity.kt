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

/**
 * 测试Move Item时动画
 */
class MoveItemsRecyclerViewActivity : AppCompatActivity() {
    private lateinit var mRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_move_item)
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
    }

    inner class MyAdapter(val mStrings: MutableList<String>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            println("RecyclerView 测试MOVEItem时动画 onCreateViewHolder ")
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_item, parent, false)
            return object : RecyclerView.ViewHolder(view) {}
        }

        override fun getItemCount(): Int {
            return mStrings.size
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            println("RecyclerView 测试MOVEItem时动画 onBindViewHolder $position ${mStrings[position]}")
            val textView = holder.itemView as TextView
            textView.layoutParams.height = (resources.displayMetrics.density * 100).toInt()
            textView.text = mStrings[position]
        }

        override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
            println("RecyclerView 测试MOVEItem时动画 发生回收 " + (holder.itemView as TextView).text)
            super.onViewRecycled(holder)
        }
    }

    //删除头部两个Items
    fun removeHeadItems(view: View) {
//        mRecyclerView.layoutManager?.childCount
        val topPosition = mRecyclerView.getChildAdapterPosition(mRecyclerView.getChildAt(0))
        if (topPosition == 0) {
            (mRecyclerView.adapter as MyAdapter).mStrings.swap(topPosition, topPosition + 1)
            (mRecyclerView.adapter as MyAdapter).notifyItemMoved(topPosition, topPosition + 1)
        } else {
            (mRecyclerView.adapter as MyAdapter).mStrings.swap(topPosition, topPosition - 1)
            (mRecyclerView.adapter as MyAdapter).notifyItemMoved(topPosition, topPosition - 1)
        }

    }

    private fun <T> MutableList<T>.swap(a: Int, b: Int): List<T> = this
        .also {
            val temp = this[a]
            it[a] = this[b]
            it[b] = temp
        }

    fun removeMiddleItems(view: View) {
        val topPosition = mRecyclerView.getChildAdapterPosition(mRecyclerView.getChildAt(0))
        val lastPosition =
            mRecyclerView.getChildAdapterPosition(mRecyclerView.getChildAt(mRecyclerView.childCount - 1))
        val deletePosition = (topPosition + lastPosition) / 2
        (mRecyclerView.adapter as MyAdapter).mStrings.swap(deletePosition, deletePosition + 1)
        (mRecyclerView.adapter as MyAdapter).notifyItemMoved(deletePosition, deletePosition + 1)
    }

    fun removeTailItems(view: View) {
        val lastPosition =
            mRecyclerView.getChildAdapterPosition(mRecyclerView.getChildAt(mRecyclerView.childCount - 1))
        val size = (mRecyclerView.adapter as MyAdapter).mStrings.size;
        if (lastPosition < size - 1) {
            (mRecyclerView.adapter as MyAdapter).mStrings.swap(lastPosition, lastPosition + 1)
            (mRecyclerView.adapter as MyAdapter).notifyItemMoved(lastPosition, lastPosition + 1)
        } else {
            (mRecyclerView.adapter as MyAdapter).mStrings.swap(lastPosition, lastPosition - 1)
            (mRecyclerView.adapter as MyAdapter).notifyItemMoved(lastPosition, lastPosition - 1)
        }
    }
}