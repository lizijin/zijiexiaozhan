package com.peter.viewgrouptutorial

import android.animation.AnimatorSet
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class AnimatedSubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animated_sub)


    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0,R.anim.slide_out_right)

    }

    fun onClick(view: View) {
        val headRect = Rect()
        val contentRect = Rect()
        findViewById<View>(R.id.head_text).getGlobalVisibleRect(headRect)
        findViewById<View>(R.id.content_text).getGlobalVisibleRect(contentRect)
        Toast.makeText(this, "$headRect $contentRect",Toast.LENGTH_LONG).show()
    }
}