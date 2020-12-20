package com.peter.viewgrouptutorial

import android.app.AlertDialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class DialogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)
    }

    private var longWhich = 0
    private var allWhich = 0

    fun showLongTextDialog(view: View) {
        when (longWhich++ % 3) {
            0 -> {
                val alertDialog =
                    AlertDialog.Builder(this).apply {
                        setMessage(R.string.long_hate_song)
                        setTitle("原始Dialog")
                    }.create()
                alertDialog.show()
            }
            1 -> {
                val alertDialog =
                    androidx.appcompat.app.AlertDialog.Builder(this).apply {
                        setMessage(R.string.long_hate_song)
                        setTitle("appcompat Dialog")
                    }.create()
                alertDialog.show()
            }
            2 -> {
                val alertDialog =
                    MaterialAlertDialogBuilder(this).apply {
                        setMessage(R.string.long_hate_song)
                        setTitle("Material Dialog")
                    }.create()
                alertDialog.show()
            }

        }
//        val decorView = alertDialog.window.decorView;
//        println("zijiexiaozhan ${alertDialog.window.decorView}")
//        printlnView(alertDialog.window.decorView)
//        printlnView(alertDialog.window.decorView)

    }

    fun printlnView(view: View?) {
        view?.let {
            println("zijiexiaozhan $it")
            if (view is ScrollView) {
                view.layoutParams.height = 500
                view.setBackgroundColor(Color.RED)
                return
            }
            if (view is ViewGroup) {
                for (i in 0 until view.childCount) {
                    printlnView(view.getChildAt(i))
                }
            }
        }
    }

    fun showShortTextDialog(view: View) {
        val alertDialog = AlertDialog.Builder(this).setMessage("This is a short Message").create()
        alertDialog.show()
    }

    fun showAll(view: View) {
        repeat(2) {
            when (allWhich++ % 3) {
                0 -> {
                    val alertDialog =
                        AlertDialog.Builder(this).apply {
                            setMessage(R.string.long_hate_song)
                            setTitle("白居易·长恨歌1")
                            setIcon(R.mipmap.ic_launcher)
                            setPositiveButton("好诗") { dialog, which ->
                                Toast.makeText(this@DialogActivity, "好诗", Toast.LENGTH_SHORT).show()
                            }
                            setNegativeButton("坏诗") { dialog, which ->
                                Toast.makeText(this@DialogActivity, "坏诗", Toast.LENGTH_SHORT).show()
                            }
                            setNeutralButton("一般") { dialog, which ->
                                Toast.makeText(this@DialogActivity, "一般", Toast.LENGTH_SHORT).show()
                            }
                        }.create()
                    alertDialog.show()
                }
                1 -> {
                    val alertDialog =
                        androidx.appcompat.app.AlertDialog.Builder(this).apply {
                            setMessage(R.string.long_hate_song)
                            setTitle("白居易·长恨歌2")
                            setIcon(R.mipmap.ic_launcher)
                            setPositiveButton("好诗") { dialog, which ->
                                Toast.makeText(this@DialogActivity, "好诗", Toast.LENGTH_SHORT).show()
                            }
                            setNegativeButton("坏诗") { dialog, which ->
                                Toast.makeText(this@DialogActivity, "坏诗", Toast.LENGTH_SHORT).show()
                            }
                            setNeutralButton("一般") { dialog, which ->
                                Toast.makeText(this@DialogActivity, "一般", Toast.LENGTH_SHORT).show()
                            }
                        }.create()
                    alertDialog.show()
                }
                2 -> {
                    val alertDialog =
                        MaterialAlertDialogBuilder(this).apply {
                            setMessage(R.string.long_hate_song)
                            setTitle("白居易·长恨歌3")
                            setIcon(R.mipmap.ic_launcher)
                            setPositiveButton("好诗") { dialog, which ->
                                Toast.makeText(this@DialogActivity, "好诗", Toast.LENGTH_SHORT).show()
                            }
                            setNegativeButton("坏诗") { dialog, which ->
                                Toast.makeText(this@DialogActivity, "坏诗", Toast.LENGTH_SHORT).show()
                            }
                            setNeutralButton("啊一般") { dialog, which ->
                                Thread.sleep(20000)
                                Toast.makeText(this@DialogActivity, "一般", Toast.LENGTH_SHORT).show()
                            }
                        }.create()
                    alertDialog.show()
                }
            }
        }
//        printlnView(dialog.window.decorView)
    }

    fun showBottomSheetDialog(view: View) {
        val bottomSheetDialog = BottomSheetDialog(this).apply {
            setContentView(TextView(this@DialogActivity).apply {
                setText(R.string.long_hate_song)
            })
        }
        bottomSheetDialog.apply {
//            behavior.setPeekHeight(100,true)
//            behavior.isHideable = false
            behavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
            behavior.isFitToContents = false
            behavior.setExpandedOffset(700)
        }

        bottomSheetDialog.show()
    }
}