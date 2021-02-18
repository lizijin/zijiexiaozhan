package com.peter.viewgrouptutorial.offsetproblem

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.peter.viewgrouptutorial.R

class OffsetProblemConfirmActivity : AppCompatActivity() {
    private lateinit var mOffsetButton: OffsetButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_offset_problem_confirm)
        mOffsetButton = findViewById(R.id.offset_button)
//        mOffsetButton.viewTreeObserver.addOnPreDrawListener {
//            println("mOffsetButton onPreDraw")
//            true
//        }
    }

    fun offsetBottom(view: View) {
        ViewCompat.offsetTopAndBottom(mOffsetButton, 100)
    }
}