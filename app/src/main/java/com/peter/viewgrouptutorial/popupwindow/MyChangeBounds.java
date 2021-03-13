package com.peter.viewgrouptutorial.popupwindow;

import android.graphics.Rect;
import android.os.Build;
import android.transition.ChangeBounds;
import android.transition.TransitionValues;
import android.view.View;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class MyChangeBounds extends ChangeBounds {
    int mTouchX;
    int mTouchY;

    public MyChangeBounds(int x, int y) {
        mTouchX = x;
        mTouchY = y;
    }

    @Override
    public void captureStartValues(TransitionValues transitionValues) {
//        super.captureStartValues(transitionValues);
        View view = transitionValues.view;

        if (view.isLaidOut() || view.getWidth() != 0 || view.getHeight() != 0) {
            int position[] = new int[2];
            view.getLocationInWindow(position);
//            transitionValues.values.put("android:changeBounds:bounds", new Rect(position[0] - mTouchX, position[1] - mTouchY,
//                    position[0] - mTouchX + 1, position[1] - mTouchY + 1));
            transitionValues.values.put("android:changeBounds:bounds", new Rect(  mTouchX,   mTouchY,
                      mTouchX + 1,   mTouchY + 1));
            transitionValues.values.put("android:changeBounds:parent", transitionValues.view.getParent());
//            if (mReparent) {
//                values.view.getLocationInWindow(tempLocation);
//                values.values.put(PROPNAME_WINDOW_X, tempLocation[0]);
//                values.values.put(PROPNAME_WINDOW_Y, tempLocation[1]);
//            }
//            if (mResizeClip) {
//                values.values.put(PROPNAME_CLIP, view.getClipBounds());
//            }
        }
    }
}
