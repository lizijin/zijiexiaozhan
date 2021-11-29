package com.peter.viewgrouptutorial.coroutines

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.peter.viewgrouptutorial.R
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class CoroutinesSimpleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines1)
    }

    fun startCoroutines(view: View) {
        MainScope().launch {
            println("Hello Coroutines")
        }
    }

}
