package com.example.gamificationapp.adapters

import android.app.Activity
import android.text.TextUtils.replace
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gamificationapp.R
import com.example.gamificationapp.fragments.CreateDragDropFragment

class CreateLevelsRecyclerAdapter(private val dataSet: ArrayList<String>,  private val clickListener: View.OnClickListener): RecyclerView.Adapter<CreateLevelsRecyclerAdapter.ViewHolder>() {

    var createDropDownsFragment: CreateDragDropFragment = CreateDragDropFragment()

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val textViewLevelNumber : TextView

            init {
                textViewLevelNumber = view.findViewById(R.id.id_text_view_level_number)
            }
        }

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_levels_list, viewGroup, false)

            return ViewHolder(view)
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            viewHolder.textViewLevelNumber.text = dataSet[position]
            if(viewHolder.textViewLevelNumber.text == "+")
            {
                viewHolder.itemView.setOnClickListener(clickListener)
            }
        }

        override fun getItemCount() = dataSet.size
}