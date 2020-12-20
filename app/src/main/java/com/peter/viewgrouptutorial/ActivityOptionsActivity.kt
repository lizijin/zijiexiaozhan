package com.peter.viewgrouptutorial

import android.app.ActivityOptions
import android.app.SharedElementCallback
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Pair
import android.view.View
import android.view.Window
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi

class ActivityOptionsActivity : AppCompatActivity() {
    companion object{
        var count =0
        var factor = 0.0
    }
    private lateinit var s1:SeekBar
    private lateinit var s2:SeekBar
    private lateinit var t1:TextView
    private lateinit var  t2 : TextView
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_options)
        s1 = findViewById(R.id.s1)
        s2 = findViewById(R.id.s2)
        t1 = findViewById(R.id.t1)
        t2 = findViewById(R.id.t2)
        s1.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                t1.text = "$progress"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
        s2.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                t2.text = "${progress.toDouble()/100.toDouble()}"

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
        setExitSharedElementCallback(
            object : SharedElementCallback() {
                override fun onSharedElementsArrived(
                    sharedElementNames: MutableList<String>?,
                    sharedElements: MutableList<View>?,
                    listener: OnSharedElementsReadyListener?
                ) {
                    super.onSharedElementsArrived(sharedElementNames, sharedElements, listener)
                    Log.d("zijiexiaozhan","1 exit onSharedElementsArrived $sharedElementNames")
                }

                override fun onSharedElementStart(
                    sharedElementNames: MutableList<String>?,
                    sharedElements: MutableList<View>?,
                    sharedElementSnapshots: MutableList<View>?
                ) {
                    super.onSharedElementStart(
                        sharedElementNames,
                        sharedElements,
                        sharedElementSnapshots
                    )
                    Log.d("zijiexiaozhan","1 exit onSharedElementStart $sharedElementNames")

                }

                override fun onSharedElementEnd(
                    sharedElementNames: MutableList<String>?,
                    sharedElements: MutableList<View>?,
                    sharedElementSnapshots: MutableList<View>?
                ) {
                    super.onSharedElementEnd(
                        sharedElementNames,
                        sharedElements,
                        sharedElementSnapshots
                    )

                    Log.d("zijiexiaozhan","1 exit onSharedElementEnd $sharedElementNames")

                }
            }

        )

        setEnterSharedElementCallback(object : SharedElementCallback() {

            override fun onSharedElementsArrived(
                sharedElementNames: MutableList<String>?,
                sharedElements: MutableList<View>?,
                listener: OnSharedElementsReadyListener?
            ) {
                super.onSharedElementsArrived(sharedElementNames, sharedElements, listener)
                Log.d("zijiexiaozhan","1 enter onSharedElementEnd $sharedElementNames")

            }

            override fun onSharedElementStart(
                sharedElementNames: MutableList<String>?,
                sharedElements: MutableList<View>?,
                sharedElementSnapshots: MutableList<View>?
            ) {
                super.onSharedElementStart(
                    sharedElementNames,
                    sharedElements,
                    sharedElementSnapshots
                )
                Log.d("zijiexiaozhan","1 enter onSharedElementStart $sharedElementNames")

            }

            override fun onSharedElementEnd(
                sharedElementNames: MutableList<String>?,
                sharedElements: MutableList<View>?,
                sharedElementSnapshots: MutableList<View>?
            ) {
                super.onSharedElementEnd(
                    sharedElementNames,
                    sharedElements,
                    sharedElementSnapshots
                )
                Log.d("zijiexiaozhan","1 enter onSharedElementEnd $sharedElementNames")

            }
        })


    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun gotoPictureActivity(view: View) {
        val activityOptions =
            ActivityOptions.makeSceneTransitionAnimation(
                this,
                Pair(findViewById(R.id.image_view), "xuanyu")

            )
        count = s1.progress
        factor = (s2.progress.toDouble()-50)/5.toDouble()
        startActivity(Intent(this, PictureActivity::class.java), activityOptions.toBundle())


    }
}