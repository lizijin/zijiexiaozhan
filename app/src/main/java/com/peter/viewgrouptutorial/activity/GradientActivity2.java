package com.peter.viewgrouptutorial.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.peter.viewgrouptutorial.CodeGradientDrawable;
import com.peter.viewgrouptutorial.Corner;
import com.peter.viewgrouptutorial.Gradient;
import com.peter.viewgrouptutorial.R;

public class GradientActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gradient2);
        ImageView imageView = findViewById(R.id.imageview1);
        Corner corner = new Corner.Builder(this).radius(12F).build();
        Gradient gradient = new Gradient.Builder(this).gradientColors(new int[]{
                Color.parseColor("#ff0000"), Color.parseColor("#00ff00")}).build();
        CodeGradientDrawable drawable =  new CodeGradientDrawable.Builder(this)
                .gradient(gradient)
                .corner(corner)
                .build();


        imageView.setImageDrawable(drawable);
    }
}