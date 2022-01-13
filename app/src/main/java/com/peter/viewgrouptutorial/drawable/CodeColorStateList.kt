package com.peter.viewgrouptutorial.drawable

import android.content.res.ColorStateList
import android.util.SparseArray
import androidx.annotation.ColorInt
import java.lang.ref.WeakReference

class CodeColorStateList private constructor(
    private val states: Array<out IntArray>,
    private val colors: IntArray
) : ColorStateList(states, colors) {
    companion object {
        private val EMPTY = arrayOf(IntArray(0))

        /** Thread-safe cache of single-color ColorStateLists.  */
        private val sCache = SparseArray<WeakReference<CodeColorStateList>>()

        /**
         * @return A ColorStateList containing a single color.
         */
        fun valueOf(@ColorInt color: Int): CodeColorStateList {
            synchronized(sCache) {
                val index: Int = sCache.indexOfKey(color)
                if (index >= 0) {
                    val cached: CodeColorStateList? = sCache.valueAt(index).get()
                    if (cached != null) {
                        return cached
                    }

                    // Prune missing entry.
                    sCache.removeAt(index)
                }

                // Prune the cache before adding new ColorItems.
                val N: Int = sCache.size()
                for (i in N - 1 downTo 0) {
                    if (sCache.valueAt(i).get() == null) {
                        sCache.removeAt(i)
                    }
                }
                val csl = CodeColorStateList(EMPTY, intArrayOf(color))
                sCache.put(color, WeakReference(csl))
                return csl
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as CodeColorStateList
        return states.contentDeepEquals(other.states) && colors.contentEquals(other.colors)
    }

    override fun hashCode(): Int {
        return 31 * states.contentDeepHashCode() + colors.contentHashCode()
    }

    override fun toString(): String {
        return "CodeColorStateList(states=${states.contentToString()}, colors=${colors.contentToString()})"
    }

    class Builder {
        private var colorItems: MutableList<SelectorColorItem> = arrayListOf()
        private var maxAttributeNum = 0

        fun addSelectorItem(colorItemBuilder: SelectorColorItem.Builder) = apply {
            val colorItem = colorItemBuilder.build()
            colorItems.add(colorItem)
            maxAttributeNum = maxAttributeNum.coerceAtLeast(colorItem.states.size)
        }

        fun build(): CodeColorStateList {
            if (colorItems.isEmpty()) throw IllegalArgumentException("colorItems is empty")
            val colors = IntArray(colorItems.size)

            //初始化数组
            val states = Array(colorItems.size) {
                IntArray(maxAttributeNum) {
                    0
                }
            }
            colorItems.forEachIndexed { index, colorItem ->
                colors[index] = colorItem.color
                colorItem.states.forEachIndexed { index2, state ->
                    states[index][index2] = state
                }
            }
            return CodeColorStateList(states, colors)
        }
    }
}

class SelectorColorItem private constructor(val color: Int, val states: MutableList<Int>) {
    class Builder {
        private var color: Int = -1
        private var states: MutableList<Int> = arrayListOf()

        fun color(color: Int): Builder = apply {
            this.color = color
        }

        //动态添加正向状态 android:state_pressed="true"
        fun addState(state: State): Builder = apply {
            if (!states.contains(state.value)) {
                states.add(state.value)
            }
        }

        //动态添加逆向状态 android:state_pressed="false"
        fun minusState(state: State): Builder = apply {
            if (!states.contains(-state.value)) {
                states.add(-state.value)
            }
        }

        internal fun build(): SelectorColorItem {
            if (color === -1) throw IllegalArgumentException("must call color method")

            /** 两个SelectorColorItem添加state的内容相同，添加顺序不同，会被认为是相同
             * @see CodeColorStateList.equals(other: Any?)
             */
            states.sort()
            return SelectorColorItem(color, states)
        }
    }
}

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
