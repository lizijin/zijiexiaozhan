package com.peter.viewgrouptutorial.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.peter.viewgrouptutorial.R
import java.util.*

/**
 * 测试删除Item时动画
 */
class DragAndSwipeRecyclerViewActivity : AppCompatActivity() {
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mCheckBox: CheckBox
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_insert_item)
        mRecyclerView = findViewById(R.id.recyclerview)
        mCheckBox = findViewById(R.id.checkbox)
        mRecyclerView.setHasFixedSize(true)

        val callback = object : SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val fromPos = viewHolder.adapterPosition
                val toPos = target.adapterPosition
                (mRecyclerView.adapter as DragAndSwipeRecyclerViewActivity.MyAdapter).mStrings.swap(
                    fromPos,
                    toPos
                )
                (mRecyclerView.adapter as MyAdapter).notifyItemMoved(fromPos,toPos)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                TODO("Not yet implemented")
            }
        }
        ItemTouchHelper(callback).attachToRecyclerView(mRecyclerView)
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
        }

        override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
            println("RecyclerView 测试增加Item时动画 发生回收 " + (holder.itemView as TextView).text)
            super.onViewRecycled(holder)
        }
    }


    fun addMiddleItems(view: View) {
        val topPosition = mRecyclerView.getChildAdapterPosition(mRecyclerView.getChildAt(0))
        val lastPosition =
            mRecyclerView.getChildAdapterPosition(mRecyclerView.getChildAt(mRecyclerView.childCount - 1))
        val deletePosition = (topPosition + lastPosition) / 2
        (mRecyclerView.adapter as MyAdapter).mStrings.add(deletePosition, "addItem2")
        (mRecyclerView.adapter as MyAdapter).mStrings.add(deletePosition, "addItem1")
        if (!mCheckBox.isChecked) {
            (mRecyclerView.adapter as MyAdapter).notifyItemRangeInserted(deletePosition, 2)
        } else {
            (mRecyclerView.adapter as MyAdapter).notifyDataSetChanged()

        }
    }

    private fun <T> MutableList<T>.swap(a: Int, b: Int): List<T> = this
        .also {
            val temp = this[a]
            it[a] = this[b]
            it[b] = temp
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
        val topPosition = mRecyclerView.getChildAdapterPosition(mRecyclerView.getChildAt(1))
        (mRecyclerView.adapter as MyAdapter).mStrings.add(topPosition, "added Item2")
        (mRecyclerView.adapter as MyAdapter).mStrings.add(topPosition, "added Item1")
        if (!mCheckBox.isChecked) {
            (mRecyclerView.adapter as MyAdapter).notifyItemRangeInserted(topPosition, 2)
        } else {
            (mRecyclerView.adapter as MyAdapter).notifyDataSetChanged()

        }
    }
}