package com.example.gamificationapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gamificationapp.R

class LevelsRecycleAdapter(private val dataSet: ArrayList<Int>) :
    RecyclerView.Adapter<LevelsRecycleAdapter.ViewHolder>() {

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
        viewHolder.textViewLevelNumber.text = dataSet[position].toString()
    }

    override fun getItemCount() = dataSet.size

}