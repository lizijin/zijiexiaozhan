package com.peter.viewgrouptutorial.coroutines

import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class TodoViewModel : ViewModel() {

    private val repository = TodoRepository()
    private val todoLiveData: MutableLiveData<Todo> by lazy {
        MutableLiveData<Todo>()
    }


    private val todoLiveData2: MutableLiveData<Todo> by lazy {
        MutableLiveData<Todo>()
    }

    private val idLiveData: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("jiangbin excpetion $throwable")
    }
    val todoLiveDataSwitchMap = idLiveData.switchMap {
        liveData(exceptionHandler) {
//            emit(repository.getTodo(it))
            val id = it
//            repeat(10000){
//                println("repeat jiangbin")
//                repository.getTodo(id)
//            }
                emit(repository.getTodo(it))

        }
    }

    private val todoLiveDataMap = Transformations.map(todoLiveData) {
        "This is todo with Transformations.map ${it.toString()}"
    }

    fun getTodoNormal(): LiveData<Todo> {
        return todoLiveData
    }

    fun getTodoMap(): LiveData<String> {
        return todoLiveDataMap
    }

    fun getTodoSwitchMap(): LiveData<Todo> {
        return todoLiveDataSwitchMap
    }


    fun fetchTodo(id: Int) {
        viewModelScope.launch(exceptionHandler) {
            val todo = repository.getTodo(id)
            todoLiveData.value = todo
        }
    }

    fun setId(parseInt: Int) {
        idLiveData.value = parseInt

    }

//    fun fetchTodoSwitchMap(id: Int) {
//        viewModelScope.launch {
//            val todo = repository.getTodo(id)
//            todoLiveData2.value = todo
//        }
//    }

}