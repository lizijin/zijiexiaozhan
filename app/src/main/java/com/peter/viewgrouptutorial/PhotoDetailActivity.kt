//package com.peter.viewgrouptutorial
//
//import android.os.Build
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.transition.ChangeBounds
//import android.transition.ChangeImageTransform
//import android.transition.TransitionSet
//import android.widget.ImageView
//import androidx.annotation.RequiresApi
//
//class PhotoDetailActivity : AppCompatActivity() {
//    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        val transitionSet = TransitionSet()
//        transitionSet.addTransition(ChangeBounds().apply { interpolator = BetterBounceInterpolator(ActivityOptionsActivity.count,ActivityOptionsActivity.factor) })
//        transitionSet.addTransition(ChangeImageTransform().apply { interpolator = BetterBounceInterpolator(ActivityOptionsActivity.count,ActivityOptionsActivity.factor) })
//        transitionSet.setMatchOrder(TransitionSet.MATCH_ITEM_ID)
//        window.sharedElementEnterTransition = transitionSet
//
//        setContentView(R.layout.activity_photo_detail)
//        val photoView = findViewById<ImageView>(R.id.photo_detail_view)
//       val imageResource =  intent.getIntExtra("imageResource",R.drawable.xuanyu)
//        photoView.setImageResource(imageResource)
//    }
//}