package com.peter.viewgrouptutorial.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.*

class JobRelationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val parentJob = SupervisorJob()
        val mainScope = MainScope()+ parentJob +CoroutineExceptionHandler { coroutineContext, throwable ->
            println("JobRelationActivity exception $throwable")
        }
       val job1 =  mainScope.launch {
           launch {
               delay(1_000)
               1/0
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
        val job2 =  mainScope.launch {
            delay(1_000)
            println("JobRelationActivity job2")
            delay(1_000)

        }
        println("JobRelationActivity parentJob $parentJob job1 $job1 job2 $job2")

//        job.cancel("exception",NullPointerException())
//        mainScope.cancel()
    }
}