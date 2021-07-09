package com.peter.viewgrouptutorial.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.peter.viewgrouptutorial.R

class SpecialFragment : Fragment(R.layout.special_fragment) {
    private var mFragmentView: View? = null
    var count = 0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return mFragmentView ?: super.onCreateView(inflater, container, savedInstanceState).apply {
            mFragmentView = this!!
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("SpecialFragment create")

        val textView = view.findViewById<TextView>(R.id.special_text_view)
        val button = view.findViewById<Button>(R.id.special_add_count)
        button.setOnClickListener {
            textView.text = "count is ${count++}"
        }
    }
}