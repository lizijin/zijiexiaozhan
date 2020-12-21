package com.peter.viewgrouptutorial.dispatchevent

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ScrollView
import androidx.coordinatorlayout.widget.CoordinatorLayout

class DependentBehavior : MyBehavior {
    constructor() : super()
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    init {
        name = "textView Behavior"
    }

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        return dependency is ScrollView
    }

}