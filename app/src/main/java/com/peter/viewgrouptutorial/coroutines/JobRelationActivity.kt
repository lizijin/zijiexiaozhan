package com.peter.viewgrouptutorial.coroutines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class JobRelationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        test1()
//        test2()
        test3()

    }

    private fun test3() {
//        MainScope().launch {
//            try {
//                coroutineScope {
//                    println("JobRelationActivity3 start")
//                    1 / 0
//                    println("JobRelationActivity3 end")
//
//                }
//            } catch (ex: Exception) {
//                println("JobRelationActivity3 catch exception")
//
//            }
//
//        }

        (MainScope() + CoroutineExceptionHandler { coroutineContext, throwable ->
            println("JobRelationActivity3 exception $throwable")
        }).launch {
            coroutineScope {
                println("JobRelationActivity3 start")
                1 / 0
                println("JobRelationActivity3 end")

            }

        }

    }

    private fun test2() {
        val job = SupervisorJob()
        (MainScope() + CoroutineExceptionHandler { coroutineContext, throwable ->
            println("JobRelationActivity2 exception $throwable")
        }).launch(job) {
            launch(job) {
                delay(2000L)
                println("JobRelationActivity2 I will  be printed")
            }
            launch {
                delay(1000L)
                throw Error("Some error")
            }
            launch {
                println("JobRelationActivity2 $coroutineContext")

                delay(2000L)
                println("JobRelationActivity2 I will not be printed")
            }
        }
    }

    private fun test1() {
        val parentJob = SupervisorJob()
        val mainScope =
            MainScope() + parentJob + CoroutineExceptionHandler { coroutineContext, throwable ->
                println("JobRelationActivity exception $throwable")
            }
        val job1 = mainScope.launch {
            launch {
                delay(1_000)
                1 / 0
                println("JobRelationActivity job11")
                delay(1_000)
            }
            launch {
                delay(1_000)
                println("JobRelationActivity job12")
                delay(1_000)
            }
            println("JobRelationActivity job1")
            delay(1_000)


        }
        val job2 = mainScope.launch {
            delay(1_000)
            println("JobRelationActivity job2")
            delay(1_000)

        }
        println("JobRelationActivity parentJob $parentJob job1 $job1 job2 $job2")
    }
}