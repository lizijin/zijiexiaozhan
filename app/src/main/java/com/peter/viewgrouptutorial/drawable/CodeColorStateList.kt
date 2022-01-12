package com.peter.viewgrouptutorial.drawable

import android.content.res.ColorStateList
import android.util.SparseArray
import androidx.annotation.ColorInt
import java.lang.ref.WeakReference

class CodeColorStateList(val states: Array<out IntArray>, val colors: IntArray) : ColorStateList(states, colors) {
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

                // Prune the cache before adding new items.
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
        return states.contentEquals(other.states) && colors.contentEquals(other.colors)
    }

    override fun hashCode(): Int {
        return 31 * states.contentHashCode() + colors.contentHashCode()
    }

    override fun toString(): String {
        return "CodeColorStateList(states=${states.contentToString()}, colors=${colors.contentToString()})"
    }


}