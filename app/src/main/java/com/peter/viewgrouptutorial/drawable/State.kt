package com.peter.viewgrouptutorial.drawable

/** 将系统android.R.attr.state_checked 等常量封装，方便调用**/
sealed class State private constructor(val value: Int)

/** @see android.R.attr.state_activated*/
object StateActivated : State(android.R.attr.state_activated)

/** @see android.R.attr.state_active*/
object StateActive : State(android.R.attr.state_active)

object StateCheckable : State(android.R.attr.state_checkable)

object StateChecked : State(android.R.attr.state_checked)

object StateEnabled : State(android.R.attr.state_enabled)

object StateFirst : State(android.R.attr.state_first)

object StateFocused : State(android.R.attr.state_focused)

object StateLast : State(android.R.attr.state_last)

object StateMiddle : State(android.R.attr.state_middle)

object StatePressed : State(android.R.attr.state_pressed)

object StateSelected : State(android.R.attr.state_selected)

object StateSingle : State(android.R.attr.state_single)

object StateWindowFocused : State(android.R.attr.state_window_focused)