package com.peter.viewgrouptutorial.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.peter.viewgrouptutorial.R

class TestFragmentActivity : AppCompatActivity() {
    private lateinit var mSpecTopFragment: SpecialFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_fragment)
        mSpecTopFragment = SpecialFragment()
        mSpecTopFragment.arguments = Bundle().apply {
            putCharSequence("key", "指定增加 移除观察生命周期")
        }
    }

    fun popupInclude(view: View) {
        supportFragmentManager.popBackStack("bbb2", FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    fun popupNotInclude(view: View) {
        supportFragmentManager.popBackStack("bbb2", 0)
    }

    var count = 0
    fun addFragmentTwice(view: View) {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<TopFragment>(R.id.top_fragment_container_view, args = Bundle().apply {
                count++
                putCharSequence("key", "aaa$count")
            })
            addToBackStack("aaa$count")
        }
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<TopFragment>(R.id.bottom_fragment_container_view, args = Bundle().apply {
                count++
                putCharSequence("key", "bbb$count")
            })
            addToBackStack("bbb$count")
        }
    }

    fun addFragmentOnce(view: View) {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            val bundle = Bundle().apply {
                count++
                putCharSequence("key", "ccc$count")
            }
            add<TopFragment>(R.id.top_fragment_container_view, args = bundle)
            add<TopFragment>(R.id.bottom_fragment_container_view, args = bundle)

            addToBackStack("ccc$count")
        }

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            val bundle = Bundle().apply {
                count++
                putCharSequence("key", "ccc$count")
            }
            add<TopFragment>(R.id.top_fragment_container_view, args = bundle)
            add<TopFragment>(R.id.bottom_fragment_container_view, args = bundle)

//            addToBackStack("ccc$count")
        }
    }

    fun lifecycle(view: View) {

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            val bundle = Bundle().apply {
                count++
                putCharSequence("key", "ccc$count")
            }
            add<TopFragment>(R.id.top_fragment_container_view, args = bundle)

            addToBackStack("ccc$count")
        }


    }

    fun addSpecFragment(view: View) {
        supportFragmentManager.commit {
            add(R.id.top_fragment_container_view, mSpecTopFragment, "")
        }
    }

    fun removeSpecFragment(view: View) {
        supportFragmentManager.commit {
            remove(mSpecTopFragment)
        }
    }

    fun showAllFragments(view: View) {
        for (fragment in supportFragmentManager.fragments) {
            println("showAllFragments $fragment")
        }
    }
}