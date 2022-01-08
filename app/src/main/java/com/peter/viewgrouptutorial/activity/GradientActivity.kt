package com.peter.viewgrouptutorial.activity

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.peter.viewgrouptutorial.*

class GradientActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gradient)
        initImageView1()
        initImageView2()

       println("jiangbinbin "+resources.getDisplayMetrics().densityDpi)

        twoGradientSrc()
        twoGradientBackground()

        cornerDemo()

        threeGradient()
        twoGradientBackgroundAngle()
//        val solidColor = ColorStateList.valueOf(Color.parseColor("#ff0000"))
//        val gradient = Gradient.Builder().apply {
//            this.gradientColors(IntArray(2).apply {
//                this[0]=Color.parseColor("#ff0000")
//                this[1] = Color.parseColor("#00ff00")
//            })
//            this.gradientRadius(10f)
//        }.build()
//
//        val corner = Corner.Builder().apply {
//            this.radius(40f)
//            this.radii(topLeftRadius = 100f)
//        }.build()
//
//        val stroke = Stroke.Builder().apply {
//            setStroke(10, ColorStateList.valueOf(Color.parseColor("#890800")),10f,20f)
//        }.build()
//
//        val padding = Padding.Builder().setPadding(top = 100).build()
//        findViewById<TextView>(R.id.imageview2).setBackgroundDrawable(
//            CodeGradientDrawable.Builder().apply {
//                color( solidColor)
//                gradient(gradient)
//                stroke(stroke )
//                corner(corner)
//                padding(padding)
//            }.build()
//        )
    }




    // 演示solidColor作为src
    private fun initImageView1() {
        findViewById<ImageView>(R.id.imageview1).setImageResource(R.drawable.gradient_test1)

        val drawable = CodeGradientDrawable.Builder().apply {
            theme(theme)
            size(100,100)
            solidColor(ColorStateList.valueOf(Color.parseColor("#ff0000")))
        }.build()

        findViewById<ImageView>(R.id.imageview2).setImageDrawable(drawable)

    }

    // 演示solidColor作为背景
    private fun initImageView2() {
        findViewById<ImageView>(R.id.imageview3).setBackgroundResource(R.drawable.gradient_test1)

        val drawable = CodeGradientDrawable.Builder().apply {
            theme(theme)
            size(100,100)
            solidColor(ColorStateList.valueOf(Color.parseColor("#ff0000")))
        }.build()

        findViewById<ImageView>(R.id.imageview4).background= drawable
    }

    // 演示渐变作为src
    private fun twoGradientSrc() {
        findViewById<ImageView>(R.id.imageview5).setImageResource(R.drawable.gradient_test2)

        val gradient = Gradient.Builder().apply {

            val colors = IntArray(2).apply {
                this[0]= Color.parseColor("#ff0000")
                this[1]= Color.parseColor("#00ff00")
            }
            this.gradientColors(colors)
        }.build()
        val drawable = CodeGradientDrawable.Builder().apply {
            theme(theme)

//            size(100,100)
            gradient(gradient)
        }.build()

        findViewById<ImageView>(R.id.imageview6).setImageDrawable(drawable)

    }

    // 演示渐变作为背景
    private fun twoGradientBackground() {
        findViewById<ImageView>(R.id.imageview7).setBackgroundResource(R.drawable.gradient_test2)

        val gradient = Gradient.Builder().apply {
            val colors = IntArray(2).apply {
                this[0]= Color.parseColor("#ff0000")
                this[1]= Color.parseColor("#00ff00")
            }
            this.gradientColors(colors)
        }.build()
        val drawable = CodeGradientDrawable.Builder().apply {
            theme(theme)

            size(100,100)
            gradient(gradient)
        }.build()

        findViewById<ImageView>(R.id.imageview8).background = drawable

    }

    private fun cornerDemo() {
        findViewById<ImageView>(R.id.imageview9).setImageResource(R.drawable.gradient_test3)

        val gradient = Gradient.Builder().apply {
            val colors = IntArray(2).apply {
                this[0]= Color.parseColor("#ff0000")
                this[1]= Color.parseColor("#00ff00")
            }
            this.gradientColors(colors)
        }.build()

        val corner = Corner.Builder().apply {
            this.radius(12f)
        }.build()

        val drawable = CodeGradientDrawable.Builder().apply {
            theme(theme)

            size(100,100)
            gradient(gradient)
            corner(corner)
        }.build()

        findViewById<ImageView>(R.id.imageview10).setImageDrawable(drawable)
    }

    private fun threeGradient() {
        findViewById<ImageView>(R.id.imageview11).setImageResource(R.drawable.gradient_test4)

        val gradient = Gradient.Builder().apply {
            val colors = IntArray(3).apply {
                this[0]= Color.parseColor("#ff0000")
                this[1]= Color.parseColor("#0000ff")
                this[2]= Color.parseColor("#00ff00")
            }
            this.gradientColors(colors)
        }.build()

        val corner = Corner.Builder().apply {
            this.radius(12f)
        }.build()

        val drawable = CodeGradientDrawable.Builder().apply {
            theme(theme)

            size(100,100)
            gradient(gradient)
            corner(corner)
        }.build()

        findViewById<ImageView>(R.id.imageview12).setImageDrawable(drawable)
    }

    // 演示渐变作为背景
    private fun twoGradientBackgroundAngle() {
        findViewById<ImageView>(R.id.imageview13).setBackgroundResource(R.drawable.gradient_test5)

        val gradient = Gradient.Builder().apply {

            val colors = IntArray(2).apply {
                this[0]= Color.parseColor("#ff0000")
                this[1]= Color.parseColor("#00ff00")
            }
            this.gradientColors(colors)
            this.orientation(GradientDrawable.Orientation.BL_TR)
        }.build()
        val drawable = CodeGradientDrawable.Builder().apply {
            theme(theme)
            size(100,100)
            gradient(gradient)
        }.build()

        findViewById<ImageView>(R.id.imageview14).background = drawable

    }
}