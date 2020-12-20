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
import com.peter.viewgrouptutorial.stickyheader.AnimalsAdapter
import java.util.*

class RecyclerViewTestActivity : AppCompatActivity() {
    private lateinit var mRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_test)
        mRecyclerView = findViewById(R.id.recyclerview)
        mRecyclerView.setItemViewCacheSize(0)
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.isItemPrefetchEnabled = true

        mRecyclerView.layoutManager = linearLayoutManager
//        mRecyclerView.setItemAnimator(null);
        //        mRecyclerView.setItemAnimator(null);
        val list: MutableList<String> =
            ArrayList()
//        val animals = resources.getStringArray(R.array.animals)
        repeat(100) {
            list.add("item $it")
        }
//        multiTypeAdapter.setItems(list);
//        multiTypeAdapter.register(String.class, new StringViewBinder());
        //        multiTypeAdapter.setItems(list);
//        multiTypeAdapter.register(String.class, new StringViewBinder());
        val animalsAdapter = object : AnimalsAdapter<RecyclerView.ViewHolder>() {
            override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
                super.onViewRecycled(holder)
                println("MyRecyclerViewActivity onViewRecycled -- $holder")

            }


            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ): RecyclerView.ViewHolder {
                println("MyRecyclerViewActivity onCreateViewHolder --")
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_item, parent, false)
                return object : RecyclerView.ViewHolder(view) {}
            }

            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
                println(
                    "MyRecyclerViewActivity onBindViewHolder -- position $position " + getItem(
                        position
                    )
                )
                val textView = holder.itemView as TextView

                textView.layoutParams.height = if (position == 0) 10 else 100

                textView.text = getItem(position)
            }
        }
        animalsAdapter.addAll(list)
        animalsAdapter.setHasStableIds(true)
        mRecyclerView.setAdapter(animalsAdapter)
    }

    fun scrollRecyclerView90(view: View) {
        mRecyclerView.scrollBy(0, 90)
    }

    fun scrollRecyclerView110(view: View) {
        mRecyclerView.scrollBy(0, 10)

    }

    fun scrollRecyclerView110_(view: View) {
        mRecyclerView.scrollBy(0, -110)

    }

    fun scrollRecyclerView90_(view: View) {
        mRecyclerView.scrollBy(0, -90)

    }
}