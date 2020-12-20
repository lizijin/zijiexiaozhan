package com.peter.viewgrouptutorial

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_view_page.*

class ViewPageActivity : AppCompatActivity() {
    private lateinit var mViewPager: ViewPager
    private val array = arrayOf(
        R.drawable.pic1
//        R.drawable.xuanyu1,
//        R.drawable.xuanyu4,
//        R.drawable.xuanyu3,
//        R.drawable.xuanyu6,
//        R.drawable.xuanyu5,
//        R.drawable.xuanyu8,
//        R.drawable.xuanyu7,
//        R.drawable.xuanyu9
    )

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_page)
        postponeEnterTransition()

        mViewPager = findViewById(R.id.viewPager)

        mViewPager.adapter = object : PagerAdapter() {
            override fun isViewFromObject(view: View, ov: Any): Boolean {
                return view === ov
            }

            override fun getCount(): Int {
                return array.size
            }

            override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
//                super.destroyItem(container, position, `object`)
            }
            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun instantiateItem(container: ViewGroup, position: Int): Any {
//                return super.instantiateItem(container, position)
                val view = LayoutInflater.from(this@ViewPageActivity)
                    .inflate(R.layout.activity_photo_detail, null).apply {
                        (findViewById(R.id.photo_detail_view) as ImageView).apply {
                            setImageResource(array[position])
                            transitionName = "xuanyu$position"
                        }
                    }
                container.addView(view)
                return view

            }
        }
        viewPager.addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
            override fun onLayoutChange(
                v: View, left: Int, top: Int, right: Int, bottom: Int,
                oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int
            ) {
                if (viewPager.getChildCount() > 0) {
                    viewPager.removeOnLayoutChangeListener(this)
                    startPostponedEnterTransition()
                }
            }
        })
//        viewPager.currentItem = ListViewSceneActivity.position
    }
}