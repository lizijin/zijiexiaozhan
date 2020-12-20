package com.peter.viewgrouptutorial

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import androidx.coordinatorlayout.widget.CoordinatorLayout

class ConsumeBehavior : CoordinatorLayout.Behavior<Button> {
    constructor() : super()
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: Button,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        println("zijiexiaozhan behavior onStartNestedScroll child: $child directTargetChild: $directTargetChild target: $target")
        return true
    }

    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        child: Button,
        target: View,
        dx: Int,
        dy: Int,
        consumed: IntArray,type:Int
    ) {
        println("zijiexiaozhan behavior onNestedPreScroll child: $child target: $target dx: $dx dy:$dy" )
        consumed[0]= dx*3/4
        consumed[1]=dy*3/4
        child.offsetTopAndBottom(-consumed[1])
//        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
    }

    override fun onNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: Button,
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int,
        consumed: IntArray
    ) {
        child.offsetTopAndBottom(dyConsumed)

        println("zijiexiaozhan behavior onNestedScroll child: $child target: $target dxConsumed: $dxConsumed dyConsumed:$dyConsumed dxUnconsumed:$dxUnconsumed dyUnconsumed:$dyUnconsumed" )

        super.onNestedScroll(
            coordinatorLayout,
            child,
            target,
            dxConsumed,
            dyConsumed,
            dxUnconsumed,
            dyUnconsumed,
            type,
            consumed
        )
    }
    override fun onNestedScrollAccepted(
        coordinatorLayout: CoordinatorLayout,
        child: Button,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ) {
        println("zijiexiaozhan behavior onNestedScrollAccepted child: $child directTargetChild: $directTargetChild target: $target" )

        super.onNestedScrollAccepted(
            coordinatorLayout,
            child,
            directTargetChild,
            target,
            axes,
            type
        )
    }
}