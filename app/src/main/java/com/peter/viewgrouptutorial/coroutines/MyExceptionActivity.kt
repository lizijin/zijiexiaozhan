package com.peter.viewgrouptutorial.coroutines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.peter.viewgrouptutorial.R
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MyExceptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_exception)
        val handler = CoroutineExceptionHandler { _, throwable ->
            println("jiangbin exception ${throwable.message}")
        }
        val scope = CoroutineScope(Job())
        scope.launch {
            println("MyExceptionActivity parent ${this.coroutineContext[Job]}")
            launch(handler) {
                println("MyExceptionActivity child ${this.coroutineContext[Job]}")

                throw Exception("Failed coroutine")
            }
        }
    }
}