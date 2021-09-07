package com.peter.viewgrouptutorial

import com.alibaba.android.alpha.Task

class MyTask(val name: String, val sleepTime: Long) : Task(name) {
    override fun run() {
        println("zijiexiaozhan alpha $name start ${Thread.currentThread().name}")
        Thread.sleep(sleepTime)
        println("zijiexiaozhan alpha $name end ${Thread.currentThread().name}")
    }
}