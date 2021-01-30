package com.peter.viewgrouptutorial.viewpager2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.peter.viewgrouptutorial.R

/**
 * ViewPager With Fragments
 */
class ViewPager2WithFragmentsActivity : AppCompatActivity() {
    private lateinit var mViewPager2: ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_view_pager2)
        mViewPager2 = findViewById(R.id.viewPager2)
        mViewPager2.orientation = ViewPager2.ORIENTATION_VERTICAL
        mViewPager2.adapter = MyAdapter(this)
//        mViewPager2.offscreenPageLimit = 2
    }

    inner class MyAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int {
            return 100
        }

        override fun createFragment(position: Int): Fragment {
            return MyFragment("Item $position")
        }

    }

    class MyFragment(val text: String) : Fragment() {
        init {
            println("jiangbinMyFragment $text")
        }
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
//            return super.onCreateView(inflater, container, savedInstanceState)
            var view = layoutInflater.inflate(R.layout.view_item_view_pager_snap, container)
            view.findViewById<TextView>(R.id.text_view).text = text
            return view;
        }
    }


}