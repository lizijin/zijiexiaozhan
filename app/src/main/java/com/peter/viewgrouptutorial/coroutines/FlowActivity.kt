package com.peter.viewgrouptutorial.coroutines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.peter.viewgrouptutorial.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class FlowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow)
        val flow = flow<Int> {
            for (i in 1..3) {
                delay(100)
                println("emit running in ${Thread.currentThread().name}")

                emit(i)
            }
        }.flowOn(Dispatchers.IO).filter {
            println("filter running in ${Thread.currentThread().name}")

            it % 2 == 0
        }
        MainScope().launch {

            flow.collect {
                println("$it running in ${Thread.currentThread().name}")
            }

        }
    }
}