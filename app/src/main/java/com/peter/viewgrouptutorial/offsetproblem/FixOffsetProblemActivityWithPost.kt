package com.peter.viewgrouptutorial.offsetproblem

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.peter.viewgrouptutorial.R

class FixOffsetProblemActivityWithPost : AppCompatActivity() {
    private lateinit var rootLayout: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fix_offset_problem_with_post)
        rootLayout = findViewById(R.id.root_layout)
    }

    var count: Int = 0
    fun addAndOffset(view: View) {
        var newButton = OffsetButton(this).apply {
            text = "This an new added Button ${count++}"
            setBackgroundColor(Color.GREEN)
        }
        rootLayout.addView(newButton, ViewGroup.LayoutParams.MATCH_PARENT, 500)
        newButton.post {
            ViewCompat.offsetTopAndBottom(newButton, 100)
        }
    }
}