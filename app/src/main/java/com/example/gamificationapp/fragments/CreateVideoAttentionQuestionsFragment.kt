package com.example.gamificationapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.gamificationapp.R

class CreateVideoAttentionQuestionsFragment : Fragment(R.layout.fragment_create_video_attention_questions ) {

    lateinit var true1: TextView
    lateinit var true2: TextView
    lateinit var true3: TextView

    lateinit var false1: TextView
    lateinit var false2: TextView
    lateinit var false3: TextView

    lateinit var finish: TextView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_create_video_attention_questions, container, false)

        true1 = view.findViewById(R.id.id_text_view_true1)
        true2 = view.findViewById(R.id.id_text_view_true2)
        true3 = view.findViewById(R.id.id_text_view_true3)

        false1 = view.findViewById(R.id.id_text_view_false1)
        false2 = view.findViewById(R.id.id_text_view_false2)
        false3 = view.findViewById(R.id.id_text_view_false3)

        finish = view.findViewById(R.id.id_text_view_finish_video_attention)

        true1.setOnClickListener {
            true1.background = resources.getDrawable(R.drawable.rounded_button_green, null)
            false1.background = resources.getDrawable(R.drawable.rounded_button_grey, null)
        }

        true2.setOnClickListener {
            true2.background = resources.getDrawable(R.drawable.rounded_button_green, null)
            false2.background = resources.getDrawable(R.drawable.rounded_button_grey, null)
        }

        true3.setOnClickListener {
            true3.background = resources.getDrawable(R.drawable.rounded_button_green, null)
            false3.background = resources.getDrawable(R.drawable.rounded_button_grey, null)
        }

        finish.setOnClickListener{
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                val creatingMethodsFragment = CreatingMethodsFragment()
                replace(R.id.id_frame_layout_fragment, creatingMethodsFragment)
                addToBackStack("methods create fragment")
                commit()
            }
        }

        return view
    }
}