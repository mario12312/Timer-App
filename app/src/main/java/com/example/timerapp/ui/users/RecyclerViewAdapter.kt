package com.example.timerapp.ui.users

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.timerapp.R
import com.example.timerapp.model.User
import kotlinx.android.synthetic.main.item_user.view.*
import java.util.*

import javax.inject.Inject

class RecyclerViewAdapter @Inject constructor(val ctx: Context, var clickListener: OnUserClick) :
    RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    private var listData: List<User>? = null
    var onItemClick: ((User) -> Unit)? = null

    fun setlistData(listData: List<User>){
        this.listData = listData
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user,parent,false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        if(listData?.size != null && listData?.size!! > 0){
            return listData?.size!!
        }else{
            return 0
        }
    }
    interface OnUserClick {
        fun onItemClick(list: List<User>,position: Int )
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.MyViewHolder, position: Int) {
        holder.bind(listData?.get(position)!!)
    }

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val user_title = view.user_title
        val row_user = view.row_user
        init {
            view.setOnClickListener {
                onItemClick?.invoke(listData!![adapterPosition])
            }
        }
        fun bind(data: User){
            user_title.text = data.title
            if(data.completed){
                val random = Random()
                val color: Int =
                    Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
                row_user.setCardBackgroundColor(color)
            }
            else{
                row_user.setCardBackgroundColor(Color.parseColor("#ffffff"))
            }
        }
    }
}