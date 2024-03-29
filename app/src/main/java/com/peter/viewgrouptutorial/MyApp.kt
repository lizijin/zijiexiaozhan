package com.peter.viewgrouptutorial;

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.*
import android.view.Choreographer
import androidx.annotation.RequiresApi
import androidx.core.os.TraceCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.tencent.matrix.Matrix
import com.tencent.matrix.iocanary.IOCanaryPlugin
import com.tencent.matrix.iocanary.config.IOConfig
import com.tencent.matrix.trace.TracePlugin
import com.tencent.matrix.trace.config.TraceConfig
import com.tencent.matrix.util.MatrixLog
import com.tencent.sqlitelint.SQLiteLint
import com.tencent.sqlitelint.SQLiteLintPlugin
import com.tencent.sqlitelint.config.SQLiteLintConfig
import curtains.onNextDraw

class MyApp : Application() {

    lateinit var mHandler: Handler
    var time1: Long = 0
    var time2: Long = 0
    var time3: Long = 0
    var time4: Long = 0

    companion object {
        const val TAG: String = "Matrix.TestPluginListener"
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        println("zijiexiaozhan MyApp attachBaseContext")
        time1 = SystemClock.uptimeMillis()
        time3 = System.currentTimeMillis()

        try {
            val forName = Class.forName("android.app.ActivityThread")
            val field = forName.getDeclaredField("sCurrentActivityThread")
            field.isAccessible = true
            val activityThreadValue = field[forName]
            val mH = forName.getDeclaredField("mH")
            mH.isAccessible = true
            val handler = mH[activityThreadValue]
            mHandler = handler as Handler
        } catch (e: Exception) {
        }

        val dynamicConfig = MatrixConfig()
        val matrixEnable: Boolean = dynamicConfig.isMatrixEnable
        val fpsEnable: Boolean = dynamicConfig.isFPSEnable
        val traceEnable: Boolean = dynamicConfig.isTraceEnable

        MatrixLog.i(TAG, "MatrixApplication.onCreate")

        val builder = Matrix.Builder(this)
        builder.patchListener(MatrixListener(applicationContext))

        //trace

        //trace
        val traceConfig = TraceConfig.Builder()
            .dynamicConfig(dynamicConfig)
            .enableFPS(fpsEnable)
            .enableEvilMethodTrace(traceEnable)
            .enableAnrTrace(traceEnable)
            .enableStartup(traceEnable)
            .splashActivities("com.wm.dmall.LaunchActivity;")
            .isDebug(true)
            .isDevEnv(false)
            .build()

        val tracePlugin = TracePlugin(traceConfig)
        builder.plugin(tracePlugin)


        if (matrixEnable) {


            //io
            val ioCanaryPlugin = IOCanaryPlugin(
                IOConfig.Builder()
                    .dynamicConfig(dynamicConfig)
                    .build()
            )
            builder.plugin(ioCanaryPlugin)


            // prevent api 19 UnsatisfiedLinkError
            //sqlite
            val sqlLiteConfig: SQLiteLintConfig
            sqlLiteConfig = try {
                SQLiteLintConfig(SQLiteLint.SqlExecutionCallbackMode.CUSTOM_NOTIFY)
            } catch (t: Throwable) {
                SQLiteLintConfig(SQLiteLint.SqlExecutionCallbackMode.CUSTOM_NOTIFY)
            }
            builder.plugin(SQLiteLintPlugin(sqlLiteConfig))


        }

        Matrix.init(builder.build())

        tracePlugin.start()
    }

    @SuppressLint("SoonBlockedPrivateApi")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate() {
        time4 = System.currentTimeMillis()

        super.onCreate()
        time2 = SystemClock.uptimeMillis()
        time4 = System.currentTimeMillis()

        println("zijiexiaozhan AutoApplication 222 " + (time2 - time1))
        println("zijiexiaozhan AutoApplication 333 " + (time4 - time3))

        Choreographer.getInstance().postFrameCallback {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                println("zijiexiaozhan AutoApplication 666 ${SystemClock.elapsedRealtime() - android.os.Process.getStartElapsedRealtime()}")
                println("zijiexiaozhan AutoApplication 6662 ${SystemClock.uptimeMillis() - android.os.Process.getStartUptimeMillis()}")

            }
        }

        var firstDraw = false
        val handler = Handler()

        registerActivityLifecycleCallbacks(
            object : ActivityLifecycleCallbacks {
                @RequiresApi(Build.VERSION_CODES.N)
                override fun onActivityCreated(
                    activity: Activity,
                    savedInstanceState: Bundle?
                ) {
                    val window = activity.window
                        window.onNextDraw {
                            if (firstDraw) return@onNextDraw
                            firstDraw = true
                            handler.postAtFrontOfQueue {
                                println("zijiexiaozhan AutoApplication 6666 ${SystemClock.elapsedRealtime() - android.os.Process.getStartElapsedRealtime()}")
                                println("zijiexiaozhan AutoApplication 66662 ${SystemClock.uptimeMillis() - android.os.Process.getStartUptimeMillis()}")

                            }
                        }
                }

                override fun onActivityStarted(p0: Activity) {
                }

                override fun onActivityResumed(p0: Activity) {
                }

                override fun onActivityPaused(p0: Activity) {
                }

                override fun onActivityStopped(p0: Activity) {
                }

                override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
                }

                override fun onActivityDestroyed(p0: Activity) {
                }
            })

        mHandler.postAtFrontOfQueue(ApplicationTask())
        ProcessLifecycleOwner.get().lifecycle.addObserver(object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
            fun onForeground() {
                println("zijiexiaozhan ProcessLifecycleOwner onForeground ")

            }

            @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
            fun onBackground() {
                println("zijiexiaozhan ProcessLifecycleOwner onBackground ")

            }
        })


    }

    private fun A() {
        B()
        H()
        L()
        SystemClock.sleep(800)
    }

    private fun B() {
        C()
        G()
        SystemClock.sleep(200)
    }

    private fun C() {
        D()
        E()
        F()
        SystemClock.sleep(100)
    }

    private fun D() {
        SystemClock.sleep(20)
    }

    private fun E() {
        SystemClock.sleep(20)
    }

    private fun F() {
        SystemClock.sleep(20)
    }

    private fun G() {
        SystemClock.sleep(20)
    }

    private fun H() {
        SystemClock.sleep(20)
        I()
        J()
        K()
    }

    private fun I() {
        SystemClock.sleep(20)
    }

    private fun J() {
        SystemClock.sleep(6)
    }

    private fun K() {
        SystemClock.sleep(10)
    }


    private fun L() {
        SystemClock.sleep(100)
    }


    inner class ApplicationTask : Runnable {
        override fun run() {
            TraceCompat.beginSection("ApplicationTask")
            A()
            TraceCompat.endSection()
        }
    }
}