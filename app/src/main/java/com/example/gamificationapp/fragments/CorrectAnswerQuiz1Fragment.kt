package com.example.gamificationapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gamificationapp.R
import java.util.*
import kotlin.concurrent.schedule


class CorrectAnswerQuiz1Fragment() : Fragment() {

    lateinit var scoreboardQuizFragment: ScoreboardQuizFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_correct_answer_quiz_1,container,false)
        scoreboardQuizFragment = ScoreboardQuizFragment()

        Timer("SettingUp", false).schedule(5000) {
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.id_frame_layout_fragment,scoreboardQuizFragment)
                addToBackStack("scoreboard quiz fragment")
                commit()
            }
        }
        return view
    }
}