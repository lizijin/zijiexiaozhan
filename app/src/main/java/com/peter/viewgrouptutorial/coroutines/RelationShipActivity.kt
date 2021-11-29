package com.peter.viewgrouptutorial.coroutines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.peter.viewgrouptutorial.R
import kotlinx.coroutines.*

class RelationShipActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_relation_ship)
        println("RelationShipActivity1  scopeJob " + lifecycleScope.coroutineContext.job)
        val scopeJob = lifecycleScope.coroutineContext.job
        val launchJob = Job()
        println("RelationShipActivity1  launchJob $launchJob")

//        launchNewJob(launchJob, scopeJob)
        launchScopeJob(launchJob, scopeJob)

    }

    private fun launchNewJob(
        launchJob: CompletableJob,
        scopeJob: Job
    ) {
        lifecycleScope.launch(launchJob + CoroutineExceptionHandler { context, throwable ->
            println("RelationShipActivity1  exception catch by launchJob ")

        }) {
            delay(1000L)
            println("RelationShipActivity1 coroutineJob " + coroutineContext.job)
            println(
                "RelationShipActivity11 scopeChildren " + scopeJob.children.iterator().next()
            )


            println(
                "RelationShipActivity12 launchChildren " + launchJob.children.iterator().next()
            )


        }
    }

    private fun launchScopeJob(
        launchJob: CompletableJob,
        scopeJob: Job
    ) {


        lifecycleScope.launch(CoroutineExceptionHandler { context, throwable ->
            println("RelationShipActivity1  exception catch by scope ")

        }) {
            delay(1000L)
            println("RelationShipActivity1 coroutineJob " + coroutineContext.job)
            println(
                "RelationShipActivity11 scopeChildren " + scopeJob.children.iterator().next()
            )


            println(
                "RelationShipActivity12 launchChildren " + launchJob.children.iterator().next()
            )


        }
    }
}