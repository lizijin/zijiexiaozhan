package com.peter.viewgrouptutorial.coroutines

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.peter.viewgrouptutorial.R

class CoroutinesActivity : AppCompatActivity() {
    private lateinit var mButton: Button
    private lateinit var mContent: TextView
    private lateinit var mMapContent: TextView
    private lateinit var mSwitchMapContent: TextView
    private lateinit var mEditTextView :EditText
    private lateinit var mTodoViewModel: TodoViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines)
        mButton = findViewById(R.id.button)
        mTodoViewModel = ViewModelProviders.of(this).get(TodoViewModel::class.java)
        mContent = findViewById(R.id.content)
        mMapContent = findViewById(R.id.map_content)
        mEditTextView = findViewById(R.id.edit_text_e)
        mSwitchMapContent = findViewById(R.id.switch_map_content)
        mTodoViewModel.getTodoNormal().observe(this) {
            mContent.text = it.toString()
        }

        mTodoViewModel.getTodoMap().observe(this) {
            mMapContent.text = it
        }

        mTodoViewModel.getTodoSwitchMap().observe(this) {
            mSwitchMapContent.text = "SwitchMap " + it.toString()

        }
    }

    var count = 1
    fun normalUseLiveData(view: View) {
        mTodoViewModel.fetchTodo(count++)
    }

    fun liveDataWithMap(view: View) {
        mTodoViewModel.fetchTodo(count++)

    }

    fun liveDataWithSwitchMap(view: View) {
        mTodoViewModel.setId(Integer.parseInt(mEditTextView.text.toString()))
//        mTodoViewModel.fetchTodoSwitchMap()
    }
}