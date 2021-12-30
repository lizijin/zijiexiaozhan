package com.peter.viewgrouptutorial.coroutines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.peter.viewgrouptutorial.R
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ScopeNestScopeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scope_nest_scope)
        val mainScope = MainScope()
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            println("scope exception $throwable")
        }
        mainScope.launch(exceptionHandler) {

            lifecycleScope.launch {
                delay(1000)
                println("life scope job ")
            }
            launch {
                delay(100)
                println("scope job1 ")
                throw RuntimeException()
            }

            launch {
                delay(200)
                println("scope job1 ")
            }
        }
    }
}