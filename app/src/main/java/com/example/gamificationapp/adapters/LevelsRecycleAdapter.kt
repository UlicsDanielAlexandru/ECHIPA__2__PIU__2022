package com.example.gamificationapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gamificationapp.R
import com.example.gamificationapp.fragments.DragAndDropFragment

class LevelsRecycleAdapter(private val dataSet: ArrayList<Int>, private val clickListener: OnClickListener) :
    RecyclerView.Adapter<LevelsRecycleAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewLevelNumber : TextView
        val imageViewLevelBox : ImageView

        init {
            textViewLevelNumber = view.findViewById(R.id.id_text_view_level_number)
            imageViewLevelBox = view.findViewById(R.id.id_image_view_level_box)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_levels_list, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textViewLevelNumber.text = dataSet[position].toString()
        viewHolder.imageViewLevelBox.setOnClickListener(clickListener)
    }

    override fun getItemCount() = dataSet.size

}