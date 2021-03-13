package com.peter.viewgrouptutorial.popupwindow;

import android.view.View;
import android.view.ViewTreeObserver;

public class Test {
    void test(){
        View view = null;
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }
}
