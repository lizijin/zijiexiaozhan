package com.peter.viewgrouptutorial.popupwindow;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.PopupWindow;
import android.widget.Scroller;


public class AnimatePopupWindow extends PopupWindow {
    private Animator mDismissAnimatorSet;
    private long mAnimatorDuration = 300L;
    private Activity mContext;
    private int touchX;
    private int touchY;
    private View contentView;
    public static boolean sPopupShowing = false;

    public AnimatePopupWindow(View contentView, int width, int height, int touchX, int touchY) {
        super(contentView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        contentView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {
                contentView.getLayoutParams().width = width;
                contentView.getLayoutParams().height = height;
//                contentView.setScaleY(0);
//                contentView.setScaleX(0);
            }

            @Override
            public void onViewDetachedFromWindow(View v) {

            }
        });

        setOutsideTouchable(false);
        setFocusable(true);
        interceptorOutsideEvent();
        mContext = (Activity) contentView.getContext();
        this.touchX = touchX;
        this.touchY = touchY;
        this.contentView = contentView;
        contentView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                contentView.removeOnLayoutChangeListener(this);
                int[] popupLayoutPosition = new int[2];

                contentView.getLocationOnScreen(popupLayoutPosition);



                DisplayMetrics displayMetrics = v.getContext().getResources().getDisplayMetrics();
                int screenWidth = displayMetrics.widthPixels;
                int screenHeight = displayMetrics.heightPixels;

                System.out.println("jiangbin onLayoutChange left " + popupLayoutPosition[0] + " top " + popupLayoutPosition[1] + " touchX " + touchX + " touchY " + touchY+" getLeft "+contentView.getLeft()+" getTop "+contentView.getTop());
                ObjectAnimator showScaleXAnimator = ObjectAnimator.ofFloat(contentView, "scaleX", 0f, 1f);
                ObjectAnimator showScaleYAnimator = ObjectAnimator.ofFloat(contentView, "scaleY", 0f, 1f);
                ObjectAnimator showTranslateXAnimator = ObjectAnimator.ofFloat(contentView, "translationX", touchX-popupLayoutPosition[0]-width/2, (screenWidth - width) / 2-popupLayoutPosition[0]);
                ObjectAnimator showTranslateYAnimator = ObjectAnimator.ofFloat(contentView, "translationY", touchY-popupLayoutPosition[1]-height/2, (screenHeight - height) / 2-popupLayoutPosition[1]);



//                ObjectAnimator showTranslateXAnimator = ObjectAnimator.ofFloat(contentView, "translationX", touchX-popupLayoutPosition[0], (screenWidth - width) / 2-popupLayoutPosition[0]);
//                ObjectAnimator showTranslateYAnimator = ObjectAnimator.ofFloat(contentView, "translationY", touchY-popupLayoutPosition[1], (screenHeight - height) / 2-popupLayoutPosition[1]);


                AnimatorSet showPopupWindowAnimatorSet = new AnimatorSet();
                showPopupWindowAnimatorSet.setDuration(mAnimatorDuration);
                showPopupWindowAnimatorSet.playTogether(showScaleXAnimator, showScaleYAnimator, showTranslateXAnimator, showTranslateYAnimator);//两个动画同时开始


                showTranslateXAnimator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        darkenBackground(0.5f);
                        sPopupShowing = true;

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                showPopupWindowAnimatorSet.start();


                ObjectAnimator dismissScaleXAnimator = ObjectAnimator.ofFloat(contentView, "scaleX", 1f, 0f);
                ObjectAnimator dismissScaleYAnimator = ObjectAnimator.ofFloat(contentView, "scaleY", 1f, 0f);
                ObjectAnimator dismissTranslateXAnimator = ObjectAnimator.ofFloat(contentView, "translationX", (screenWidth - width) / 2-popupLayoutPosition[0], touchX-popupLayoutPosition[0]-width/2);
                ObjectAnimator dismissTranslateYAnimator = ObjectAnimator.ofFloat(contentView, "translationY", (screenHeight - height) / 2-popupLayoutPosition[1], touchY-popupLayoutPosition[1]-height/2);



                AnimatorSet dismissPopupWindowAnimatorSet = new AnimatorSet();
                dismissPopupWindowAnimatorSet.setDuration(mAnimatorDuration);
                dismissPopupWindowAnimatorSet.playTogether(dismissScaleXAnimator, dismissScaleYAnimator, dismissTranslateYAnimator, dismissTranslateXAnimator);//两个动画同时开始


                dismissPopupWindowAnimatorSet.setDuration(mAnimatorDuration);

                mDismissAnimatorSet = dismissPopupWindowAnimatorSet;

                mDismissAnimatorSet.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        dismiss();
                        darkenBackground(1.0f);
                        sPopupShowing = false;

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });

            }
        });
    }

    private void interceptorOutsideEvent() {
        setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    mDismissAnimatorSet.start();
                    return true;
                }
                return false;
            }
        });
    }

    private void darkenBackground(float bgcolor) {
        WindowManager.LayoutParams lp = mContext.getWindow().getAttributes();
        lp.alpha = bgcolor;
        mContext.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        mContext.getWindow().setAttributes(lp);
    }


    public void initAnimator() {
    }

    public void animateDismiss() {
        mDismissAnimatorSet.start();
    }

}
