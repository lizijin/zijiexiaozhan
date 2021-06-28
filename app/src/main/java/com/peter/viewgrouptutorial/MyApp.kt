package com.peter.viewgrouptutorial;

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.os.Build
import android.os.Handler
import android.os.SystemClock
import androidx.annotation.RequiresApi
import com.tencent.matrix.Matrix
import com.tencent.matrix.iocanary.IOCanaryPlugin
import com.tencent.matrix.iocanary.config.IOConfig
import com.tencent.matrix.trace.TracePlugin
import com.tencent.matrix.trace.config.TraceConfig
import com.tencent.matrix.util.MatrixLog
import com.tencent.sqlitelint.SQLiteLint
import com.tencent.sqlitelint.SQLiteLintPlugin
import com.tencent.sqlitelint.config.SQLiteLintConfig

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

        mHandler.postAtFrontOfQueue(ApplicationTask())


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
            A()
        }
    }
}