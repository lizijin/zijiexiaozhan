package com.peter.viewgrouptutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

public class LockActivity extends AppCompatActivity {
    final Object lock = new Object();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock);
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"LockThread").start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    System.out.println("get the lock");
                }
            }
        },1000);
    }

    public void clickButton(View view) {
        Toast.makeText(this,"Hello",Toast.LENGTH_SHORT).show();
    }
}