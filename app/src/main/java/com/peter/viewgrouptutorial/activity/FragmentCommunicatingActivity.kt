package com.peter.viewgrouptutorial.activity

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.peter.viewgrouptutorial.R

/**
 * 研究Fragment之间通信问题
 */
class FragmentCommunicatingActivity : AppCompatActivity() {
    private lateinit var mResultTextView: TextView
    private val mCommunicatingFragment: CommunicatingFragment = CommunicatingFragment()
    private val mCommunicatingViewModel: CommunicatingViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_communicating)
        mResultTextView = findViewById(R.id.result_text_view)
        println("communication in activity $mCommunicatingViewModel")


        mCommunicatingViewModel.selectedItem.observe(this) {
            mResultTextView.text = "you press button $it in fragment"
        }
    }

    fun addFragment(view: View) {
        supportFragmentManager.commit {
            add(R.id.fragment_container_view, mCommunicatingFragment)
        }
    }

    fun removeFragment(view: View) {
        supportFragmentManager.commit {
            remove(mCommunicatingFragment)
        }
    }
}