package com.peter.viewgrouptutorial.coroutines

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TodoViewModel : ViewModel() {
//    var test:MutableLiveData<String> = MutableLiveData("test")

    private val repository = TodoRepository()
    private val todoLiveData: MutableLiveData<Todo> by lazy {
        MutableLiveData<Todo>()
    }

    fun getTodo(): LiveData<Todo> {
        return todoLiveData
    }

    fun fetchTodo(id: Int) {
        viewModelScope.launch {
            val todo = repository.getTodo(id)
            todoLiveData.value = todo
        }
    }
}