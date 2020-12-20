package com.peter.viewgrouptutorial

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class VisibilityChangedActivity:AppCompatActivity() {
    lateinit var viewGroup:MyViewGroup
    lateinit var view:View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visibility_changed)
       val rootFrameLayout =  findViewById<FrameLayout>(R.id.root_frameLayout)
        val viewGroup = MyViewGroup(this)
        this.viewGroup = viewGroup
        viewGroup.setBackgroundColor(Color.RED)
        val view = MyView(this)
        this.view = view
        view.setBackgroundColor(Color.YELLOW)
        viewGroup.addView(view)
        rootFrameLayout.addView(viewGroup)
        view.layoutParams.height=200


    }

    fun changeVisibility(view: View) {

        viewGroup.visibility= if(viewGroup.visibility==View.VISIBLE){
            View.INVISIBLE
        }else{
            View.VISIBLE
        }

    }
     class MyViewGroup(context: Context?) : LinearLayout(context) {

         override fun onVisibilityChanged(changedView: View, visibility: Int) {
             super.onVisibilityChanged(changedView, visibility)
             Log.d("zijiexiaozhan","$changedView onVisibilityChanged in ViewGroup ")
         }

     }
     class MyView(context: Context?):View(context){
         override fun onVisibilityChanged(changedView: View, visibility: Int) {
             super.onVisibilityChanged(changedView, visibility)
             Log.d("zijiexiaozhan","$changedView onVisibilityChanged  in View")

         }
     }

    fun changeChildVisibility(view: View) {
        this.view.visibility= if(this.view.visibility==View.VISIBLE){
            View.INVISIBLE
        }else{
            View.VISIBLE
        }
    }
}