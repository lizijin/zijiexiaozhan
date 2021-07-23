package com.peter.viewgrouptutorial.jetpack.navigation

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.peter.viewgrouptutorial.R
import java.lang.ref.ReferenceQueue
import java.lang.ref.WeakReference

class NavigationActivity : AppCompatActivity() {
    lateinit var activity: WeakReference<Activity>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        activity = WeakReference(this, ReferenceQueue())
    }
}