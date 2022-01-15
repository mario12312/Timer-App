package com.example.timerapp.ui.users

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.timerapp.model.User
import kotlinx.android.synthetic.main.item_user.view.*
import java.util.*

class RecyclerViewViewHolder(val view: View) :
    RecyclerView.ViewHolder(view) {

        val user_title = view.user_title
        val row_user = view.row_user
        val completed = false
        fun bind(data: User){
            user_title.text = data.title
            if(data.completed){
                val random = Random()
                val color: Int =
                    Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
                row_user.setCardBackgroundColor(color)
            }
        }
}