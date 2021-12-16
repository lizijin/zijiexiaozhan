package com.peter.viewgrouptutorial.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.peter.viewgrouptutorial.R
import kotlinx.coroutines.*

class CancelJobActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cancel_job)
        case3()
    }

    private fun case1() {
        val scope = MainScope()
        scope.launch(Job()) {
            launch {
                delay(2000L)
                println("CancelJobActivity job1 finished")
                scope.cancel()

            }
            launch {
                delay(3000L)
                println("CancelJobActivity job2 finished")
            }
        }
    }

    private fun case2() {
        val scope = MainScope()
        scope.launch {
            launch {
                delay(2000L)
                println("CancelJobActivity job1 finished")
                scope.cancel()

            }
            launch {
                delay(3000L)
                println("CancelJobActivity job2 finished")
            }
        }
    }

    private fun case3() {
        val scope = MainScope()
        scope.launch(SupervisorJob()) {
            launch {
                delay(2000L)
                println("CancelJobActivity job1 finished")
                scope.cancel()

            }
            launch {
                delay(3000L)
                println("CancelJobActivity job2 finished")
            }
        }
    }

}