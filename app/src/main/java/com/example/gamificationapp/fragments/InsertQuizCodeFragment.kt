package com.example.gamificationapp.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.gamificationapp.AppActivity
import com.example.gamificationapp.R


class InsertQuizCodeFragment : Fragment() {
    lateinit var insert_quiz_code: EditText
    lateinit var image_send_quiz: ImageView
    lateinit var questionQuizFragment: QuestionQuizFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_insert_quiz_code,container,false)
        questionQuizFragment = QuestionQuizFragment()
        insert_quiz_code = view.findViewById(R.id.insert_quiz_code)
        image_send_quiz = view.findViewById(R.id.image_send_quiz)

        setListeners()
        return view
    }

    private fun setListeners(){
        image_send_quiz.setOnClickListener {
            verify_code()
        }
    }

    private fun verify_code() {
        if(insert_quiz_code.text.toString() == "quiz123") {
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.id_frame_layout_fragment,questionQuizFragment)
                addToBackStack("question quiz fragment")
                commit()
            }
        }
        else {
            activity?.runOnUiThread {
                Toast.makeText(context, "Code is NOT valid", Toast.LENGTH_SHORT).show()
            }
        }
    }
}