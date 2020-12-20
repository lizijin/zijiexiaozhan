package com.peter.viewgrouptutorial

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Explode
import android.transition.Scene
import android.transition.TransitionManager
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.RequiresApi

class SceneActivity : AppCompatActivity() {
    private lateinit var sceneRoot: FrameLayout
    private lateinit var scene1: Scene
    private lateinit var scene2: Scene

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scene)
        sceneRoot = findViewById(R.id.scene_root)
        scene1 = Scene.getSceneForLayout(sceneRoot, R.layout.scene1, this)
        scene2 = Scene.getSceneForLayout(sceneRoot, R.layout.scene2, this)
    }

    @SuppressLint("NewApi")
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun goScene1(view: View) {
        TransitionManager.go(scene1, Explode())
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun goScene2(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            TransitionManager.go(scene2, Explode())
        }

    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun changeBounds(view: View) {
        val view1 = findViewById<View>(R.id.view1)
        TransitionManager.beginDelayedTransition(sceneRoot)
        val layoutParams = view1.layoutParams
        layoutParams.width = 1000
        layoutParams.height = 1000
        view1.layoutParams = layoutParams
    }
}