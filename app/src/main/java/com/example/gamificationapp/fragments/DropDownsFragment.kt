package com.example.gamificationapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.gamificationapp.R

class DropDownsFragment : Fragment(R.layout.fragment_create_game_drop_downs) {
    lateinit var dragDropSpinner: Spinner
    lateinit var multipleSpinner: Spinner
    lateinit var fillGapSpinner: Spinner
    lateinit var videoAttentionSpinner: Spinner
    lateinit var next: TextView

    var timeSelectionFragment: TimeSelectionFragment = TimeSelectionFragment()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_create_game_drop_downs, container, false)
        dragDropSpinner = view.findViewById(R.id.id_spinner_drag_drop)
        multipleSpinner = view.findViewById(R.id.id_spinner_multiple_choice)
        fillGapSpinner = view.findViewById(R.id.id_spinner_fill_the_gap)
        videoAttentionSpinner = view.findViewById(R.id.id_spinner_video_attention)
        next = view.findViewById(R.id.id_text_view_next_drop_downs)

        val list1: MutableList<String> = ArrayList()
        val list2: MutableList<String> = ArrayList()
        val list3: MutableList<String> = ArrayList()
        val list4: MutableList<String> = ArrayList()

        for(i in 1 .. 4)
            list1.add("   $i")

        for(i in 1 .. 6)
            list2.add("   $i")

        for(i in 1 .. 10)
            list3.add("   $i")

        for(i in 1 .. 2)
            list4.add("   $i")

        val dataAdapter1: ArrayAdapter<String> =
            ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, list1)

        val dataAdapter2: ArrayAdapter<String> =
            ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, list2)

        val dataAdapter3: ArrayAdapter<String> =
            ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, list3)

        val dataAdapter4: ArrayAdapter<String> =
            ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, list4)

        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        dataAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        dragDropSpinner.adapter = dataAdapter1
        multipleSpinner.adapter = dataAdapter3
        fillGapSpinner.adapter = dataAdapter2
        videoAttentionSpinner.adapter = dataAdapter4

        next.setOnClickListener{
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.id_frame_layout_fragment, timeSelectionFragment)
                addToBackStack("time selection fragment")
                commit()
            }
        }

        return view
    }


}