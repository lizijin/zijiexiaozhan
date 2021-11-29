package com.peter.viewgrouptutorial.coroutines

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.peter.viewgrouptutorial.R
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecursionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recursion)

    }

    fun depth(t: TreeNode?): Int =
        if (t == null) 0 else maxOf(
            depth(t.leftNode), // recursive call one
            depth(t.rightNode) // recursive call two
        ) + 1

    private suspend fun suspendDepth(t: TreeNode?): Int {
        return if (t == null) 0 else maxOf(
            suspendDepth(t.leftNode), // recursive call one
            suspendDepth(t.rightNode) // recursive call two
        ) + 1
    }

    private fun generateTree(): TreeNode {
        val n = 100_000
        return generateSequence(TreeNode(null, null)) { prev ->
            TreeNode(prev, null)

        }.take(n).last()
    }

    class TreeNode(val leftNode: TreeNode?, val rightNode: TreeNode?)

    fun normalCall(view: View) {
        findViewById<TextView>(R.id.result_text).text = "result " + depth(generateTree())
    }

    fun suspendCall(view: View) {
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            println("RecursionActivity catch exception in handler " + Thread.currentThread())
            throwable.printStackTrace()
//        throw  throwable
        }

        lifecycleScope.launch(Dispatchers.IO+exceptionHandler) {
            val result = suspendDepth(generateTree())
            withContext(Dispatchers.Main) {
                findViewById<TextView>(R.id.result_text).text = "height $result"
            }

        }
    }
}