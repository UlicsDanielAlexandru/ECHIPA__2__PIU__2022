package com.example.gamificationapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.gamificationapp.R


class LearningMethodsFragment : Fragment() {

    lateinit var method1_btn: TextView
    lateinit var method2_btn: TextView
    lateinit var method3_btn: TextView
    lateinit var insertQuizCodeFragment: InsertQuizCodeFragment
    lateinit var levelsFragment: LevelsFragment
    lateinit var collaborativeMethodSelectionFragment: CollaborativeMethodSelectionFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_learning_methods,container,false)
        insertQuizCodeFragment = InsertQuizCodeFragment()
        levelsFragment = LevelsFragment()
        collaborativeMethodSelectionFragment = CollaborativeMethodSelectionFragment()
        method1_btn = view.findViewById(R.id.method1_btn)
        method2_btn = view.findViewById(R.id.method2_btn)
        method3_btn = view.findViewById(R.id.method3_btn)
        setListeners()
        return view
    }

    private fun setListeners(){
        method1_btn.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.id_frame_layout_fragment, collaborativeMethodSelectionFragment)
                addToBackStack("collaborative method selection fragment")
                commit()
            }
        }
        method2_btn.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.id_frame_layout_fragment, levelsFragment)
                addToBackStack("levels fragment")
                commit()
            }
        }
        method3_btn.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.id_frame_layout_fragment,insertQuizCodeFragment)
                addToBackStack("insert quiz code")
                commit()
            }
        }
    }
}