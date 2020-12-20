package com.peter.viewgrouptutorial.textview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.style.ReplacementSpan;

import androidx.annotation.NonNull;


public class RoundBackgroundColorSpan extends ReplacementSpan {
    private int bgColor;
    private int textColor;
    private Context context;
    private String text;
    private int bgCorner;
    private boolean hasBorder;
    private int textSize;

    public RoundBackgroundColorSpan(Context context, int bgColor, int textColor, String text) {
        super();
        this.bgColor = bgColor;
        this.textColor = textColor;
        this.context = context;
        this.text = text;
        this.bgCorner = 4;
    }

    public RoundBackgroundColorSpan(Context context, int bgColor, int textColor, int corner, String text) {
        this(context, bgColor, textColor, corner, text, false);
    }

    public RoundBackgroundColorSpan(Context context, int bgColor, int textColor, int corner, String text, boolean hasBorder) {
        super();
        this.bgColor = bgColor;
        this.textColor = textColor;
        this.context = context;
        this.text = text;
        this.bgCorner = corner;
        this.hasBorder = hasBorder;
    }

    /**
     * @param size 为px值
     */
    public void setTextSize(int size) {
        this.textSize = size;
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
    return 0;
    }

    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, @NonNull Paint paint) {

    }

}
