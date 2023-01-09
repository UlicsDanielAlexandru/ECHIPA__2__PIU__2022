package com.example.gamificationapp.fragments


import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.gamificationapp.R


class CreateFillTheGapFragment : Fragment(R.layout.fragment_create_fill_the_gap) {

    lateinit var textQuestion: EditText
    lateinit var next: Button
    var createMultipleChoiceFragment: CreateMultipleChoiceFragment = CreateMultipleChoiceFragment()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_create_fill_the_gap, container, false)
        textQuestion = view.findViewById(R.id.id_text_view_create_fill_the_gap_text)
        next = view.findViewById(R.id.id_button_next_fill_the_gap)

        next.setOnClickListener{
            val regex = """#\$[a-zA-Z0-9]+\$#""".toRegex()

            var text = textQuestion.text.toString()
            val results = regex.findAll(text)
            var ok = true

            try {
                results.elementAt(3)
            } catch (e: java.lang.IndexOutOfBoundsException) {
                ok = false
            }

            val builder = AlertDialog.Builder(context)
            builder.setTitle("Wrong input")
            builder.setMessage("One or more of the required #\$answer\$# values missing from the input question")

            builder.setPositiveButton(android.R.string.yes) { _, _ ->
                Toast.makeText(context,
                    "OK", Toast.LENGTH_SHORT).show()
            }

            if(ok)
            {
                activity?.supportFragmentManager?.beginTransaction()?.apply {
                    replace(R.id.id_frame_layout_fragment, createMultipleChoiceFragment)
                    addToBackStack("multiple choice create fragment")
                    commit()
                }
            }else
            {
                builder.show()
            }
        }



        return view;
    }
}