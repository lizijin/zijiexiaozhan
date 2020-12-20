package com.peter.viewgrouptutorial

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

class BottomNavigation : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.getOrCreateBadge(R.id.navigation_home).number = 10
        Handler().postDelayed(Runnable {
            val smallLabel: TextView = navView.findViewById(R.id.smallLabel)
            smallLabel.visibility = View.VISIBLE
            val largeLabel: TextView = navView.findViewById(R.id.largeLabel)
            largeLabel.visibility = View.GONE
        }, 2000)


//        val navController = findNavController(R.id.nav_host_fragment)
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)
    }
}