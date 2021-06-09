package com.peter.viewgrouptutorial

import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private val scope = MainScope()

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }

    fun asyncShowData() = scope.launch { // Is invoked in UI context with Activity's scope as a parent
        // actual implementation
    }

    suspend fun showIOData() {
        val data = withContext(Dispatchers.IO) {
            // compute data in background thread
        }
        withContext(Dispatchers.Main) {
            // Show data in UI
        }
    }
}