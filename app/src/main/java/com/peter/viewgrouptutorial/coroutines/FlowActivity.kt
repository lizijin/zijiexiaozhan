package com.peter.viewgrouptutorial.coroutines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.peter.viewgrouptutorial.R
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class FlowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow)
        val flow = flow<Int> {
            for (i in 1..3) {
                delay(100)
                println("emit running in ${Thread.currentThread().name} ${currentCoroutineContext()}")

                emit(i)
            }
        }
            .buffer().filter {
            println("filter running in ${Thread.currentThread().name} ${currentCoroutineContext()}")

            it % 2 == 0
        }
        MainScope().launch {

            flow.collect {
                println("$it running in ${Thread.currentThread().name} ${currentCoroutineContext()}")
            }

        }
    }
}