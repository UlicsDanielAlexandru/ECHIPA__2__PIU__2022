package com.example.gamificationapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.gamificationapp.R

class ButtonsFragment(
    var clickListener1: OnClickListener,
    var clickListener2: OnClickListener,
    var clickListener3: OnClickListener
) : Fragment() {
    // Test variables
    lateinit var screen1: Button
    lateinit var screen11: Button
    lateinit var screen12: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_buttons, container, false)
        screen1 = view.findViewById(R.id.screen1)
        screen1.setOnClickListener(clickListener1)
        screen11 = view.findViewById(R.id.screen11)
        screen11.setOnClickListener(clickListener2)
        screen12 = view.findViewById(R.id.screen12)
        screen12.setOnClickListener(clickListener3)
        return view
    }

}