package com.peter.viewgrouptutorial.jetpack.viewmodel

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.peter.viewgrouptutorial.R
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MyViewModelActivity : AppCompatActivity() {
    //    private val mViewModel by viewModels()
    private lateinit var  mViewModel:MyViewModel
    private lateinit var mGreetingLabel: TextView
    private val vm:MyViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("jiangbin onCreate $this")
        setContentView(R.layout.activity_my_view_model)
        mGreetingLabel = findViewById(R.id.greeting_label)
        val model: MyViewModel by viewModels()
        mViewModel = model
        model.getGreeting().observe(this,
            Observer<String> { t -> mGreetingLabel.text = t })

        runBlocking {
            launch {  }
        }



    }

    fun changeGreeting(view: View) {
        mViewModel.changeGreeting("Hi World~")
    }
}