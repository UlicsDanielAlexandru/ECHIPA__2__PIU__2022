package com.example.gamificationapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.gamificationapp.R

class CollaborativeMethodSelectionFragment : Fragment() {

    lateinit var textViewExperimented : TextView
    lateinit var textViewCollaborative : TextView
    lateinit var userSelectionFragment: UserSelectionFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_collaborative_method_selection, container, false)
        textViewExperimented = view.findViewById(R.id.id_text_view_experimented_method)
        textViewCollaborative = view.findViewById(R.id.id_text_view_collaborative_method)
        userSelectionFragment = UserSelectionFragment()
        setListeners()
        return view
    }

    private fun setListeners() {
        textViewExperimented.setOnClickListener {
            userSelectionFragment.experimented = true
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.id_frame_layout_fragment, userSelectionFragment)
                addToBackStack("levels fragment")
                commit()
            }
        }
        textViewCollaborative.setOnClickListener {
            userSelectionFragment.experimented = false
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.id_frame_layout_fragment, userSelectionFragment)
                addToBackStack("levels fragment")
                commit()
            }
        }
    }

}