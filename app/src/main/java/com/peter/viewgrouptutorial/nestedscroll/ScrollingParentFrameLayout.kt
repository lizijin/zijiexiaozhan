package com.peter.viewgrouptutorial.nestedscroll

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.view.NestedScrollingParent2
import androidx.core.view.NestedScrollingParentHelper
import com.peter.viewgrouptutorial.dispatchevent.MyFrameLayout

//Q1 为何这个onNestedPreScroll 不需要child 参数 为何onStartNestedScroll需要child 参数
class ScrollingParentFrameLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : MyFrameLayout(context, attrs, defStyleAttr), NestedScrollingParent2, Naming {
    private val mParentHelper: NestedScrollingParentHelper = NestedScrollingParentHelper(this)
    override var name: String = ""
    override fun onNestedScrollAccepted(child: View, target: View, axes: Int, type: Int) {
        Log.d(
            "zijiexiaozhan",
            "$name onNestedScrollAccepted child: ${if (child is Naming) child.getNaming() else child} target: ${if (target is Naming) target.getNaming() else target} "
        )
        mParentHelper.onNestedScrollAccepted(child,target,axes,type)
    }

    override fun onStartNestedScroll(child: View, target: View, axes: Int, type: Int): Boolean {
        Log.d(
            "zijiexiaozhan",
            "$name onStartNestedScroll child: ${if (child is Naming) child.getNaming() else child} target: ${if (target is Naming) target.getNaming() else target} "
        )
        return true
    }

    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        Log.d(
            "zijiexiaozhan",
            "$name onNestedPreScroll target: ${if (target is Naming) target.getNaming() else target} "
        )
    }

    override fun onNestedScroll(
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int
    ) {
        Log.d(
            "zijiexiaozhan",
            "$name onNestedScroll target: ${if (target is Naming) target.getNaming() else target} "
        )
    }

    override fun onStopNestedScroll(target: View, type: Int) {
        Log.d(
            "zijiexiaozhan",
            "$name onStopNestedScroll target: ${if (target is Naming) target.getNaming() else target} "
        )
        mParentHelper.onStopNestedScroll(target,type)
    }

    override fun getNaming(): String {
        return name
    }
}