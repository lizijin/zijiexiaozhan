package com.peter.viewgrouptutorial.popupwindow;

import android.os.Build;
import android.transition.Slide;
import android.transition.TransitionValues;
import android.view.View;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MySlide  extends Slide {
//    @Override
//    public void captureStartValues(TransitionValues transitionValues) {
//        super.captureStartValues(transitionValues);
////        View view = transitionValues.view;
//        int[] position = {0,1};
//
//        transitionValues.values.put("android:slide:screenPosition", position);
//    }
}
