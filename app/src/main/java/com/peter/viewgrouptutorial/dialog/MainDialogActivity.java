package com.peter.viewgrouptutorial.dialog;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.peter.viewgrouptutorial.R;

public class MainDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dialog);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void morphing(View view) {
        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(this,view,"element");
        startActivity(new Intent(this,DialogActivity.class),activityOptions.toBundle());
    }
}