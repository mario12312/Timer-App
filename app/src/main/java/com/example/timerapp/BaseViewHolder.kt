package com.example.timerapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.timerapp.network.users.RecyclerData

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(data: RecyclerData)
}