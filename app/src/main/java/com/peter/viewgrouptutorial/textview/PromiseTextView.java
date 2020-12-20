package com.peter.viewgrouptutorial.textview;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.util.AttributeSet;


import com.peter.viewgrouptutorial.R;

public class PromiseTextView extends androidx.appcompat.widget.AppCompatTextView {

    public PromiseTextView(Context context) {
        this(context, null);
    }

    public PromiseTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PromiseTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     * 设置时效信息和商品名称
     *
     * @param promise 时效信息
     * @param name    商品名称
     */

    public void setText(String promise, String name) {
        setText(promise, 10, name);
    }

    public void setText(String promise, String name, int textColor) {
        setText(promise, 10, name, textColor);
    }

    public void setText(String promise, int promiseSizeDp, String name) {
        int textColor = getResources().getColor(R.color.colorPrimary);
        setText(promise, promiseSizeDp, name, textColor);
    }

    public void setText(String promise, int promiseSizeDp, String name, int textColor) {
        if (TextUtils.isEmpty(promise)) {
            setText(name);
        } else {
            int bgColor = getResources().getColor(R.color.colorPromise);
            SpannableString spannableString = new SpannableString(promise + name);
//            RoundBackgroundColorSpan2 roundBackgroundColorSpan = new RoundBackgroundColorSpan2(getContext(), bgColor, textColor, 2, promise, promiseSizeDp);
            RoundBackgroundColorSpan roundBackgroundColorSpan = new RoundBackgroundColorSpan(getContext(), bgColor, textColor, 2, promise, true);
            spannableString.setSpan(roundBackgroundColorSpan, 0, promise.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new AbsoluteSizeSpan(promiseSizeDp, true), 0, promise.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            setText(spannableString);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        System.out.println("zijiexiaozhan "+getText()+" "+MeasureSpec.getSize(heightMeasureSpec));
    }
}
