package com.example.gamificationapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.gamificationapp.R


class SubjectFragment : Fragment() {

    lateinit var textViewCourse2: TextView
    lateinit var learningMethodsFragment: LearningMethodsFragment
    lateinit var creatingMethodsFragment: CreatingMethodsFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_subject, container, false)
        learningMethodsFragment = LearningMethodsFragment()
        creatingMethodsFragment = CreatingMethodsFragment()
        textViewCourse2 = view.findViewById(R.id.course2_btn)
        setListeners()
        return view
    }

    private fun setListeners() {

        textViewCourse2.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                if (activity?.intent?.getBooleanExtra("professor", false) == false) {
                    replace(R.id.id_frame_layout_fragment, learningMethodsFragment)
                    addToBackStack("learning methods")
                } else {
                    replace(R.id.id_frame_layout_fragment, creatingMethodsFragment)
                    addToBackStack("make learning methods")
                }

                commit()
            }
        }
    }
}