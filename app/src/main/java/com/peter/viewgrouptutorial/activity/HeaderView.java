package com.peter.viewgrouptutorial.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.peter.viewgrouptutorial.R;


public class HeaderView extends FrameLayout {
    public final TextView mTextView;

    public HeaderView(@NonNull Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.item_header, this);
        mTextView = findViewById(R.id.header_text);
    }

}
