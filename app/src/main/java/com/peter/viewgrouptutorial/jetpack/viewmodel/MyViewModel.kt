package com.peter.viewgrouptutorial.jetpack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyViewModel : ViewModel() {
    init{
        viewModelScope.launch { withContext(this.coroutineContext){

        } }
    }
    private val greeting: MutableLiveData<String> by lazy {
        MutableLiveData<String>().apply {
            value = "Hello World"
        }
    }

    fun getGreeting(): LiveData<String> {
        return greeting
    }

    fun changeGreeting(greeting: String) {
        this.greeting.value = greeting
    }


}