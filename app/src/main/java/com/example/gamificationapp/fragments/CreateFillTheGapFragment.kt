package com.example.gamificationapp.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.gamificationapp.R


class CreateFillTheGapFragment : Fragment(R.layout.fragment_create_fill_the_gap) {

    lateinit var textQuestion: EditText
    lateinit var next: Button


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_create_fill_the_gap, container, false)
        textQuestion = view.findViewById(R.id.id_text_view_create_fill_the_gap_text)
        next = view.findViewById(R.id.id_button_next_fill_the_gap)

        val regex = """#\$[a-zA-Z0-9]+\$#""".toRegex()


        return view;
    }
}