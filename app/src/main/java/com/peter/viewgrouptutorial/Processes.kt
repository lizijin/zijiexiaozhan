package com.peter.viewgrouptutorial

import android.system.Os
import android.system.OsConstants
import java.io.BufferedReader
import java.io.FileReader

object Processes {
    @JvmStatic
    fun readProcessForkRealtimeMillis(): Long {
        val myPid = android.os.Process.myPid()
        val ticksAtProcessStart = readProcessStartTicks(myPid)
        // Min API 21, use reflection before API 21.
        // See https://stackoverflow.com/a/42195623/703646
        val ticksPerSecond = Os.sysconf(OsConstants._SC_CLK_TCK)
        return ticksAtProcessStart * 1000 / ticksPerSecond
    }

    // Benchmarked (with Jetpack Benchmark) on Pixel 3 running
    // Android 10. Median time: 0.13ms
    fun readProcessStartTicks(pid: Int): Long {
        val path = "/proc/$pid/stat"
        val stat = BufferedReader(FileReader(path)).use { reader ->
            reader.readLine()
        }
        val fields = stat.substringAfter(") ")
            .split(' ')
        return fields[19].toLong()
    }
}