package com.example.gamificationapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.gamificationapp.R


class Question1QuizFragment : Fragment() {
    lateinit var first_answer: TextView
    lateinit var second_answer: TextView
    lateinit var third_answer: TextView
    lateinit var forth_answer: TextView
    lateinit var x_2: ImageView
    lateinit var x_3: ImageView
    lateinit var x_4: ImageView
    var x: String = "0"

    lateinit var wrongAnswerQuiz1Fragment: WrongAnswerQuiz1Fragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_question_1_quiz,container,false)
        val view1 = inflater.inflate(R.layout.fragment_wrong_answer_quiz_1,container,false)

        wrongAnswerQuiz1Fragment = WrongAnswerQuiz1Fragment(x)
        first_answer = view.findViewById(R.id.first_answer)
        second_answer = view.findViewById(R.id.second_answer)
        third_answer = view.findViewById(R.id.third_answer)
        forth_answer = view.findViewById(R.id.forth_answer)
        x_2 = view1.findViewById(R.id.x_2)
        x_3 = view1.findViewById(R.id.x_3)
        x_4 = view1.findViewById(R.id.x_4)
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
            wrongAnswerQuiz1Fragment.x="2"
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.id_frame_layout_fragment,wrongAnswerQuiz1Fragment)
                addToBackStack("wrong answer quiz fragment")
                commit()
            }
        }
        third_answer.setOnClickListener {
            wrongAnswerQuiz1Fragment.x="3"
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.id_frame_layout_fragment,wrongAnswerQuiz1Fragment)
                addToBackStack("wrong answer quiz fragment")
                commit()
            }
        }
        forth_answer.setOnClickListener {
            wrongAnswerQuiz1Fragment.x="4"
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.id_frame_layout_fragment,wrongAnswerQuiz1Fragment)
                addToBackStack("wrong answer quiz fragment")
                commit()
            }
        }
    }
}