package com.example.gamificationapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.gamificationapp.R


class CreatingMethodsFragment : Fragment(R.layout.fragment_creating_methods) {
    lateinit var createGame: TextView
    lateinit var teach: TextView
    lateinit var createQuiz: TextView

    private var addGameLevelFragment: AddGameLevelFragment = AddGameLevelFragment()
    private var createQuizDropDown: CreateQuizDropDown = CreateQuizDropDown()
    private var makeStatusAvailableFragment: MakeStatusAvailableFragment = MakeStatusAvailableFragment()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_creating_methods, container, false)
        createGame = view.findViewById(R.id.id_text_view_create_game)
        createQuiz = view.findViewById(R.id.id_text_view_create_quiz)
        teach = view.findViewById(R.id.id_text_view_teach)
        setListeners()
        return view
    }

    private fun setListeners() {
        createGame.setOnClickListener{
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.id_frame_layout_fragment, addGameLevelFragment)
                addToBackStack("add game level fragment")
                commit()
            }
        }

        createQuiz.setOnClickListener{
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.id_frame_layout_fragment, createQuizDropDown)
                addToBackStack("add quiz fragment")
                commit()
            }
        }

        teach.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.id_frame_layout_fragment, makeStatusAvailableFragment)
                addToBackStack("make status available fragment")
                commit()
            }
        }
    }


}