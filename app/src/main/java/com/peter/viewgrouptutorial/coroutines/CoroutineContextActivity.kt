package com.peter.viewgrouptutorial.coroutines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.peter.viewgrouptutorial.R
import kotlinx.coroutines.*

class CoroutineContextActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine_context)
        SupervisorJob() + Dispatchers.Main
//        SupervisorJob().plus(SupervisorJob())
//        Dispatchers.Main.plus(Dispatchers.Default)
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            println(
                "xiaozhan exception ${throwable.message}"
            )
        }
        GlobalScope.launch(Dispatchers.Main + Job() + CoroutineName("GlobalScopeCoroutine") + exceptionHandler) {
            println("xiaozhan running in ${Thread.currentThread()} ${coroutineContext[CoroutineName]}")

            println("xiaozhan Hello Coroutines ")
            1 / 0
            println("xiaozhan will not println")
        }

    }

}