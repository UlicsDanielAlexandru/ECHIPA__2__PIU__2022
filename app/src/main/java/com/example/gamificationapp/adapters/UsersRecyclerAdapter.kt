package com.example.gamificationapp.adapters

import android.content.Context
import android.provider.ContactsContract.CommonDataKinds.Im
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.gamificationapp.R
import com.example.gamificationapp.models.Status
import com.example.gamificationapp.models.UserItem
import kotlinx.coroutines.selects.select

class UsersRecyclerAdapter(private val dataSet: ArrayList<UserItem>, var experimented: Boolean = true,
    private val clickListener: OnClickListener, var button : Button, var textView: TextView) :
    RecyclerView.Adapter<UsersRecyclerAdapter.ViewHolder>() {

    lateinit var context : Context
    var selectedNr = 0

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewUsername : TextView
        val imageViewStatus : ImageView
        val imageViewImage : ImageView
        val imageViewChat : ImageView
        val imageViewXmark : ImageView
        val imageViewCheck : ImageView

        init {
            textViewUsername = view.findViewById(R.id.id_text_view_user_username)
            imageViewStatus = view.findViewById(R.id.id_image_view_user_status)
            imageViewImage = view.findViewById(R.id.id_image_view_user_image)
            imageViewChat = view.findViewById(R.id.id_image_view_chat)
            imageViewXmark = view.findViewById(R.id.id_image_view_xmark)
            imageViewCheck = view.findViewById(R.id.id_image_view_check)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view : View = if(experimented)
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_users_experimented_list, viewGroup, false)
        else
            LayoutInflater.from(viewGroup.context)
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
        if(experimented)
            viewHolder.imageViewChat.setOnClickListener(clickListener)
        else
            viewHolder.imageViewChat.setOnClickListener {
                if(dataSet[position].selected) {
                    viewHolder.imageViewCheck.visibility = View.INVISIBLE
                    viewHolder.imageViewXmark.visibility = View.INVISIBLE
                    viewHolder.imageViewChat.backgroundTintList = ContextCompat.getColorStateList(context, R.color.text_grey)
                    selectedNr--
                }
                else {
                    viewHolder.imageViewCheck.visibility = View.VISIBLE
                    viewHolder.imageViewXmark.visibility = View.VISIBLE
                    viewHolder.imageViewChat.backgroundTintList = ContextCompat.getColorStateList(context, R.color.text_grey_transparent)
                    selectedNr++
                }
                dataSet[position].selected = !dataSet[position].selected
                if(selectedNr > 0)
                    button.visibility = View.VISIBLE
                else
                    button.visibility = View.INVISIBLE
                textView.text = selectedNr.toString()
            }
    }

    override fun getItemCount() = dataSet.size

}