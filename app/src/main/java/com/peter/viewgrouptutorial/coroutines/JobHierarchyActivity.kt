package com.peter.viewgrouptutorial.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.peter.viewgrouptutorial.R
import kotlinx.coroutines.Job

class JobHierarchyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_hierarchy)
        val  job1 = Job()
        val job2 = Job(job1)

        val job5 = Job(job2)
        val job6 = Job(job2)
        val job7 = Job(job2)



        val job3 = Job(job1)

        val job8 = Job(job3)
        val job9 = Job(job3)
        val job10 = Job(job3)

        val job4 = Job(job1)

        val job11 = Job(job4)
        val job12 = Job(job4)
        val job13 = Job(job4)

    }
}