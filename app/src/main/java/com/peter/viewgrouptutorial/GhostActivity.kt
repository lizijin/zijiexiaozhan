package com.peter.viewgrouptutorial

import android.graphics.Color
import android.graphics.Matrix
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method


class GhostActivity : AppCompatActivity() {
    private lateinit var mGhostView: View
    private lateinit var mHelloWorldView: TextView

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ghost)
        mHelloWorldView = findViewById(R.id.hello_world_text)
//        val colorDrawable = ColorDrawable(Color.BLUE)
//        colorDrawable.alpha = 100
//        colorDrawable.setBounds(0, 0, 200, 200)
//        mHelloWorldView.overlay.add(colorDrawable)
        mHelloWorldView.setBackgroundColor(Color.BLUE)
        val frameLayout =
            findViewById<View>(R.id.frameLayout) as FrameLayout
        frameLayout.right = frameLayout.left + 500
        frameLayout.bottom = frameLayout.top + 200
        this@GhostActivity.addGhost(mHelloWorldView, frameLayout)
        mHelloWorldView.setOnClickListener{
            Toast.makeText(this,"HI",Toast.LENGTH_SHORT).show()
        }

        findViewById<View>(R.id.textview2)
            .setOnClickListener {
//                mHelloWorldView.visibility = if (mHelloWorldView.visibility == View.VISIBLE) {
//                    run {
//                        mHelloWorldView.setText("Origin is InVisible")
//                    }
//                    View.INVISIBLE
//                } else {
//                    run {
//                        mHelloWorldView.setText("Origin is Visible")
//                    }
//                    View.VISIBLE
//                }
                mGhostView.visibility = if (mGhostView.visibility == View.VISIBLE) {
                    View.INVISIBLE
                } else {
                    View.VISIBLE

                }
                frameLayout.invalidate()
                Toast.makeText(
                    this,
                    "ghostV ${mGhostView.visibility} TextViewV ${mHelloWorldView.visibility}",
                    Toast.LENGTH_SHORT
                ).show()

            }
    }

    private fun addGhost(view: View, viewGroup: ViewGroup) {
        try {
            val ghostViewClass =
                Class.forName("android.view.GhostView")
            val addGhostMethod: Method = ghostViewClass.getMethod(
                "addGhost", View::class.java,
                ViewGroup::class.java, Matrix::class.java
            )
            val ghostView = addGhostMethod.invoke(null, view, viewGroup, null) as View
            ghostView.setBackgroundColor(Color.RED)
            mGhostView = ghostView
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }
    }
}