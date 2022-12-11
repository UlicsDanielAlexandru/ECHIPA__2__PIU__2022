package com.example.gamificationapp

import android.content.DialogInterface.OnClickListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class ButtonsFragment : Fragment() {
    // Test variables
    lateinit var screen1: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_buttons, container, false)
        screen1 = view.findViewById(R.id.screen1)
        return view
    }

    public fun screen1Listener(onClickListener: View.OnClickListener) {
        screen1.setOnClickListener(onClickListener)
    }
}