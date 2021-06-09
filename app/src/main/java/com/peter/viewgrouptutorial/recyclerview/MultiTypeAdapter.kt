package com.peter.viewgrouptutorial.recyclerview

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MultiTypeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val mDataList: List<Any> = ArrayList()
    private val TEXT_TYPE = 1
    private val BUTTON_TYPE = 2
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TEXT_TYPE) {
            TextHolder(TextView(parent.context))
        } else {
            ButtonHolder(Button(parent.context))
        }
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == TEXT_TYPE) {
            ((holder as TextHolder).textView).text = "text $position"
        } else {
            ((holder as ButtonHolder).button).text = "button $position"
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (mDataList[position] is String) TEXT_TYPE
        else BUTTON_TYPE
    }

    inner class TextHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView = itemView as TextView
    }

    inner class ButtonHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val button: Button = itemView as Button
    }
}