package com.peter.viewgrouptutorial.activity

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.peter.viewgrouptutorial.*
import com.peter.viewgrouptutorial.drawable.*

class GradientActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gradient)
        initImageView1()
        initImageView2()

        println("jiangbinbin " + resources.getDisplayMetrics().densityDpi)

        twoGradientSrc()
        twoGradientBackground()

        cornerDemo()

        threeGradient()
        twoGradientBackgroundAngle()

        val codeColor = CodeColorStateList.Builder().apply {
            this.addSelectorItem(SelectorColorItem.Builder().apply {
                this.color(Color.parseColor("#ff0000"))
                this.state(android.R.attr.state_pressed)
                this.state(-android.R.attr.state_checked)
            })
            this.addSelectorItem(SelectorColorItem.Builder().apply {
                this.color(Color.parseColor("#00ff00"))
                this.state(-android.R.attr.state_pressed)
                this.state(android.R.attr.state_checked)
            })
            this.addSelectorItem(SelectorColorItem.Builder().apply {
                this.color(Color.parseColor("#0000ff"))
            })
        }.build()

        val codeColor2 = CodeColorStateList.Builder().apply {
            this.addSelectorItem(SelectorColorItem.Builder().apply {
                this.color(Color.parseColor("#ff0000"))
                this.state(-android.R.attr.state_checked)
                this.state(android.R.attr.state_pressed)
            })
            this.addSelectorItem(SelectorColorItem.Builder().apply {
                this.color(Color.parseColor("#00ff00"))
                this.state(-android.R.attr.state_pressed)
                this.state(android.R.attr.state_checked)
            })
            this.addSelectorItem(SelectorColorItem.Builder().apply {
                this.color(Color.parseColor("#0000ff"))
            })
        }.build()

        println("xiaozhan equals ${codeColor == codeColor2}")

        findViewById<TextView>(R.id.textview21).setTextColor(codeColor)
    }


    // 演示solidColor作为src
    private fun initImageView1() {
        findViewById<ImageView>(R.id.imageview1).setImageResource(R.drawable.gradient_test1)

        val drawable = CodeGradientDrawable.Builder(this).apply {
            size(width = 100, height = 100)
            solidColor(CodeColorStateList.valueOf(Color.parseColor("#ff0000")))
        }.build()

        findViewById<ImageView>(R.id.imageview2).setImageDrawable(drawable)

    }

    // 演示solidColor作为背景
    private fun initImageView2() {
        findViewById<ImageView>(R.id.imageview3).setBackgroundResource(R.drawable.gradient_test1)

        val drawable = CodeGradientDrawable.Builder(this).apply {
            size(width = 100, height = 100)
            solidColor(CodeColorStateList.valueOf(Color.parseColor("#ff0000")))
        }.build()

        findViewById<ImageView>(R.id.imageview4).background = drawable
    }

    // 演示渐变作为src
    private fun twoGradientSrc() {
        findViewById<ImageView>(R.id.imageview5).setImageResource(R.drawable.gradient_test2)

        val gradient = Gradient.Builder(this@GradientActivity).apply {

            val colors = IntArray(2).apply {
                this[0] = Color.parseColor("#ff0000")
                this[1] = Color.parseColor("#00ff00")
            }
            this.gradientColors(colors)
        }
        val drawable = CodeGradientDrawable.Builder(this).apply {

//            size(100,100)
            gradient(gradient)
        }.build()

        findViewById<ImageView>(R.id.imageview6).setImageDrawable(drawable)

    }

    // 演示渐变作为背景
    private fun twoGradientBackground() {
        findViewById<ImageView>(R.id.imageview7).setBackgroundResource(R.drawable.gradient_test2)

        val gradient = Gradient.Builder(this@GradientActivity).apply {
            val colors = IntArray(2).apply {
                this[0] = Color.parseColor("#ff0000")
                this[1] = Color.parseColor("#00ff00")
            }
            this.gradientColors(colors)
        }
        val drawable = CodeGradientDrawable.Builder(this@GradientActivity).apply {

            size(width = 100, height = 100)
            gradient(gradient)
        }.build()

        findViewById<ImageView>(R.id.imageview8).background = drawable

    }

    private fun cornerDemo() {
        findViewById<ImageView>(R.id.imageview9).setImageResource(R.drawable.gradient_test3)

        val gradient = Gradient.Builder(this@GradientActivity).apply {
            val colors = IntArray(2).apply {
                this[0] = Color.parseColor("#ff0000")
                this[1] = Color.parseColor("#00ff00")
            }
            this.gradientColors(colors)
        }

        val corner = Corner.Builder(this@GradientActivity).apply {
            this.radius(12f)
        }

        val drawable = CodeGradientDrawable.Builder(this@GradientActivity).apply {

            size(width = 100, height = 100)
            gradient(gradient)
            corner(corner)
        }.build()

        findViewById<ImageView>(R.id.imageview10).setImageDrawable(drawable)
    }

    private fun threeGradient() {
        findViewById<ImageView>(R.id.imageview11).setImageResource(R.drawable.gradient_test4)

        val gradient = Gradient.Builder(this@GradientActivity).apply {
            val colors = IntArray(3).apply {
                this[0] = Color.parseColor("#ff0000")
                this[1] = Color.parseColor("#0000ff")
                this[2] = Color.parseColor("#00ff00")
            }
            this.gradientColors(colors)
        }

        val corner = Corner.Builder(this@GradientActivity).apply {
            this.radius(12f)
        }

        val drawable = CodeGradientDrawable.Builder(this@GradientActivity).apply {

            size(width = 100, height = 100)
            gradient(gradient)
            corner(corner)
        }.build()

        findViewById<ImageView>(R.id.imageview12).setImageDrawable(drawable)
    }

    // 演示渐变作为背景
    private fun twoGradientBackgroundAngle() {
        findViewById<ImageView>(R.id.imageview13).setBackgroundResource(R.drawable.gradient_test5)

        val gradient = Gradient.Builder(this@GradientActivity).apply {

            val colors = IntArray(2).apply {
                this[0] = Color.parseColor("#ff0000")
                this[1] = Color.parseColor("#00ff00")
            }
            this.gradientColors(colors)
            this.orientation(GradientDrawable.Orientation.BL_TR)
        }
        val drawable = CodeGradientDrawable.Builder(this@GradientActivity).apply {
            size(width = 100, height = 100)
            gradient(gradient)
        }.build()

        findViewById<ImageView>(R.id.imageview14).background = drawable

    }

    fun testOnclick(view: android.view.View) {
                val codeColor2 = CodeColorStateList.Builder().apply {
            this.addSelectorItem(SelectorColorItem.Builder().apply {
                this.color(Color.parseColor("#ff0000"))
                this.state(-android.R.attr.state_checked)
                this.state(android.R.attr.state_pressed)
            })
            this.addSelectorItem(SelectorColorItem.Builder().apply {
                this.color(Color.parseColor("#00ff00"))
                this.state(-android.R.attr.state_pressed)
                this.state(android.R.attr.state_checked)
            })
            this.addSelectorItem(SelectorColorItem.Builder().apply {
                this.color(Color.parseColor("#0000ff"))
            })
        }.build()
    }
}