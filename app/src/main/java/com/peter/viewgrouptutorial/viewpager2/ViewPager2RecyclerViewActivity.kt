package com.peter.viewgrouptutorial.viewpager2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.peter.viewgrouptutorial.R
import java.util.*

/**
 * ViewPager Snap
 */
class ViewPager2RecyclerViewActivity : AppCompatActivity() {
    private lateinit var mViewPager2: ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_view_pager2)
        mViewPager2 = findViewById(R.id.viewPager2)
        mViewPager2.orientation = ViewPager2.ORIENTATION_VERTICAL
        val list: MutableList<String> =
            ArrayList()
        repeat(100) {
            list.add("item $it")
        }
        mViewPager2.adapter = MyAdapter(list)
//        mViewPager2.offscreenPageLimit = 2
    }

    inner class MyAdapter(val mStrings: MutableList<String>) :
        RecyclerView.Adapter<MyViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            println("jiangbinViewPager2 onCreateViewHolder")
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_item_view_pager_snap, parent, false)
            return MyViewHolder(view)
        }

        override fun getItemCount(): Int {
            return mStrings.size
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            println("jiangbinViewPager2 $position onBindViewHolder")

            holder.textView.text = mStrings[position]
        }

        override fun onViewRecycled(holder: MyViewHolder) {
            println("jiangbinViewPager2 onViewRecycled")
            super.onViewRecycled(holder)
        }
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView = itemView.findViewById(R.id.text_view) as TextView
    }


}