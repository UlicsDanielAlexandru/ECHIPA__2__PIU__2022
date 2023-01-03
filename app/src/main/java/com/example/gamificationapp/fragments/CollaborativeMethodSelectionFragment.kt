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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_collaborative_method_selection, container, false)
        textViewExperimented = view.findViewById(R.id.id_text_view_experimented_method)
        textViewExperimented.setOnClickListener { Toast.makeText(this.context, "TEST", Toast.LENGTH_SHORT).show() }
        return view
    }

}