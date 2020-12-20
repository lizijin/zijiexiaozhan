package com.peter.viewgrouptutorial.recyclerview;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.core.view.NestedScrollingParent;
import androidx.recyclerview.widget.RecyclerView;

public class NestedRecyclerView extends RecyclerView implements NestedScrollingParent {

    private View nestedScrollTarget = null;
    private boolean nestedScrollTargetIsBeingDragged = false;
    private boolean nestedScrollTargetWasUnableToScroll = false;
    private boolean skipsTouchInterception = false;

    public NestedRecyclerView(Context context) {
        super(context);
    }

    public NestedRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NestedRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean temporarilySkipsInterception = nestedScrollTarget != null;
        if (temporarilySkipsInterception) {
            // If a descendent view is scrolling we set a flag to temporarily skip our onInterceptTouchEvent implementation
            skipsTouchInterception = true;
        }

        // First dispatch, potentially skipping our onInterceptTouchEvent
        boolean handled = super.dispatchTouchEvent(ev);

        if (temporarilySkipsInterception) {
            skipsTouchInterception = false;

            // If the first dispatch yielded no result or we noticed that the descendent view is unable to scroll in the
            // direction the user is scrolling, we dispatch once more but without skipping our onInterceptTouchEvent.
            // Note that RecyclerView automatically cancels active touches of all its descendents once it starts scrolling
            // so we don't have to do that.
            if (!handled || nestedScrollTargetWasUnableToScroll) {
                handled = super.dispatchTouchEvent(ev);
            }
        }

        return handled;
    }

    // Skips RecyclerView's onInterceptTouchEvent if requested
    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        return super.onInterceptTouchEvent(e) && !skipsTouchInterception;
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);

        if (target == nestedScrollTarget && !nestedScrollTargetIsBeingDragged) {
            if (dyConsumed != 0) {
                // The descendent was actually scrolled, so we won't bother it any longer.
                // It will receive all future events until it finished scrolling.
                nestedScrollTargetIsBeingDragged = true;
                nestedScrollTargetWasUnableToScroll = false;
            }
            else if (dyConsumed == 0 && dyUnconsumed != 0) {
                // The descendent tried scrolling in response to touch movements but was not able to do so.
                // We remember that in order to allow RecyclerView to take over scrolling.
                nestedScrollTargetWasUnableToScroll = true;
                if (target.getParent()!=null)
                    target.getParent().requestDisallowInterceptTouchEvent(false);
            }
        }
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int axes) {
        if (axes!=0 && View.SCROLL_AXIS_VERTICAL != 0) {
            // A descendent started scrolling, so we'll observe it.
            nestedScrollTarget = target;
            nestedScrollTargetIsBeingDragged = false;
            nestedScrollTargetWasUnableToScroll = false;
        }

        super.onNestedScrollAccepted(child, target, axes);
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        boolean secondPart = Build.VERSION.SDK_INT < 21 || View.SCROLL_AXIS_VERTICAL != 0;
        return (nestedScrollAxes!=0 && secondPart);
    }

    @Override
    public void onStopNestedScroll(View child) {
        nestedScrollTarget = null;
        nestedScrollTargetIsBeingDragged = false;
        nestedScrollTargetWasUnableToScroll = false;
    }
}