package com.peter.viewgrouptutorial.activity

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.peter.viewgrouptutorial.R
import com.peter.viewgrouptutorial.jetpack.viewmodel.MyViewModel

class NavFragment : Fragment(R.layout.nav_fragment) {
    private val vm: MyViewModel by activityViewModels<MyViewModel>(factoryProducer = { defaultViewModelProviderFactory })


    override fun onAttach(context: Context) {
        super.onAttach(context)
        println("lifecycle TopFragment onAttach")

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("lifecycle TopFragment onCreate $savedInstanceState")


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("lifecycle TopFragment onViewCreated $savedInstanceState")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        println("lifecycle TopFragment onCreateView $savedInstanceState $arguments")

        view?.findViewById<TextView>(R.id.textView)?.text = arguments?.getCharSequence("key")
        return view
    }

    override fun onStart() {
        super.onStart()
        println("lifecycle TopFragment onStart ")
    }

    override fun onResume() {
        super.onResume()
        println("lifecycle TopFragment onResume ")
    }

    override fun onPause() {
        super.onPause()
        println("lifecycle TopFragment onPause ")
    }

    override fun onStop() {
        super.onStop()
        println("lifecycle TopFragment onStop ")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        println("lifecycle TopFragment onSaveInstanceState ")

    }

    override fun onDestroy() {
        super.onDestroy()
        println("lifecycle TopFragment onDestroy ")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        println("lifecycle TopFragment onDestroyView ")

    }

    override fun onDetach() {
        super.onDetach()
        println("lifecycle TopFragment onDetach ")

    }
}