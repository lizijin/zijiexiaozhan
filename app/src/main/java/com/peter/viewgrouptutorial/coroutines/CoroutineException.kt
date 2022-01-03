package com.peter.viewgrouptutorial.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.peter.viewgrouptutorial.R
import kotlinx.coroutines.*

class CoroutineException : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine_exception)
//        callThirdApi6()
    }

     fun case1(view: View) {
        try {
            throw NullPointerException("null pointer exception")
        } catch (exception: Exception) {
            println("caught ${exception.message}")
        }
    }

     fun case2(view: View) {
        val scope = CoroutineScope(Dispatchers.Main + Job())
        scope.launch {
            try {
                delay(100)
                throw NullPointerException("null pointer exception")
            } catch (exception: Exception) {
                println("caught ${exception.message}")
            }
        }
    }

     fun case3(view: View) {
        val scope = CoroutineScope(Dispatchers.Main + Job())

        scope.launch {

            thirdApi(this)
        }
    }

     fun case4(view: View) {
        val scope = CoroutineScope(Dispatchers.Main + Job())
        scope.launch {
            try {
                thirdApi(this)
            } catch (exception: Exception) {
                println("caught exception ${exception.message}")
            }
        }
    }

     fun case5(view: View) {
        val scope = CoroutineScope(Dispatchers.Main + Job())
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            println("caught exception ${throwable.message}")
        }

        scope.launch(exceptionHandler) {
            thirdApi(this)
        }
    }


     fun case6(view: View) {
        val scope = CoroutineScope(Dispatchers.Main + Job())

        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            println("caught exception ${throwable.message}")
        }
        scope.launch {
            launch(exceptionHandler) {
                delay(100)
                throw NullPointerException("null pointer exception")
            }
        }
    }

     fun case7(view: View) {
        val scope = CoroutineScope(Dispatchers.Main + Job())

        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            println("caught exception in handler ${throwable.message}")
        }
        scope.launch {
            try {
                coroutineScope {
                    launch(exceptionHandler) {
                        delay(100)
                        throw NullPointerException("null pointer exception")
                    }
                }
            } catch (exception: Exception) {
                println("caught exception in try catch exception ${exception.message}")
            }
        }
    }

    fun case10(view: View) {
        val scope = CoroutineScope(Dispatchers.Main + Job())

        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            println("caught exception in handler ${throwable.message}")
        }
        scope.launch {
            try {
                supervisorScope {
                    launch(exceptionHandler) {
                        delay(100)
                        throw NullPointerException("null pointer exception")
                    }
                }
            } catch (exception: Exception) {
                println("caught exception in try catch exception ${exception.message}")
            }
        }
    }

    fun case8(view: View) {
        val scope = CoroutineScope(Dispatchers.Main + Job())

        val innerExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            println("caught exception in inner handler ${throwable.message}")
        }

        val outerExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            println("caught exception in outer handler ${throwable.message}")
        }
        scope.launch(outerExceptionHandler) {
                coroutineScope {
                    launch(innerExceptionHandler) {
                        delay(100)
                        throw NullPointerException("null pointer exception")
                    }
                }
        }
    }

    fun case9(view: View) {
        val scope = CoroutineScope(Dispatchers.Main + Job())

        val innerExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            println("caught exception in inner handler ${throwable.message}")
        }

        val outerExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            println("caught exception in outer handler ${throwable.message}")
        }
        scope.launch(outerExceptionHandler) {
            supervisorScope {
                launch(innerExceptionHandler) {
                    delay(100)
                    throw NullPointerException("null pointer exception")
                }
            }
        }
    }

    fun case11(view: View) {
        val scope = CoroutineScope(Dispatchers.Main + Job())

        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            println("caught exception in handler ${throwable.message}")
        }
        scope.launch {
            try {
                withContext(Dispatchers.IO) {
                    println("null pointer exception 3333")

                    launch(exceptionHandler) {
                        delay(200)
                        println("null pointer exception 111")
                        throw NullPointerException("null pointer exception")
                    }
                    launch(exceptionHandler) {
                        delay(100)
                        println("null pointer exception 222")

                    }
                    println("null pointer exception 444")
                }
                println("null pointer exception 555")

            } catch (exception: Exception) {
                println("caught exception in try catch exception ${exception.message}")
            }
        }
    }

    private fun thirdApi(coroutineScope: CoroutineScope) {
        coroutineScope.launch {
            delay(100)
            throw NullPointerException("null pointer exception")
        }
    }
}