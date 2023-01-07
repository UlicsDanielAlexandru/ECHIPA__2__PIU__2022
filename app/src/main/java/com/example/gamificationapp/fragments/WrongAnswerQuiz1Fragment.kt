package com.example.gamificationapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.gamificationapp.AppActivity
import com.example.gamificationapp.R
import java.util.*
import kotlin.concurrent.schedule


class WrongAnswerQuiz1Fragment(var x: String) : Fragment() {

    lateinit var scoreboardQuizFragment: ScoreboardQuizFragment

    lateinit var x_2: ImageView
    lateinit var x_3: ImageView
    lateinit var x_4: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_wrong_answer_quiz_1,container,false)
        scoreboardQuizFragment = ScoreboardQuizFragment()
        x_2 = view.findViewById(R.id.x_2)
        x_3 = view.findViewById(R.id.x_3)
        x_4 = view.findViewById(R.id.x_4)

        if(x == "2")
            x_2.visibility=View.VISIBLE
        if(x == "3")
            x_3.visibility=View.VISIBLE
        if(x == "4")
            x_4.visibility=View.VISIBLE

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