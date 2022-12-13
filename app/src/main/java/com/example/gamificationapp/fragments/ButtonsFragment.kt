package com.example.gamificationapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.gamificationapp.R

class ButtonsFragment(var clickListener: android.view.View.OnClickListener,
                      var clickListener21: android.view.View.OnClickListener,
                      var clickListener22: android.view.View.OnClickListener) : Fragment() {
    // Test variables
    lateinit var screen1: Button
    lateinit var screen21: Button
    lateinit var screen22: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_buttons, container, false)
        screen1 = view.findViewById(R.id.screen1)
        screen1.setOnClickListener(clickListener)
        screen21 = view.findViewById(R.id.screen21)
        screen21.setOnClickListener(clickListener21)
        screen22 = view.findViewById(R.id.screen22)
        screen22.setOnClickListener(clickListener22)
        return view
    }
}