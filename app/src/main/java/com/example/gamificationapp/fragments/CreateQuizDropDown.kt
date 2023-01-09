package com.example.gamificationapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import com.example.gamificationapp.R


class CreateQuizDropDown : Fragment(R.layout.fragment_create_quiz_drop_down) {

    lateinit var spinner: Spinner
    lateinit var next: TextView

    private lateinit var timeSelectionFragment: TimeSelectionFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_create_quiz_drop_down, container, false)
        next = view.findViewById(R.id.id_text_view_next_create_quiz_drag_drop)
        spinner = view.findViewById(R.id.id_spinner_drag_drop)

        val list1: MutableList<String> = ArrayList()
        for (i in 1..10)
            list1.add("   $i")

        val dataAdapter1: ArrayAdapter<String> =
            ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, list1)
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = dataAdapter1

        next.setOnClickListener {
            timeSelectionFragment = TimeSelectionFragment()
            activity?.intent?.putExtra("quiz", true)
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.id_frame_layout_fragment, timeSelectionFragment)
                addToBackStack("time selection fragment")
                commit()
            }
        }

        return view
    }
}