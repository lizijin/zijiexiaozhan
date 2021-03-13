package com.peter.viewgrouptutorial.dialog;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.Explode;
import android.transition.Transition;
import android.widget.Button;

import com.peter.viewgrouptutorial.R;

public class DialogActivity extends AppCompatActivity {
    private Button mButton;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog2);
        mButton  = findViewById(R.id.button);
//        Transition transition = null;
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//            transition = new Explode();
//        }
//        transition.addTarget(mButton);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().setSharedElementEnterTransition(transition);
//        }
    }
}