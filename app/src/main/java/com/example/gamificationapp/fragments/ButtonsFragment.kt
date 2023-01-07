package com.example.gamificationapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
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
                      var clickListener9: android.view.View.OnClickListener,
                      var clickListener10: android.view.View.OnClickListener,
                      var clickListener11: android.view.View.OnClickListener,
                      var clickListener12: android.view.View.OnClickListener,
                      var clickListener13: android.view.View.OnClickListener,
                      var clickListener14: android.view.View.OnClickListener,
                      var clickListener15: android.view.View.OnClickListener,
                      var clickListener16: android.view.View.OnClickListener,
                      var clickListener17: android.view.View.OnClickListener,
                      var clickListener18: android.view.View.OnClickListener,
                      var clickListener19: android.view.View.OnClickListener,
                      var clickListener20: android.view.View.OnClickListener,
                      var clickListener21: android.view.View.OnClickListener,
                      var clickListener22: android.view.View.OnClickListener,
                      var clickListener23: android.view.View.OnClickListener,
                      var clickListener24: android.view.View.OnClickListener  ) : Fragment() {
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
    lateinit var screen10: Button
    lateinit var screen11: Button
    lateinit var screen12: Button
    lateinit var screen13: Button
    lateinit var screen14: Button
    lateinit var screen15: Button
    lateinit var screen16: Button
    lateinit var screen17: Button
    lateinit var screen18: Button
    lateinit var screen19: Button
    lateinit var screen20: Button
    lateinit var screen21: Button
    lateinit var screen22: Button
    lateinit var screen23: Button
    lateinit var screen24: Button

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
        screen11 = view.findViewById(R.id.screen11)
        screen12 = view.findViewById(R.id.screen12)
        screen21 = view.findViewById(R.id.screen21)
        screen22 = view.findViewById(R.id.screen22)
        screen23 = view.findViewById(R.id.screen23)
        screen24 = view.findViewById(R.id.screen24)

        screen1.setOnClickListener(clickListener1)
        screen2.setOnClickListener(clickListener2)
        screen3.setOnClickListener(clickListener3)
        screen4.setOnClickListener(clickListener4)
        screen5.setOnClickListener(clickListener5)
        screen6.setOnClickListener(clickListener6)
        screen7.setOnClickListener(clickListener7)
        screen8.setOnClickListener(clickListener8)
        screen9.setOnClickListener(clickListener9)
        screen11.setOnClickListener(clickListener11)
        screen12.setOnClickListener(clickListener12)
        screen21.setOnClickListener(clickListener21)
        screen22.setOnClickListener(clickListener22)
        screen23.setOnClickListener(clickListener23)
        screen24.setOnClickListener(clickListener24)

        return view
    }
}