package com.peter.viewgrouptutorial.recyclerview

import android.animation.AnimatorInflater
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.BounceInterpolator
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.peter.viewgrouptutorial.R
import java.util.*


class RecyclerViewActivityAnimate : AppCompatActivity() {
    private lateinit var mRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_animate)
        mRecyclerView = findViewById(R.id.recyclerview)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.setItemViewCacheSize(4)
        mRecyclerView.itemAnimator = object :SimpleItemAnimator(){
            override fun animateAdd(holder: RecyclerView.ViewHolder?): Boolean {
                val set = AnimatorInflater.loadAnimator(
                    this@RecyclerViewActivityAnimate,
                    R.animator.zoom_in
                )
                set.interpolator = BounceInterpolator()
                set.setTarget(holder!!.itemView)
                set.start()

                return true
            }

            override fun runPendingAnimations() {
            }

            override fun animateMove(
                holder: RecyclerView.ViewHolder?,
                fromX: Int,
                fromY: Int,
                toX: Int,
                toY: Int
            ): Boolean {
                return false
            }

            override fun animateChange(
                oldHolder: RecyclerView.ViewHolder?,
                newHolder: RecyclerView.ViewHolder?,
                fromLeft: Int,
                fromTop: Int,
                toLeft: Int,
                toTop: Int
            ): Boolean {
                return false
            }

            override fun isRunning(): Boolean {
                return false
            }

            override fun endAnimation(item: RecyclerView.ViewHolder) {
            }

            override fun animateRemove(holder: RecyclerView.ViewHolder?): Boolean {
               return false
            }

            override fun endAnimations() {
            }

        }
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
            println("RecyclerView 场景一 onCreateViewHolder ")
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_item, parent, false)
            return object : RecyclerView.ViewHolder(view) {}
        }

        override fun getItemCount(): Int {
            return mStrings.size
        }
        fun add(){
            mStrings.add(2,"test");
            notifyItemRangeInserted(2,3);
//            notifyDataSetChanged()
        }
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            println("RecyclerView 场景一 onBindViewHolder $position ")
            val textView = holder.itemView as TextView
            textView.layoutParams.height = (resources.displayMetrics.density * 100).toInt()
            textView.text = mStrings[position]
        }

        override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
            println("RecyclerView 场景一 发生回收 " + (holder.itemView as TextView).text)
            super.onViewRecycled(holder)
        }

    }

    fun scroll120(view: View) {
        mRecyclerView.scrollBy(0, (resources.displayMetrics.density * 120).toInt())
    }

    fun scroll60(view: View) {
        mRecyclerView.scrollBy(0, (resources.displayMetrics.density * 60).toInt())

    }

    fun scroll40(view: View) {
        mRecyclerView.scrollBy(0, (resources.displayMetrics.density * 40).toInt())

    }

    fun animation1(view: View) {
        (mRecyclerView.adapter as MyAdapter).add();
    }
}