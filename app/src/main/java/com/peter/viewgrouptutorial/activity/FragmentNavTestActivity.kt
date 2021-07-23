package com.peter.viewgrouptutorial.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.peter.viewgrouptutorial.R

class FragmentNavTestActivity : AppCompatActivity() {
    //    lateinit var mTopFragmentContainerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_nav_test)
    }

    fun addFragment(view: View) {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            val bundle = Bundle().apply {
                putCharSequence("key", "this is a single Fragment")
            }
            add<TopFragment>(R.id.top_fragment_container_view, args = bundle)
            add<NavFragment>(R.id.bottom_fragment_container_view, args = bundle)

            addToBackStack(null)
        }
    }

//    fun navFragment(view: View) {
//        val fragment = supportFragmentManager.findFragmentById(R.id.bottom_fragment_container_view)
//        supportFragmentManager.commit {
//            setPrimaryNavigationFragment(fragment)
//        }
//        fragment?.childFragmentManager?.commit {
//            setReorderingAllowed(true)
//            val bundle = Bundle().apply {
//                putCharSequence("key", "this is a nav Fragment")
//            }
//            add<TopFragment>(R.id.nav_fragment, args = bundle)
//            addToBackStack(null)
//
//        }
//        println("the fragment is $fragment")
//    }


    fun navFragment(view: View) {
//        val fragment = supportFragmentManager.findFragmentById(R.id.bottom_fragment_container_view)
//        supportFragmentManager.commit {
//            setPrimaryNavigationFragment(fragment)
//        }
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            val bundle = Bundle().apply {
                putCharSequence("key", "this is a nav Fragment")
            }
            add<TopFragment>(R.id.nav_fragment, args = bundle)
            addToBackStack(null)

        }
//        println("the fragment is $fragment")
    }
}