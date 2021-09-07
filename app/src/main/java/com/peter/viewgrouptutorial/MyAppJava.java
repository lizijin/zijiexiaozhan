package com.peter.viewgrouptutorial;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.asynclayoutinflater.view.AsyncLayoutInflater;

import com.alibaba.android.alpha.AlphaManager;
import com.alibaba.android.alpha.Project;
import com.alibaba.android.alpha.Task;

import org.jetbrains.annotations.NotNull;

import curtains.WindowsKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

public class MyAppJava extends Application {
    public  static  View mView;
    static {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            System.out.println("zijiexiaozhan timing 加载apk和资源 " + (SystemClock.elapsedRealtime() - Process.getStartElapsedRealtime()));

            System.out.println("zijiexiaozhan timing 加载apk和资源2 " + Processes.readProcessForkRealtimeMillis()+" start "+Process.getStartElapsedRealtime());

        }
    }

    boolean firstDraw = false;
    Handler handler = new Handler();
    long attachTime = 0L;
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        attachTime = SystemClock.elapsedRealtime();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                mView  =   LayoutInflater.from(MyAppJava.this).inflate(R.layout.activity_multi_one_picture, null);
//            }
//        }).start();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("zijiexiaozhan timing cp " + (SystemClock.elapsedRealtime() - attachTime));
        MyTask taskA = new MyTask("taskA",1000L);
        MyTask taskB = new MyTask("taskB",1000L);
        MyTask taskC = new MyTask("taskC",1000L);
        MyTask taskD = new MyTask("taskD",1000L);
        MyTask taskE = new MyTask("taskE",1000L);

        Project.Builder builder = new Project.Builder();
//        builder.add(task1);
//        builder.add(task2);
//        builder.add(task3);
//        builder.add(task4);
//        builder.add(taskE).after(taskB).after(taskA);
//        builder.add(taskE).after(taskB).after(taskA);
        builder.add(taskA);
        builder.add(taskB).after(taskA);
        builder.add(taskC).after(taskA);

        builder.add(taskD).after(taskC);
        builder.add(taskE).after(taskB);


        AlphaManager.getInstance(getApplicationContext()).addProject(builder.create());
        AlphaManager.getInstance(getApplicationContext()).start();
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                Window window = activity.getWindow();
                WindowsKt.onNextDraw(window, () -> {
                    if (firstDraw) return null;
                    firstDraw = true;
                    handler.postAtFrontOfQueue(() -> {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            System.out.println("zijiexiaozhan timing 首帧 " + (SystemClock.elapsedRealtime() - Process.getStartElapsedRealtime()));
                        }
                    });
                    return null;
                });
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });

//        new AsyncLayoutInflater(this).inflate(R.layout.activity_multi_one_picture, null, new AsyncLayoutInflater.OnInflateFinishedListener() {
//            @Override
//            public void onInflateFinished(@NonNull @NotNull View view, int resid, @Nullable ViewGroup parent) {
//                mView = view;
//            }
//        });

    }
}
