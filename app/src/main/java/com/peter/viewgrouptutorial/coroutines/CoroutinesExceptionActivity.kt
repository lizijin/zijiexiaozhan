package com.peter.viewgrouptutorial.coroutines

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.peter.viewgrouptutorial.R
import kotlinx.coroutines.*
import kotlin.concurrent.thread
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * 参考文章 https://medium.com/androiddevelopers/exceptions-in-coroutines-ce8da1ec060c
 */
class CoroutinesExceptionActivity : AppCompatActivity() {
    lateinit var coroutineScopeButton: Button
    lateinit var supervisorCoroutineScopeButton: Button
    lateinit var resultTextView1: TextView
    lateinit var resultTextView2: TextView
    lateinit var resultTextView3: TextView
    lateinit var throwExceptionCheckBox: CheckBox
    lateinit var catchExceptionCheckBox: CheckBox

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("CoroutinesExceptionActivity catch exception in handler " + Thread.currentThread())
//        throw  throwable
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines_exception)

        coroutineScopeButton = findViewById(R.id.coroutines_scope_button)

        resultTextView1 = findViewById(R.id.result_text_view1)
        resultTextView2 = findViewById(R.id.result_text_view2)
        resultTextView3 = findViewById(R.id.result_text_view3)



        throwExceptionCheckBox = findViewById(R.id.throw_exception_check_box)
        catchExceptionCheckBox = findViewById(R.id.catch_exception_check_box)

        supervisorCoroutineScopeButton = findViewById(R.id.supervisor_coroutines_scope_button)


        lifecycleScope
        coroutineScopeButton.setOnClickListener {
            val job = Job()
            val scope =
                CoroutineScope(job + exceptionHandler + Dispatchers.Main + CoroutineName("test0"))
            println("CoroutinesExceptionActivity job1 $job")
            println("CoroutinesExceptionActivity scope $scope")

            scope.launch {
                var count = 0
                while (true) {
                    delay(100)
                    println("CoroutinesExceptionActivity in first " + count++)
                }
            }

            val job2 = scope.launch(CoroutineName("test1")) {
                println("CoroutinesExceptionActivity scope2 $this")

                val job3 = scope.launch {
                    println("CoroutinesExceptionActivity scope3 $this")

                    while (true) {
                        println("CoroutinesExceptionActivity in 1111 ")

                        delay(200)
                    }
                }
                val newScope = CoroutineScope(SupervisorJob()+exceptionHandler)
                val job4 = newScope.launch {
                    var count = 0

                    while (true) {
                        println("CoroutinesExceptionActivity in 2222 ")

                        while (true) {
                            delay(5000)
                            println("CoroutinesExceptionActivity in second ")
                            count++ / 0
                        }                    }
                }
                println("CoroutinesExceptionActivity job3 $job3")
                println("CoroutinesExceptionActivity job4 $job4")
                while (true) {
                    delay(500)
                    println("CoroutinesExceptionActivity in second ")
                }

            }
            println("CoroutinesExceptionActivity job2 $job2")


        }

    }

    private suspend fun showText(text: String) {
        withContext(Dispatchers.Main) {
            resultTextView1.text = text
        }
    }

    private suspend fun doWorkInCoroutines() =
        suspendCoroutine<String> {
            thread {
                Thread.sleep(1000)

                if (throwExceptionCheckBox.isChecked) {
                    it.resumeWithException(RuntimeException("force throw some exception"))
                } else {
                    it.resumeWith(Result.success("I am ok"))
                }
            }
        }
}