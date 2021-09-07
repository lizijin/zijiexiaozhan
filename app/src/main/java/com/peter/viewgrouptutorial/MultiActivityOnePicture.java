package com.peter.viewgrouptutorial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.asynclayoutinflater.view.AsyncLayoutInflater;

import com.peter.viewgrouptutorial.activity.DashboardActivity;

import org.jetbrains.annotations.NotNull;

public class MultiActivityOnePicture extends AppCompatActivity {
    boolean mInflateDone = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                setContentView(R.layout.activity_multi_one_picture);
//                mInflateDone = true;
//            }
//        }).start();
//        while (MyAppJava.mView == null) {
//            System.out.println("MultiActivityOnePicture while");
//        }
//        setContentView(MyAppJava.mView);
//        setContentView(R.layout.activity_multi_one_picture);

        new AsyncLayoutInflater(this).inflate(R.layout.activity_multi_one_picture, null, new AsyncLayoutInflater.OnInflateFinishedListener() {
            @Override
            public void onInflateFinished(@NonNull @NotNull View view, int resid, @Nullable @org.jetbrains.annotations.Nullable ViewGroup parent) {
                setContentView(view);
            }
        });
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        setContentView(null);
        System.out.println("MultiActivityOnePicture onDestroy");

    }

    public void  jumpNew(View view) {
        startActivity(new Intent(this, DashboardActivity.class));
    }
}
