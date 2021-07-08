package com.peter.viewgrouptutorial.activity

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.peter.viewgrouptutorial.R

class BottomFragment:Fragment(R.layout.bottom_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("BottomFragment create")
    }
}