package com.example.gamificationapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.gamificationapp.R

class ButtonsFragment(var clickListener1: android.view.View.OnClickListener,
                      var clickListener2: android.view.View.OnClickListener,
                      var clickListener3: android.view.View.OnClickListener,
                      var clickListener4: android.view.View.OnClickListener,
                      var clickListener5: android.view.View.OnClickListener,
                      var clickListener6: android.view.View.OnClickListener,
                      var clickListener7: android.view.View.OnClickListener,
                      var clickListener8: android.view.View.OnClickListener,
                      var clickListener9: android.view.View.OnClickListener) : Fragment() {
    // Test variables
    lateinit var screen1: Button
    lateinit var screen2: Button
    lateinit var screen3: Button
    lateinit var screen4: Button
    lateinit var screen5: Button
    lateinit var screen6: Button
    lateinit var screen7: Button
    lateinit var screen8: Button
    lateinit var screen9: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_buttons, container, false)
        screen1 = view.findViewById(R.id.screen1)
        screen2 = view.findViewById(R.id.screen2)
        screen3 = view.findViewById(R.id.screen3)
        screen4 = view.findViewById(R.id.screen4)
        screen5 = view.findViewById(R.id.screen5)
        screen6 = view.findViewById(R.id.screen6)
        screen7 = view.findViewById(R.id.screen7)
        screen8 = view.findViewById(R.id.screen8)
        screen9 = view.findViewById(R.id.screen9)
        screen1.setOnClickListener(clickListener1)
        screen2.setOnClickListener(clickListener2)
        screen3.setOnClickListener(clickListener3)
        screen4.setOnClickListener(clickListener4)
        screen5.setOnClickListener(clickListener5)
        screen6.setOnClickListener(clickListener6)
        screen7.setOnClickListener(clickListener7)
        screen8.setOnClickListener(clickListener8)
        screen9.setOnClickListener(clickListener9)
        return view
    }

}