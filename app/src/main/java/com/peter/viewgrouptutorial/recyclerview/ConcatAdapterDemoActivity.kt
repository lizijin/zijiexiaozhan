package com.peter.viewgrouptutorial.recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.peter.viewgrouptutorial.R

class ConcatAdapterDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_concat_adapter_demo)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val config = ConcatAdapter.Config.Builder().setIsolateViewTypes(false).build()
        recyclerView.adapter = ConcatAdapter(
            config,
            TextAdapter(),
            ButtonAdapter()
        )
    }
}