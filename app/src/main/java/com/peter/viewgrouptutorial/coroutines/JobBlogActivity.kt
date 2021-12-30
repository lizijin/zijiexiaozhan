package com.peter.viewgrouptutorial.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.peter.viewgrouptutorial.R
import kotlinx.coroutines.*

class JobBlogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_blog)
//        case3()
//        exceptionWithJob()
//        exceptionWithSupervisorJob()
//        jobCancel()
//        cancelExceptionWithJob()
        invokeCompletion()
    }

    fun invokeCompletion() {

        val job = MainScope().launch {
            println("job start")
            delay(10000)
            println("job end")
        }
        MainScope().launch {
            delay(1000)
            job.cancel()
        }
        job.invokeOnCompletion {
            println("job invokeOnCompletion")
        }
    }

    fun jobCancel() {
        val job1 = MainScope().launch(Dispatchers.IO) {
            Thread.sleep(2000L)
            ensureActive()
            println("running in job1")
        }
        MainScope().launch {
            delay(1000)
            job1.cancel()
            println("running in job2")
        }
    }

    private fun exceptionWithJob() {
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            println("catch exception ${throwable.message}")
        }
        val mainScope = CoroutineScope(Dispatchers.Main + Job() + exceptionHandler)

        mainScope.launch {
            println("job1 start")
            delay(2000)
            println("job1 end")
        }

        mainScope.launch {
            println("job2 start")
            delay(1000)
            1 / 0
            println("job2 end")
        }

        mainScope.launch {
            println("job3 start")
            delay(2000)
            println("job3 end")
        }

    }

    private fun cancelExceptionWithJob() {
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            println("catch exception ${throwable.message}")
        }
        val mainScope = CoroutineScope(Dispatchers.Main + Job() + exceptionHandler)

        mainScope.launch {
            println("job1 start")
            delay(2000)
            println("job1 end")
        }

        mainScope.launch {
            println("job2 start")
            launch {
                println("job4 start")
                delay(2000)
                println("job4 end")
            }
            delay(1000)

            throw CancellationException()
            println("job2 end")
        }

        mainScope.launch {
            println("job3 start")
            delay(2000)
            println("job3 end")
        }

    }

    private fun exceptionWithSupervisorJob() {
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            println("catch exception ${throwable.message}")
        }
        val mainScope = CoroutineScope(Dispatchers.Main + SupervisorJob() + exceptionHandler)

        mainScope.launch {
            println("job1 start")
            delay(2000)
            println("job1 end")
        }

        mainScope.launch {
            println("job2 start")
            delay(1000)
            1 / 0
            println("job2 end")
        }

        mainScope.launch {
            println("job3 start")
            delay(2000)
            println("job3 end")
        }

    }

    private fun case3() {
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            println("catch exception ${throwable.message}")
        }
//       val mainScope =  CoroutineScope(Dispatchers.Main+ Job()+exceptionHandler)
        val mainScope = CoroutineScope(Dispatchers.Main + SupervisorJob() + exceptionHandler)

        val job1 = mainScope.launch(CoroutineName("job1")) {
            println("job1 start")
            delay(2000)
            println("job1 end")
        }

        mainScope.launch(CoroutineName("job2")) {
            println("job2 start")

            delay(1000)
            job1.cancel()
            println("job2 end")
        }

        mainScope.launch(CoroutineName("job3")) {
            println("job3 start")
            delay(2000)
            println("job3 end")
        }

    }

    fun case1() {
        val job = MainScope().launch {
            println("before delay")
            delay(1000L)
            println("after delay")
        }
    }
}