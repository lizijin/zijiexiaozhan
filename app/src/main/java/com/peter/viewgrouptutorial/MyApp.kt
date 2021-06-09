package com.peter.viewgrouptutorial;

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.*
import androidx.annotation.RequiresApi
import curtains.onDecorViewReady
import curtains.onNextDraw

class MyApp : Application() {

    lateinit var mHandler: Handler
     var time1:Long = 0
    var time2:Long = 0
    var time3:Long = 0
    var time4:Long = 0
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        println("jiangbin MyApp attachBaseContext")
        time1 =SystemClock.uptimeMillis()
        time3 =System.currentTimeMillis()
    }

    @SuppressLint("SoonBlockedPrivateApi")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate() {
        time4 =System.currentTimeMillis()

        super.onCreate()
        time2 = SystemClock.uptimeMillis()
        time4 =System.currentTimeMillis()

        println("jiangbin AutoApplication 222 " + (time2 - time1))
        println("jiangbin AutoApplication 333 " + (time4 - time3))

//        TraceCompat.beginSection("appOnCreate")
        println("jiangbin MyApp onCreate")
//        Debug.startMethodTracing("sample")
//        Thread.sleep(400)
        try {
            val forName = Class.forName("android.app.ActivityThread")
            val field = forName.getDeclaredField("sCurrentActivityThread")
            field.isAccessible = true
            val activityThreadValue = field[forName]
            val mH = forName.getDeclaredField("mH")
            mH.isAccessible = true
            val handler = mH[activityThreadValue]
            mHandler = handler as Handler

            val handlerClass: Class<*>? = handler.javaClass.superclass
            if (null != handlerClass) {
                val callbackField =
                    handlerClass.getDeclaredField("mCallback")
                callbackField.isAccessible = true

//                val originalCallback =
//                    callbackField[handler] as Handler.Callback
//                println("jiangbin MyApp origin $originalCallback")

//                val callback: com.tencent.matrix.trace.hacker.ActivityThreadHacker.HackCallback =
//                    com.tencent.matrix.trace.hacker.ActivityThreadHacker.HackCallback(
//                        originalCallback
//                    )
                callbackField[handler] = HackCallback()
            }

            mHandler.postAtFrontOfQueue(){
//                Thread.sleep(1000)
                println("jiangbin MyApp ----")
                mHandler.postAtFrontOfQueue(){
                    println("jiangbin MyApp 222----")
//                    Thread.sleep(1000)

                }

            }


//            val mQueueField =  mHandler.looper.javaClass.getDeclaredField("mQueue")
//            mQueueField.isAccessible = true
//            val mq = mQueueField.get(mHandler.looper) as MessageQueue
//            val method = mq.javaClass.getMethod("postSyncBarrier")
//            method.isAccessible = true
//            method.invoke(mq)
            println("jiangbin MyApp last")
        } catch (e: Exception) {
            e.printStackTrace()
        }
        var firstDraw = false
        val handler = Handler()

        registerActivityLifecycleCallbacks(
            object : ActivityLifecycleCallbacks {
                override fun onActivityPaused(activity: Activity?) {
                }

                override fun onActivityResumed(activity: Activity?) {
                }

                override fun onActivityStarted(activity: Activity?) {
                }

                override fun onActivityDestroyed(activity: Activity?) {
                }

                override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
                }

                override fun onActivityStopped(activity: Activity?) {
                }

                override fun onActivityCreated(
                    activity: Activity,
                    savedInstanceState: Bundle?
                ) {
                    if (firstDraw) return
                    val window = activity.window
                    window.onDecorViewReady {
                        window.onNextDraw {
                            if (firstDraw) return@onNextDraw
                            firstDraw = true
                            handler.postAtFrontOfQueue {
                                println("jiangbin end")
//                                Debug.stopMethodTracing()
                            }
                        }
                    }
                }
            })
        Thread.sleep(500)
//        TraceCompat.endSection()

    }
    inner  class HackCallback :Handler.Callback{
        override fun handleMessage(msg: Message?): Boolean {
            println("jiangbin MyApp "+msg?.what)
            return  false
        }
    }
}