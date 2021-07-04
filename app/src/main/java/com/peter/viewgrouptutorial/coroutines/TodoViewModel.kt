package com.peter.viewgrouptutorial.coroutines

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class TodoViewModel : ViewModel() {
//    var test:MutableLiveData<String> = MutableLiveData("test")

    private val repository = TodoRepository()
    private val todoLiveData: MutableLiveData<Todo> by lazy {
        MutableLiveData<Todo>()
    }

    //    val viewModelResult = Transformations.map()
    val todoLiveDataString = Transformations.switchMap(todoLiveData) {
        MutableLiveData<String>("hello ${it.toString()} ")
    }


    fun getTodo(): LiveData<String> {
        return todoLiveDataString
    }

    fun fetchTodo(id: Int) {
        viewModelScope.launch {
            val todo = repository.getTodo(id)
            todoLiveData.value = todo
        }
    }
}