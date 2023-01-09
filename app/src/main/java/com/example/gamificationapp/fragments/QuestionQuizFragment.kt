package com.example.gamificationapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.gamificationapp.R


class QuestionQuizFragment : Fragment() {
    lateinit var false_answer: TextView
    lateinit var correct_answer: TextView
    lateinit var correctAnswerQuizFragment: CorrectAnswerQuizFragment
    lateinit var wrongAnswerQuizFragment: WrongAnswerQuizFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_question_quiz,container,false)
        correctAnswerQuizFragment = CorrectAnswerQuizFragment()
        wrongAnswerQuizFragment = WrongAnswerQuizFragment()

        false_answer = view.findViewById(R.id.false_answer)
        correct_answer = view.findViewById(R.id.correct_answer)

        setListeners()
        return view
    }

    private fun setListeners(){
        correct_answer.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.id_frame_layout_fragment,correctAnswerQuizFragment)
                addToBackStack("correct answer quiz fragment")
                commit()
            }
        }
        false_answer.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.id_frame_layout_fragment,wrongAnswerQuizFragment)
                addToBackStack("wrong answer quiz fragment")
                commit()
            }
        }
    }
}