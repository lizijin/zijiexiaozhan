package com.peter.viewgrouptutorial.coroutines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.peter.viewgrouptutorial.R
import kotlinx.coroutines.*
import kotlin.coroutines.EmptyCoroutineContext

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

        plusCase()

    }

    fun plusCase() {
        //case1 plus(EmptyCoroutineContext)
        val case1 = Dispatchers.Main + EmptyCoroutineContext
        println("xiaozhan case1 $case1")

        //case2 相同类型的Element

        val case2 = CoroutineName(
            "c1") + CoroutineName("c2")
        println("xiaozhan case2 $case2")

        //case3 当前的CoroutineContext没有ContinuationInterceptor
        val case3 = CoroutineName("c1") + Job()
        println("xiaozhan case3 $case3")

        //case4 当前的CoroutineContext只有ContinuationInterceptor，
        val case4 = Dispatchers.Main + Job()
        println("xiaozhan case4 $case4")

        //case5 当前的CoroutineContext有ContinuationInterceptor和其他

        val case5 = case4 + CoroutineName("c5")
        println("xiaozhan case5 $case5")
        case5.minusKey(Job)
    }

}