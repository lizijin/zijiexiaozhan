package com.peter.viewgrouptutorial.popupwindow;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.peter.viewgrouptutorial.R;

import java.util.ArrayList;

public class PopupWindowActivity extends AppCompatActivity {
    private int mTouchX = 0;
    private int mTouchY = 0;
    private ViewGroup mRoot;
    private AnimatorSet reverseAnimator;
    private ArrayList<Button> buttons = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window);
        mRoot = findViewById(R.id.root);
        buttons.add(findViewById(R.id.button));
        buttons.add(findViewById(R.id.button1));
        buttons.add(findViewById(R.id.button2));
        buttons.add(findViewById(R.id.button3));
        buttons.add(findViewById(R.id.button4));
        buttons.add(findViewById(R.id.button5));
        buttons.add(findViewById(R.id.button6));
        buttons.add(findViewById(R.id.button7));
        buttons.add(findViewById(R.id.button8));
        buttons.add(findViewById(R.id.button9));
        buttons.add(findViewById(R.id.button10));
        buttons.add(findViewById(R.id.button11));
        buttons.add(findViewById(R.id.button12));
        View.OnTouchListener onTouchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mTouchX = (int) event.getRawX();
                    mTouchY = (int) event.getRawY();
                    showPopupWindow2();
                }
                return true;
            }
        };
        for (Button button : buttons) {
            button.setOnTouchListener(onTouchListener);
        }
//        findViewById(R.id.button12).setOnClickListener(new View.OnClickListener() {
//            float step = 10;
//
//            @Override
//
//            public void onClick(View v) {
//                v.setTranslationX(step);
//                step+=10f;
//            }
//        });

    }
    public static int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;
    }
    public static int dp2px(Context contxt, int dp) {
        final float scale = contxt.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
    private void showPopupWindow2() {
        View popupLayout = LayoutInflater.from(this).inflate(R.layout.popupwindow, null);
        int mWidth = getScreenWidth(this) -  dp2px(this, 38 * 2);
        int mHeight = dp2px(this, 432);
        AnimatePopupWindow popupWindow = new AnimatePopupWindow(popupLayout, mWidth,mHeight, mTouchX, mTouchY);
        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.NO_GRAVITY, 0, 0);
        popupWindow.initAnimator();
        popupLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.animateDismiss();
            }
        });

    }

    private void showPopupWindow() {
        View popupLayout = LayoutInflater.from(this).inflate(R.layout.popupwindow, null);

        PopupWindow popupWindow = new PopupWindow(popupLayout, 600, WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);

        popupLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reverseAnimator.start();
                reverseAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        popupWindow.dismiss();
                    }
                });
            }
        });
        popupWindow.showAtLocation(mRoot, Gravity.CENTER, 0, 0);
        popupLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                popupLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int[] popupLayoutPosition = new int[2];
                popupLayout.getLocationOnScreen(popupLayoutPosition);
                final int width = popupLayout.getMeasuredWidth();
                final int height = popupLayout.getMeasuredHeight();
                int popupLayoutLeft = popupLayoutPosition[0];
                int popupLayoutTop = popupLayoutPosition[1];
                int popupLayoutCenterX = popupLayoutLeft + width / 2;
                int popupLayoutCenterY = popupLayoutTop + height / 2;

                final Data data = new Data(mTouchX, mTouchY);
                PropertyValuesHolder xHolder = PropertyValuesHolder.ofInt("centerX", mTouchX, popupLayoutCenterX);
                PropertyValuesHolder yHolder = PropertyValuesHolder.ofInt("centerY", mTouchY, popupLayoutCenterY);
                ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(data, xHolder, yHolder);
                animator.setDuration(1000);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int animatedValue = (int) animation.getAnimatedValue();
                        float animatedFraction = animation.getAnimatedFraction();
                        long playTime = animation.getCurrentPlayTime();

                        System.out.println("animatedValue:" + animatedValue + ",  playTime:" + playTime + "data " + data);
                        popupWindow.update(data.centerX - popupLayoutCenterX, data.centerY - popupLayoutCenterY, width, height);
                    }
                });

                ObjectAnimator scaleX = ObjectAnimator.ofFloat(popupLayout, "scaleX", 0, 1f);
                ObjectAnimator scaleY = ObjectAnimator.ofFloat(popupLayout, "scaleY", 0f, 1f);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.setDuration(1000);
//                animatorSet.setInterpolator(new DecelerateInterpolator());
                animatorSet.playTogether(scaleX, scaleY, animator);//两个动画同时开始
                animatorSet.start();
//                animator.start();


                final Data data2 = new Data(popupLayoutCenterX, popupLayoutCenterY);
                PropertyValuesHolder xHolder2 = PropertyValuesHolder.ofInt("centerX", popupLayoutCenterX, mTouchX);
                PropertyValuesHolder yHolder2 = PropertyValuesHolder.ofInt("centerY", popupLayoutCenterY, mTouchY);
                ObjectAnimator animator2 = ObjectAnimator.ofPropertyValuesHolder(data2, xHolder2, yHolder2);
                animator2.setDuration(1000);
                animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int animatedValue = (int) animation.getAnimatedValue();
                        float animatedFraction = animation.getAnimatedFraction();
                        long playTime = animation.getCurrentPlayTime();

                        System.out.println("animatedValue:" + animatedValue + ",  playTime:" + playTime + "data " + data);
                        popupWindow.update(data2.centerX - popupLayoutCenterX, data2.centerY - popupLayoutCenterY, width, height);
                    }
                });

                ObjectAnimator scaleX2 = ObjectAnimator.ofFloat(popupLayout, "scaleX", 1f, 0);
                ObjectAnimator scaleY2 = ObjectAnimator.ofFloat(popupLayout, "scaleY", 1f, 0);
                reverseAnimator = new AnimatorSet();
                reverseAnimator.setDuration(1000);
                reverseAnimator.playTogether(scaleX2, scaleY2, animator2);


                System.out.println("jiangbin gloablelayout popupLayoutCenterX -> " + popupLayoutCenterX + "  popupLayoutCenterY -> " + popupLayoutCenterY);
//                ValueAnimator xValueAnimator = ValueAnimator.ofInt(mTouchX, popupLayoutCenterX);
//                xValueAnimator.setDuration(300);
//                xValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                    @Override
//                    public void onAnimationUpdate(ValueAnimator animation) {
//                        int xValue = (int) animation.getAnimatedValue();
//                    }
//                });
//
//                ValueAnimator yValueAnimator = ValueAnimator.ofInt(mTouchY, popupLayoutCenterY);
//                yValueAnimator.setDuration(300);
//                yValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                    @Override
//                    public void onAnimationUpdate(ValueAnimator animation) {
//                        int yValue = (int) animation.getAnimatedValue();
//
//                    }
//                });
//                AnimatorSet animatorSet = new AnimatorSet();
//                animatorSet.playTogether(xValueAnimator, yValueAnimator);

            }
        });
    }

    public static class Data {
        int centerX;
        int centerY;

        public Data(int centerX, int centerY) {
            this.centerX = centerX;
            this.centerY = centerY;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "centerX=" + centerX +
                    ", centerY=" + centerY +
                    '}';
        }

        public void setCenterX(int centerX) {
            this.centerX = centerX;
        }

        public void setCenterY(int centerY) {
            this.centerY = centerY;
        }

        public int getCenterX() {
            return centerX;
        }

        public int getCenterY() {
            return centerY;
        }
    }
}
