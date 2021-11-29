package com.peter.viewgrouptutorial.coroutines

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.peter.viewgrouptutorial.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlin.concurrent.thread
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

class CoroutinesScopeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines_scope)
        test1()
//        val liveData:LiveData<String> = liveData {
//            println("CoroutinesScopeActivity1  111111")
//
//            test()
//            println("CoroutinesScopeActivity1  222222")
//
//            emit("111")
//            println("CoroutinesScopeActivity1  3333333")
//
//        }
//        lifecycleScope.launchWhenResumed {
//            delay(1000)
//            println("CoroutinesScopeActivity1  onResume")
//
//        }


//        MainScope().launch(Executors.newFixedThreadPool(1).asCoroutineDispatcher()) {
//            val result = withContext(Dispatchers.IO) {
//                Thread.sleep(10_000)
//                println("I am running in ${Thread.currentThread()}")
//                "Hello coroutines"
//
//            }
//            println("I am running in ${Thread.currentThread()} result is $result")
//        }

//        MainScope().launch {
//            val result = withContext(Dispatchers.IO) {
//                Thread.sleep(10_000)
//                println("I am running in ${Thread.currentThread()}")
//                "Hello coroutines"
//
//            }
//            println("I am running in ${Thread.currentThread()} result is $result")
//        }

        val coroutinesBodyRunnable = java.lang.Runnable {
            thread {
                Thread.sleep(10_000)
                println("I am running in ${Thread.currentThread()}")
                val result = "Hello coroutines"
                Handler(Looper.getMainLooper()).post {
                    println("I am running in ${Thread.currentThread()} result is $result")
                }
            }

        }
        Handler(Looper.getMainLooper()).post(coroutinesBodyRunnable)

//        thread {
//            Thread.sleep(10_000)
//            println("I am running in ${Thread.currentThread()}")
//            val result = "Hello coroutines"
//            Handler(Looper.getMainLooper()).post {
//                println("I am running in ${Thread.currentThread()} result is $result")
//            }
//        }
//        liveData.observe(this){
//
//        }

//        MainScope().launch {
//            delay(5000)
//            println("CoroutinesScopeActivity1  after 5")
//            liveData.observe(this@CoroutinesScopeActivity){
//                println("CoroutinesScopeActivity1  444444")
//
//            }
//        }

//        mySuspend {
//            test()
//        }.startCoroutine(RunSuspend())
//        println("CoroutinesScopeActivity end ")

//        MainScope().launch {
//
//            suspend {
//                test()
//            }.startCoroutine(RunSuspend())
//            println("run in ${Thread.currentThread()}")
//            coroutineScope {
//
//                println("run in ${Thread.currentThread()}")
//                delay(1000)
//
//            }
//            println("run End ${Thread.currentThread()}")
//
//        }
    }

    suspend fun test() {
        delay(1000)
        println("CoroutinesScopeActivity1 after 1 second in ${Thread.currentThread()} ")

    }

    private class RunSuspend : Continuation<Unit> {
        override val context: CoroutineContext
            get() = EmptyCoroutineContext + Dispatchers.IO

        override fun resumeWith(result: Result<Unit>) {
        }

    }


    public inline fun <R> mySuspend(noinline block: suspend () -> R): suspend () -> R = block

    fun ff(a: TTT.() -> Unit) {
        val b = object : TTT {
            override fun emit(emit: String) {
                println("emmmmmmmmmm $emit")
            }

        }
        b.a()

    }

    fun test1() {

        ff {
            emit("tttttt")
        }
    }

    public interface TTT {
        fun emit(emit: String)

    }

}