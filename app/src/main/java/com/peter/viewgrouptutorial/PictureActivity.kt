package com.peter.viewgrouptutorial

import android.app.ActivityOptions
import android.app.SharedElementCallback
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.ChangeImageTransform
import android.transition.TransitionSet
import android.util.Log
import android.util.Pair
import android.view.MotionEvent
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.annotation.RequiresApi

class PictureActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
//        return super.dispatchTouchEvent(ev)
        val activityOptions =
            ActivityOptions.makeSceneTransitionAnimation(
                this,
                Pair(findViewById(R.id.photo_view), "xuanyu")

            )
        startActivity(Intent(this, PictureActivity::class.java), activityOptions.toBundle())
        return  true
    }
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val transitionSet = TransitionSet()
//        transitionSet.addTransition(ChangeBounds().apply { interpolator = BetterBounceInterpolator(ActivityOptionsActivity.count,ActivityOptionsActivity.factor) })
//        transitionSet.addTransition(ChangeImageTransform().apply { interpolator = BetterBounceInterpolator(ActivityOptionsActivity.count,ActivityOptionsActivity.factor) })
//
//        window.sharedElementEnterTransition = transitionSet
        setContentView(R.layout.activity_picture)


        setExitSharedElementCallback(
            object : SharedElementCallback() {
                override fun onSharedElementsArrived(
                    sharedElementNames: MutableList<String>?,
                    sharedElements: MutableList<View>?,
                    listener: OnSharedElementsReadyListener?
                ) {
                    super.onSharedElementsArrived(sharedElementNames, sharedElements, listener)
                    Log.d("zijiexiaozhan","2 exit onSharedElementsArrived +$sharedElementNames")
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
                    Log.d("zijiexiaozhan","2 exit onSharedElementStart $sharedElementNames")

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

                    Log.d("zijiexiaozhan","2 exit onSharedElementEnd $sharedElementNames $sharedElements")

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
                Log.d("zijiexiaozhan","2 enter onSharedElementEnd $sharedElementNames")

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
                Log.d("zijiexiaozhan","2 enter onSharedElementStart $sharedElementNames")

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
                Log.d("zijiexiaozhan","2 enter onSharedElementEnd $sharedElementNames $sharedElements")
                val transitionSet = TransitionSet()
                transitionSet.addTransition(ChangeBounds().apply { interpolator = LinearInterpolator() })
                transitionSet.addTransition(ChangeImageTransform().apply { interpolator = LinearInterpolator() })

                window.sharedElementEnterTransition = transitionSet
            }
        })

    }
}