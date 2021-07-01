package com.peter.viewgrouptutorial.coroutines

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.peter.viewgrouptutorial.R

class CoroutinesActivity : AppCompatActivity() {
    private lateinit var mButton: Button
    private lateinit var mContent: TextView
    private lateinit var mTodoViewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines)
        mButton = findViewById(R.id.button)
        mTodoViewModel = ViewModelProviders.of(this).get(TodoViewModel::class.java)
        mContent = findViewById(R.id.content)
        mTodoViewModel.getTodo().observe(this) {
            mContent.text = it.toString()
        }
    }

    var count = 1
    fun startCoroutines(view: View) {
        mTodoViewModel.fetchTodo(count++)
    }


//    private suspend fun doWork(name: String): String {
//        return withContext(Dispatchers.IO) {
//            delay(1000)
//            "hello $name"
//        }
//    }
//
//    private suspend fun greetBack(greet: String) {
//        withContext(Dispatchers.Main) {
//            mButton.text = greet
//        }
//    }
}