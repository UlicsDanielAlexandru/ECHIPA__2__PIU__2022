package com.example.gamificationapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gamificationapp.R
import java.util.*
import kotlin.concurrent.schedule

class ScoreboardQuizCreateFragment : Fragment(R.layout.fragment_scoreboard_quiz_create) {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_scoreboard_quiz_create,container,false)
        val creatingLearningMethodsFragment = CreatingMethodsFragment()

        Timer("SettingUp", false).schedule(6000) {
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.id_frame_layout_fragment, creatingLearningMethodsFragment)
                addToBackStack("back to CREATING methods fragment")
                commit()
            }
        }
        return view
    }
}