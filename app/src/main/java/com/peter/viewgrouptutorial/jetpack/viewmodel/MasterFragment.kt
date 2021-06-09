package com.peter.viewgrouptutorial.jetpack.viewmodel

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

class MasterFragment :Fragment(){
    val mViewModel:MyViewModel by activityViewModels()
}