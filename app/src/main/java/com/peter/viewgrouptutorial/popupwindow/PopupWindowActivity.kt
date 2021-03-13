//package com.peter.viewgrouptutorial.popupwindow
//
//import android.animation.ValueAnimator
//import android.graphics.Color
//import android.os.Build
//import android.os.Bundle
//import android.view.*
//import android.widget.Button
//import android.widget.PopupWindow
//import android.widget.Toast
//import androidx.annotation.RequiresApi
//import androidx.appcompat.app.AppCompatActivity
//import com.peter.viewgrouptutorial.R
//
//class PopupWindowActivity : AppCompatActivity() {
//    lateinit var root: View
//    lateinit var button: View
//    var mTouchX: Float = 0F
//    var mTouchY: Float = 0F
//    var mWidth: Int = 0
//    var mHeight: Int = 0
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_popup_window)
//        root = findViewById(R.id.root)
//        button = findViewById(R.id.button)
//        findViewById<Button>(R.id.button1).setOnTouchListener(object : View.OnTouchListener {
//            @RequiresApi(Build.VERSION_CODES.M)
//            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
//                if (event?.action == MotionEvent.ACTION_DOWN) {
//                    mTouchX = event?.rawX
//                    mTouchY = event?.rawY
//                    showPopupWindow(v!!)
//                }
//                return true
//            }
//        })
//        findViewById<Button>(R.id.button).setOnTouchListener(object : View.OnTouchListener {
//            @RequiresApi(Build.VERSION_CODES.M)
//            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
//                if (event?.action == MotionEvent.ACTION_DOWN) {
//                    mTouchX = event?.rawX
//                    mTouchY = event?.rawY
//                    showPopupWindow(v!!)
//                }
//                return true
//            }
//        })
//    }
//
//    @RequiresApi(Build.VERSION_CODES.M)
//    fun showPopupWindow(view: View) {
////        mX = view.x
////        mY = view.y
//        var layout = LayoutInflater.from(this).inflate(R.layout.popupwindow, null)
//        layout.findViewById<Button>(R.id.button1).apply {
//            setOnClickListener {
//                Toast.makeText(this@PopupWindowActivity, "you click pop5", Toast.LENGTH_SHORT)
//                    .show()
//            }
//        }
//
//        layout.findViewById<Button>(R.id.button2).apply {
//            setOnClickListener {
//                Toast.makeText(this@PopupWindowActivity, "you click pop2", Toast.LENGTH_SHORT)
//                    .show()
//            }
//        }
//
//        layout.findViewById<Button>(R.id.button3).apply {
//            setOnClickListener {
//                Toast.makeText(this@PopupWindowActivity, "you click pop3", Toast.LENGTH_SHORT)
//                    .show()
//            }
//        }
//
//        layout.findViewById<Button>(R.id.button4).apply {
//            setOnClickListener {
//                Toast.makeText(this@PopupWindowActivity, "you click pop4", Toast.LENGTH_SHORT)
//                    .show()
//            }
//        }
////        var listner = object :ViewTreeObserver.OnGlobalLayoutListener{
////            override fun onGlobalLayout() {
////                layout.viewTreeObserver.removeOnGlobalFocusChangeListener(this)
////            }
////        }
//        val popupWindow = PopupWindow(
//            layout,
//            WindowManager.LayoutParams.MATCH_PARENT,
//            WindowManager.LayoutParams.WRAP_CONTENT
//        )
////        popupWindow.enterTransition = FabTransform(0,0)
//        layout!!.viewTreeObserver.addOnGlobalLayoutListener(object :
//            ViewTreeObserver.OnGlobalLayoutListener {
//            override fun onGlobalLayout() {
//                mWidth = layout.measuredWidth
//                mHeight = layout.measuredHeight
//                println("jiangbin ${layout.parent}")
//                val parent = layout.parent as ViewGroup
//                parent.setBackgroundColor(Color.YELLOW)
//                val position = IntArray(2)
//                layout.getLocationOnScreen(position)
//                layout.viewTreeObserver.removeOnGlobalLayoutListener(this)
//                val xCenter = mWidth/2  +position[0]
//                val yCenter = mHeight/2  + position[1]
//
//                println("jiangbin layout mWidth ${mWidth} X mHeight ${mHeight} ${xCenter} X ${yCenter} left ${position[0]} top ${position[1]} ")
//
//
//                val valueAnimator = ValueAnimator.ofFloat(0f, 1f)
//                valueAnimator.setDuration(300)
//                valueAnimator.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
//                    override fun onAnimationUpdate(animation: ValueAnimator?) {
//                        println("jiangbin onAnimationUpdate  ${animation?.animatedValue}")
//                        val value: Float = animation?.animatedValue as Float;
//                       val  mCurrentWidth = mWidth*value
//                        val  mCurrentHeight = mHeight*value
//
//                        popupWindow.update(
//                            (xCenter*value-mWidth/2).toInt(),
//                            (yCenter*value-mHeight/2).toInt(),
//                            (mCurrentWidth ).toInt(),
//                            (mCurrentHeight).toInt()
//                        )
//                    }
//
//                })
//                valueAnimator.start()
//            }
//        })
//
//
//        popupWindow.showAtLocation(root, Gravity.NO_GRAVITY, 0, 0)
//
//        popupWindow.isOutsideTouchable = true
////        popupWindow.update()
////        val valueAnimator = ValueAnimator.ofFloat(0f, 1f)
////        valueAnimator.setDuration(10000)
////        valueAnimator.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
////            override fun onAnimationUpdate(animation: ValueAnimator?) {
////                println("jiangbin onAnimationUpdate  ${animation?.animatedValue}")
////                val value: Float = animation?.animatedValue as Float;
////                popupWindow.update(
////                    (100 * value).toInt(),
////                    (100 * value).toInt(), (600 * value).toInt(), (600 * value).toInt()
////                )
////            }
////
////        })
////        valueAnimator.start()
//        popupWindow.setTouchInterceptor { v, event ->
//            popupWindow.dismiss()
//            true
//        }
//    }
//
//    fun test() {
//        val view: View? = null
//        view!!.viewTreeObserver.addOnGlobalLayoutListener(object :
//            ViewTreeObserver.OnGlobalLayoutListener {
//            override fun onGlobalLayout() {
//                view.viewTreeObserver.removeOnGlobalLayoutListener(this)
//            }
//        })
//    }
//}