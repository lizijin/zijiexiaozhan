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
        println("jiangbin MyApp attachBaseContext")
        time1 = SystemClock.uptimeMillis()
        time3 = System.currentTimeMillis()

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

//            //resource
//            Intent intent = new Intent();
//            ResourceConfig.DumpMode mode = ResourceConfig.DumpMode.MANUAL_DUMP;
//            MatrixLog.i(TAG, "Dump Activity Leak Mode=%s", mode);
//            intent.setClassName(this.getPackageName(), "com.tencent.mm.ui.matrix.ManualDumpActivity");
//            ResourceConfig resourceConfig = new ResourceConfig.Builder()
//                    .dynamicConfig(dynamicConfig)
//                    .setAutoDumpHprofMode(mode)
////                .setDetectDebuger(true) //matrix test code
////                    .set(intent)
//                    .setManualDumpTargetActivity(ManualDumpActivity.class.getName())
//                    .build();
//            builder.plugin(new ResourcePlugin(resourceConfig));
//            ResourcePlugin.activityLeakFixer(this);

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


//            BatteryMonitor batteryMonitor = new BatteryMonitor(new BatteryMonitor.Builder()
//                    .installPlugin(LooperTaskMonitorPlugin.class)
//                    .installPlugin(JiffiesMonitorPlugin.class)
//                    .installPlugin(WakeLockMonitorPlugin.class)
//                    .disableAppForegroundNotifyByMatrix(false)
//                    .wakelockTimeout(2 * 60 * 1000)
//                    .greyJiffiesTime(2 * 1000)
//                    .build()
//            );
//            builder.plugin(batteryMonitor);
        }

        Matrix.init(builder.build())

        //start only startup tracer, close other tracer.

        //start only startup tracer, close other tracer.
        tracePlugin.start()
    }

    @SuppressLint("SoonBlockedPrivateApi")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate() {
        time4 = System.currentTimeMillis()

        super.onCreate()
        time2 = SystemClock.uptimeMillis()
        time4 = System.currentTimeMillis()

        println("jiangbin AutoApplication 222 " + (time2 - time1))
        println("jiangbin AutoApplication 333 " + (time4 - time3))


    }

}