package com.peter.viewgrouptutorial.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.peter.viewgrouptutorial.R

class ButtonAdapter : RecyclerView.Adapter<ButtonAdapter.ButtonViewHolder>() {
    inner class ButtonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: Button? = itemView.findViewById(R.id.button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonAdapter.ButtonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_button, parent, false)
        return ButtonViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 20
    }

    override fun onBindViewHolder(holder: ButtonAdapter.ButtonViewHolder, position: Int) {
        holder.textView?.text = "button $position"
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }
}