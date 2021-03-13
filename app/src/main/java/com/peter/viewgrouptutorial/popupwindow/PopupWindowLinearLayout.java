package com.peter.viewgrouptutorial.popupwindow;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class PopupWindowLinearLayout extends LinearLayout {
    public PopupWindowLinearLayout(Context context) {
        super(context);
    }

    public PopupWindowLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PopupWindowLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        System.out.println("jiangbin PopupWindowLinearLayout onLayout");
    }
}
