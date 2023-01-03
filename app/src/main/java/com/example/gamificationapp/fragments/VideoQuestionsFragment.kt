package com.example.gamificationapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.gamificationapp.R

class VideoQuestionsFragment: Fragment() {

    private lateinit var textViewQuestion1 : TextView
    private lateinit var textViewQuestion2 : TextView
    private lateinit var textViewQuestion3 : TextView
    private val questions = listOf("Is RMI a java specific technology?", "Is HTTP a protocol?", "Is REST a design" +
            " pattern?")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_video_questions, container, false)
        initializeViews(view)
        return view
    }

    private fun initializeViews(view : View) {
        textViewQuestion1 = view.findViewById(R.id.id_text_view_question_1)
        textViewQuestion2 = view.findViewById(R.id.id_text_view_question_2)
        textViewQuestion3 = view.findViewById(R.id.id_text_view_question_3)
        textViewQuestion1.text = questions[0]
        textViewQuestion2.text = questions[1]
        textViewQuestion3.text = questions[2]
    }

}