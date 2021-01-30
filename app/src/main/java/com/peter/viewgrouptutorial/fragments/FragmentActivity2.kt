package com.peter.viewgrouptutorial.fragments

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.peter.viewgrouptutorial.R

class FragmentActivity2 : AppCompatActivity() {
    lateinit var linearLayout: LinearLayout;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment2)
        linearLayout = findViewById(R.id.linear_layout_root)

    }

     class MyFragment(resId: Int) : Fragment(resId) {
    }

    fun addFragment(view: View) {
        val transaction = supportFragmentManager.beginTransaction();
        transaction.add(
            R.id.linear_layout_root,
            MyFragment(R.layout.test_fragment)
        )
        transaction.commitNow()
    }
}