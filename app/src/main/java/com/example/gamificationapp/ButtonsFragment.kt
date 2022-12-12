package com.example.gamificationapp

import android.content.DialogInterface.OnClickListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class ButtonsFragment(var clickListener: android.view.View.OnClickListener) : Fragment() {
    // Test variables
    lateinit var screen21: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_buttons, container, false)
        screen21 = view.findViewById(R.id.screen1)
        screen21.setOnClickListener(clickListener)
//        screen21 = view.findViewById(R.id.screen2)
//        screen21.setOnClickListener(clickListener21)


        return view
    }

}