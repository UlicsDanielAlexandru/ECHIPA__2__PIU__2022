package com.example.gamificationapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.gamificationapp.R


class MakeUserStatusFragment : Fragment(R.layout.fragment_make_user_status) {

    lateinit var visibilityButton: TextView
    lateinit var layoutVisibilities: LinearLayout

    lateinit var circleGreen: TextView
    lateinit var circleYellow: TextView
    lateinit var circleRed: TextView
    lateinit var circleWhite: TextView

    lateinit var optionGreen: TextView
    lateinit var optionYellow: TextView
    lateinit var optionRed: TextView
    lateinit var optionWhite: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view =
            inflater.inflate(R.layout.fragment_make_user_status, container, false)
        visibilityButton = view.findViewById(R.id.id_text_view_status_circle)
        layoutVisibilities = view.findViewById(R.id.id_layout_status_list)

        circleGreen = view.findViewById(R.id.id_text_view_circle_green)
        circleYellow = view.findViewById(R.id.id_text_view_circle_yellow)
        circleRed = view.findViewById(R.id.id_text_view_circle_red)
        circleWhite = view.findViewById(R.id.id_text_view_circle_gray)

        optionGreen = view.findViewById(R.id.id_text_view_option_available)
        optionYellow = view.findViewById(R.id.id_text_view_option_away)
        optionRed = view.findViewById(R.id.id_text_view_option_busy)
        optionWhite = view.findViewById(R.id.id_text_view_option_invisible)

        visibilityButton.setOnClickListener {
            layoutVisibilities.isVisible = !layoutVisibilities.isVisible
        }

        circleGreen.setOnClickListener {
            visibilityButton.background = resources.getDrawable(R.drawable.circle_green)
            layoutVisibilities.isVisible = false
        }
        optionGreen.setOnClickListener {
            visibilityButton.background = resources.getDrawable(R.drawable.circle_green)
            layoutVisibilities.isVisible = false
        }
        circleYellow.setOnClickListener {
            visibilityButton.background = resources.getDrawable(R.drawable.circle_yellow)
            layoutVisibilities.isVisible = false
        }
        optionYellow.setOnClickListener {
            visibilityButton.background = resources.getDrawable(R.drawable.circle_yellow)
            layoutVisibilities.isVisible = false
        }
        circleRed.setOnClickListener {
            visibilityButton.background = resources.getDrawable(R.drawable.circle_red)
            layoutVisibilities.isVisible = false
        }
        optionRed.setOnClickListener {
            visibilityButton.background = resources.getDrawable(R.drawable.circle_red)
            layoutVisibilities.isVisible = false
        }
        circleWhite.setOnClickListener {
            visibilityButton.background = resources.getDrawable(R.drawable.circle_white)
            layoutVisibilities.isVisible = false
        }
        optionWhite.setOnClickListener {
            visibilityButton.background = resources.getDrawable(R.drawable.circle_white)
            layoutVisibilities.isVisible = false
        }
        

        return view
    }
}