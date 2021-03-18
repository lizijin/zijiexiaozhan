package com.peter.viewgrouptutorial.expandtouch;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;


public class ExpandTouchDelegateHelper {

    private ViewGroup mParentView;
    private View mTargetView;
    private int mLeftExpand;
    private int mRightExpand;
    private int mTopExpand;
    private int mBottomExpand;

    public ExpandTouchDelegateHelper(ViewGroup parentView, View targetView, int leftExpand, int rightExpand, int topExpand, int bottomExpand) {
        mParentView = parentView;
        mTargetView = targetView;
        mLeftExpand = leftExpand;
        mRightExpand = rightExpand;
        mTopExpand = topExpand;
        mBottomExpand = bottomExpand;
    }

    public void expandTouchDelegate() {
        View currentTargetView = mTargetView;

        Rect bounds = new Rect();
        ViewGroupUtils.getDescendantRect(mParentView, mTargetView, bounds);
        bounds.left -= mLeftExpand;
        bounds.top -= mTopExpand;
        bounds.right += mRightExpand;
        bounds.bottom += mBottomExpand;
        ExpandMultiTouchDelegate touchDelegate = new ExpandMultiTouchDelegate(bounds, currentTargetView, mParentView, mLeftExpand, mRightExpand, mTopExpand, mBottomExpand);
        mParentView.setTouchDelegate(touchDelegate);
    }
}
