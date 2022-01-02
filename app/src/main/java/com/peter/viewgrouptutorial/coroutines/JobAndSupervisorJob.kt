package com.peter.viewgrouptutorial.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.peter.viewgrouptutorial.R
import kotlinx.coroutines.*

class JobAndSupervisorJob : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_and_supervisor_job)
//        testJob()
//        testSupervisorJob()
        jobException()
        Thread.sleep(2000)
        supervisorJobException()

    }

    private fun jobException() {
        val parentExceptionHandler = CoroutineExceptionHandler { _, thowable ->
            println("xiaozhan parent handle exception ${thowable.message}")
        }

        val childExceptionHandler = CoroutineExceptionHandler { _, thowable ->
            println("xiaozhan child handle exception ${thowable.message}")
        }
        MainScope().launch(parentExceptionHandler) {
            launch(Job(coroutineContext[Job]) + childExceptionHandler) {
                delay(100)
                println("xiaozhan Job log")
                throw RuntimeException("something wrong")
            }
        }
    }

    private fun supervisorJobException() {
        val parentExceptionHandler = CoroutineExceptionHandler { _, thowable ->
            println("xiaozhan parent handle exception ${thowable.message}")
        }

        val childExceptionHandler = CoroutineExceptionHandler { _, thowable ->
            println("xiaozhan child handle exception ${thowable.message}")
        }
        MainScope().launch(parentExceptionHandler) { // --> 根协程
            launch(SupervisorJob(coroutineContext[Job]) + childExceptionHandler) {
                // --> 子协程
                delay(100)
                println("xiaozhan SupervisorJob log")
                throw RuntimeException("something wrong")
            }
        }
    }


    private fun testSupervisorJob() {
        val exceptionHandler = CoroutineExceptionHandler { _, thowable ->
            println("xiaozhan ${thowable.message}")
        }
        val coroutineScope = CoroutineScope(SupervisorJob() + exceptionHandler)
        coroutineScope.launch {
            delay(100)
            println("xiaozhan supervisorJob1 log")
            throw RuntimeException("something wrong")
        }
        coroutineScope.launch {
            delay(200)
            println("xiaozhan supervisorJob2 log")
        }
        coroutineScope.launch {
            delay(300)
            println("xiaozhan supervisorJob3 log")
        }
    }

    private fun testJob() {
        val exceptionHandler = CoroutineExceptionHandler { _, thowable ->
            println("xiaozhan ${thowable.message}")
        }
        val coroutineScope = CoroutineScope(Job() + exceptionHandler)
        coroutineScope.launch {
            delay(100)
            println("xiaozhan job1 log")
            throw RuntimeException("something wrong")
        }
        coroutineScope.launch {
            delay(200)
            println("xiaozhan job2 log")
        }
        coroutineScope.launch {
            delay(300)
            println("xiaozhan job3 log")
        }
    }
}