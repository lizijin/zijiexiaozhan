package com.peter.viewgrouptutorial.activity

import android.graphics.Color
import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.peter.viewgrouptutorial.R
import kotlin.concurrent.thread

class AActivity : AppCompatActivity() {
    companion object {
        //静态变量，ArrayList保存开发者缓存View
        var sCustomViewCaches: ArrayList<View> = arrayListOf()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //当AActivity MessageQueue有空闲的时候，创建10个HeavyText布局ItemView
        Looper.myQueue().addIdleHandler {
            thread {
                repeat(10) {
                    val linearLayout = LinearLayout(this@AActivity).apply {
                        orientation = LinearLayout.VERTICAL
                    }

                    //将itemView add到linearLayout上，后有remove掉，为了正确的将item布局中padding显示出来
                    val itemView = LayoutInflater.from(this@AActivity)
                        .inflate(R.layout.custom_cache_view_item, linearLayout)
                    linearLayout.removeView(itemView)

                    //背景设置成红色为了更好的测试是否用到了正确缓存中的View
                    itemView.setBackgroundColor(Color.RED)

                    itemView.layoutParams = RecyclerView.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )

                    // 反射设置RecyclerView.LayoutParams的mViewHolder属性
                    val viewHolderField =
                        RecyclerView.LayoutParams::class.java.getDeclaredField("mViewHolder")
                            .apply {
                                isAccessible = true
                            }

                     //等效于Adapter中的onCreateViewHolder方法，创建ViewHolder
                    val viewHolder = object : RecyclerView.ViewHolder(itemView) {}

                    //将ViewHolder的mItemViewType设置成0。具体业务具体实现。主要是为了复用
                    with(
                        RecyclerView.ViewHolder::class.java.getDeclaredField("mItemViewType")
                            .apply {
                                isAccessible = true
                            }) {
                        set(viewHolder, 0)
                    }

                    viewHolderField.set(itemView.layoutParams, viewHolder)

                    //将ItemView保存到缓存中
                    sCustomViewCaches.add(itemView)
                }

                println("custom  view cache ok")

            }
            false
        }
    }
}