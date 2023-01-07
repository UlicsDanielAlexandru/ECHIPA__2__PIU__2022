package com.example.gamificationapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.gamificationapp.R
import com.example.gamificationapp.models.Status
import com.example.gamificationapp.models.UserItem

class UsersRecyclerAdapter(private val dataSet: ArrayList<UserItem>) :
    RecyclerView.Adapter<UsersRecyclerAdapter.ViewHolder>() {

    lateinit var context : Context

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewUsername : TextView
        val imageViewStatus : ImageView
        val imageViewImage : ImageView

        init {
            textViewUsername = view.findViewById(R.id.id_text_view_user_username)
            imageViewStatus = view.findViewById(R.id.id_image_view_user_status)
            imageViewImage = view.findViewById(R.id.id_image_view_user_image)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_users_collaborative_list, viewGroup, false)
        context = view.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textViewUsername.text = dataSet[position].username
        viewHolder.imageViewImage.background = dataSet[position].image
        when(dataSet[position].status) {
            Status.ACTIVE -> viewHolder.imageViewStatus.backgroundTintList = ContextCompat.getColorStateList(context, R.color.green)
            Status.AWAY -> viewHolder.imageViewStatus.backgroundTintList = ContextCompat.getColorStateList(context, R.color.clock_orange)
            Status.BUSY -> viewHolder.imageViewStatus.backgroundTintList = ContextCompat.getColorStateList(context, R.color.red)
            Status.OFFLINE -> viewHolder.imageViewStatus.backgroundTintList = ContextCompat.getColorStateList(context, R.color.text_grey)
            else -> {}
        }
    }

    override fun getItemCount() = dataSet.size

}