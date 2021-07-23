package com.peter.viewgrouptutorial.jetpack.viewmodel

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.peter.viewgrouptutorial.R

class MyViewModelActivity : AppCompatActivity() {
    private val mViewModel2: MyViewModel by viewModels()
    private lateinit var mViewModel: MyViewModel
    private lateinit var mGreetingLabel: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("jiangbin onCreate $this")
        setContentView(R.layout.activity_my_view_model)
        mGreetingLabel = findViewById(R.id.greeting_label)
        val model: MyViewModel by viewModels()

        mViewModel = ViewModelProvider(this)[MyViewModel::class.java]

        liveData<String> {
//            emitSource(MutableLiveData("dddd"))
            emit("ddddd")
        }

        println("mViewModel2 $mViewModel2 mViewModel $mViewModel model $model")

//                mViewModel = model
        mViewModel.getGreeting().observe(this,
            Observer<String> { t -> mGreetingLabel.text = t })
//
//        runBlocking {
//            launch {  }
//        }

//        CoroutineScope().
    }

    fun changeGreeting(view: View) {
        mViewModel.changeGreeting("Hi World~")
    }
}