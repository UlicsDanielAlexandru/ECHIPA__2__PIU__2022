package com.example.gamificationapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.gamificationapp.AppActivity
import com.example.gamificationapp.R
import java.util.*
import kotlin.concurrent.schedule


class ScoreboardQuizFragment : Fragment() {
    lateinit var learningMethodsFragment: LearningMethodsFragment
    private lateinit var first_place_name: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_scoreboard_quiz,container,false)
        learningMethodsFragment = LearningMethodsFragment()

        first_place_name = view.findViewById(R.id.first_place_name)
        first_place_name.text = activity?.intent?.getStringExtra("username")

        Timer("SettingUp", false).schedule(6000) {
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.id_frame_layout_fragment,learningMethodsFragment)
                addToBackStack("back to learning methods fragment")
                commit()
            }
        }
        return view
    }
}