package com.peter.viewgrouptutorial.activity

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.peter.viewgrouptutorial.R

class TopFragment : Fragment(R.layout.top_fragment) {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        println("TopFragment onAttach")

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("TopFragment onCreate $savedInstanceState")

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("TopFragment onViewCreated $savedInstanceState")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        println("TopFragment onCreateView $savedInstanceState $arguments")

        view?.findViewById<TextView>(R.id.textView)?.text = arguments?.getCharSequence("key")
        return view
    }

    override fun onStart() {
        super.onStart()
        println("TopFragment onStart ")
    }

    override fun onResume() {
        super.onResume()
        println("TopFragment onResume ")
    }

    override fun onPause() {
        super.onPause()
        println("TopFragment onPause ")
    }

    override fun onStop() {
        super.onStop()
        println("TopFragment onStop ")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        println("TopFragment onSaveInstanceState ")

    }

    override fun onDestroy() {
        super.onDestroy()
        println("TopFragment onDestroy ")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        println("TopFragment onDestroyView ")

    }

    override fun onDetach() {
        super.onDetach()
        println("TopFragment onDetach ")

    }
}