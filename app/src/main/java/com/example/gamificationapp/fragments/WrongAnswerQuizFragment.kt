package com.example.gamificationapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gamificationapp.R
import java.util.*
import kotlin.concurrent.schedule


class WrongAnswerQuizFragment : Fragment(){
    lateinit var question1QuizFragment: Question1QuizFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_wrong_answer_quiz,container,false)
        question1QuizFragment = Question1QuizFragment()

        Timer("SettingUp", false).schedule(5000) {
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.id_frame_layout_fragment,question1QuizFragment)
                addToBackStack("question 1 quiz fragment")
                commit()
            }
        }
        return view
    }
}