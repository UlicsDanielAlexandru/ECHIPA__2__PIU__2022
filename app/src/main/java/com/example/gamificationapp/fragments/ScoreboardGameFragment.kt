package com.example.gamificationapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.gamificationapp.R


class ScoreboardGameFragment(var finalScore: Int = 0) : Fragment() {

    private lateinit var textViewFinalScore : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_scoreboard_game, container, false)
        textViewFinalScore = view.findViewById(R.id.final_score)
        textViewFinalScore.text = finalScore.toString()
        return view
    }

}