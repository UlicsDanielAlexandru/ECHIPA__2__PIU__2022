package com.example.gamificationapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.gamificationapp.R


class LearningMethodsFragment : Fragment() {

    lateinit var method3_btn: TextView
    lateinit var insertQuizCodeFragment: InsertQuizCodeFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_learning_methods,container,false)
        insertQuizCodeFragment = InsertQuizCodeFragment()
        method3_btn = view.findViewById(R.id.method3_btn)
        setListeners()
        return view
    }

    private fun setListeners(){
        method3_btn.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.id_frame_layout_fragment,insertQuizCodeFragment)
                addToBackStack("insert quiz code")
                commit()
            }
        }
    }
}