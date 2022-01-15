package com.example.timerapp.adapters

import android.view.View

interface OnItemClickListener<T> {
    fun onItemClick(view: View, data: T)
}