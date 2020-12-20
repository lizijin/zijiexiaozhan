package com.peter.viewgrouptutorial

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ScrollView
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout

class MyBehavior : CoordinatorLayout.Behavior<TextView> {
    constructor() : super()
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: TextView,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        return super.onStartNestedScroll(
            coordinatorLayout,
            child,
            directTargetChild,
            target,
            axes,
            type
        )
    }

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: TextView,
        dependency: View
    ): Boolean {
//        return super.layoutDependsOn(parent, child, dependency)
        return dependency is ScrollView
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: TextView,
        dependency: View
    ): Boolean {
        if (dependency !is ScrollView) return false
        var scrollView = dependency as ScrollView
        child.offsetLeftAndRight(scrollView.scrollY / 100)
//        scrollView.height
        return true
    }
}