package com.peter.viewgrouptutorial.expandtouch;

import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

public class ExpandMultiTouchDelegate extends TouchDelegate {
    private boolean mDelegateTargeted;
    private final Rect mBounds;
    private final Rect mSlopBounds;
    private final Rect mChildInParentBounds = new Rect();
    private final View mDelegateView;
    private final int mSlop;
    private final ViewGroup mParentView;
    private final int mLeftExpand;
    private final int mRightExpand;
    private final int mTopExpand;
    private final int mBottomExpand;

    public ExpandMultiTouchDelegate(Rect bounds, View delegateView, ViewGroup parentView, int leftExpand, int rightExpand, int topExpand, int bottomExpand) {
        super(bounds, delegateView);
        mBounds = bounds;

        mSlop = ViewConfiguration.get(delegateView.getContext()).getScaledTouchSlop();
        mSlopBounds = new Rect(bounds);
        mSlopBounds.inset(-mSlop, -mSlop);
        mDelegateView = delegateView;
        mParentView = parentView;
        mLeftExpand = leftExpand;
        mRightExpand = rightExpand;
        mBottomExpand = bottomExpand;
        mTopExpand = topExpand;
    }

    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        boolean sendToDelegate = false;
        boolean hit = true;
        boolean handled = false;

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                mDelegateTargeted = mBounds.contains(x, y);
                sendToDelegate = mDelegateTargeted;
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_MOVE:
                sendToDelegate = mDelegateTargeted;
                if (sendToDelegate) {
                    Rect slopBounds = mSlopBounds;
                    if (!slopBounds.contains(x, y)) {
                        hit = false;
                    }
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                sendToDelegate = mDelegateTargeted;
                mDelegateTargeted = false;
                break;
        }
        if (sendToDelegate) {
            final View delegateView = mDelegateView;
            if (hit) {
                //主要修改在这，针对加减购布局，加购和减购按钮特殊处理
                if (delegateView instanceof ViewGroup) {
                    int count = ((ViewGroup) delegateView).getChildCount();
                    for (int i = 0; i < count; i++) {
                        if (i != 0 && i != count - 1) continue;
                        View child = ((ViewGroup) delegateView).getChildAt(i);
                        ViewGroupUtils.getDescendantRect(mParentView, child, mChildInParentBounds);
                        mChildInParentBounds.top -= mTopExpand;
                        mChildInParentBounds.bottom += mBottomExpand;
                        mChildInParentBounds.left -= mLeftExpand;
                        mChildInParentBounds.right += mRightExpand;
                        if (mChildInParentBounds.contains(x, y)) {
                            event.setLocation(child.getWidth() / 2, child.getHeight() / 2);
                            return child.dispatchTouchEvent(event);
                        }
                    }
                    return false;
                } else {
                    event.setLocation(delegateView.getWidth() / 2, delegateView.getHeight() / 2);
                }
            } else {
                int slop = mSlop;
                event.setLocation(-(slop * 2), -(slop * 2));
            }
            handled = delegateView.dispatchTouchEvent(event);
        }
        return handled;
    }
}
