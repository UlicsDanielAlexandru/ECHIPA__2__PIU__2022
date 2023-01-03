package com.example.gamificationapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.gamificationapp.R

class MultipleChoiceFragment: Fragment() {

    private val answers : ArrayList<String> = arrayListOf("SCALABILITY", "SYSTEM", "TOLERANCE", "AVAILABILITY")
    private val question : String = "What does RMI stand for?"
    private lateinit var textViewQuestion : TextView
    private lateinit var textViewAnswer1 : TextView
    private lateinit var textViewAnswer2 : TextView
    private lateinit var textViewAnswer3 : TextView
    private lateinit var textViewAnswer4 : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_multiple_choice, container, false)
        textViewQuestion = view.findViewById(R.id.id_text_view_question)
        textViewQuestion.text = question
        textViewAnswer1 = view.findViewById(R.id.id_text_view_answer_1)
        textViewAnswer1.text = answers[0]
        textViewAnswer2 = view.findViewById(R.id.id_text_view_answer_2)
        textViewAnswer2.text = answers[1]
        textViewAnswer3 = view.findViewById(R.id.id_text_view_answer_3)
        textViewAnswer3.text = answers[2]
        textViewAnswer4 = view.findViewById(R.id.id_text_view_answer_4)
        textViewAnswer4.text = answers[3]
        return view
    }

}