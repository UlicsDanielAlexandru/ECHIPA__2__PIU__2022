package com.example.gamificationapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.gamificationapp.R


class Question1QuizFragment : Fragment() {
    lateinit var first_answer: TextView
    lateinit var second_answer: TextView
    lateinit var third_answer: TextView
    lateinit var forth_answer: TextView

    lateinit var wrongAnswerQuizFragment: WrongAnswerQuizFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_question_1_quiz,container,false)
        wrongAnswerQuizFragment = WrongAnswerQuizFragment()
        first_answer = view.findViewById(R.id.first_answer)
        second_answer = view.findViewById(R.id.second_answer)
        third_answer = view.findViewById(R.id.second_answer)
        forth_answer = view.findViewById(R.id.second_answer)

        setListeners()
        return view
    }

    private fun setListeners(){
        first_answer.setOnClickListener {
            activity?.runOnUiThread {
                Toast.makeText(context, "Correct answer", Toast.LENGTH_SHORT).show()
            }
        }
        second_answer.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.id_frame_layout_fragment,wrongAnswerQuizFragment)
                addToBackStack("wrong answer quiz fragment")
                commit()
            }
        }
    }
}