package com.peter.viewgrouptutorial.fragments

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.peter.viewgrouptutorial.R

class FragmentActivity1 : AppCompatActivity() {
    lateinit var linearLayout: LinearLayout;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment1)
        linearLayout = findViewById(R.id.linear_layout_root)

    }

     class MyFragment(resId: Int) : Fragment(resId) {
    }

    fun addFragment1(view: View) {
        val transaction = supportFragmentManager.beginTransaction();
        transaction.add(
            R.id.linear_layout_root,
            MyFragment(R.layout.test_fragment)
        )
        transaction.addToBackStack("stack0")
        transaction.commit()
    }

    fun addFragment2(view: View) {
        val transaction = supportFragmentManager.beginTransaction();
        transaction.add(
            R.id.linear_layout_root,
            MyFragment(R.layout.test_fragment2)
        )
        transaction.addToBackStack("stack1")
        transaction.commit()
    }

    fun popFragment(view: View) {
        supportFragmentManager.popBackStack("stack1",FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    fun popFragment2(view: View) {
        supportFragmentManager.popBackStack("stack1",0)
    }

    fun popFragment3(view: View) {
        supportFragmentManager.popBackStack("stack0",FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    fun popFragment4(view: View) {
        supportFragmentManager.popBackStack("stack0",0)
    }
}