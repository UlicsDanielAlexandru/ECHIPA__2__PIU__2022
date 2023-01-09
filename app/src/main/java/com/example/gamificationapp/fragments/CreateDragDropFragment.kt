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


class CreateDragDropFragment : Fragment(R.layout.fragment_create_drag_drop) {

    lateinit var questionText: EditText
    lateinit var next: Button
    lateinit var insert1: EditText
    lateinit var insert2: EditText
    lateinit var insert3: EditText
    lateinit var insert4: EditText

    var createFillTheGapFragment: CreateFillTheGapFragment = CreateFillTheGapFragment()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_create_drag_drop, container, false)
        questionText = view.findViewById(R.id.id_edit_text_create_drag_drop_text)
        insert1 = view.findViewById(R.id.id_edit_text_insert1)
        insert2 = view.findViewById(R.id.id_edit_text_insert2)
        insert3 = view.findViewById(R.id.id_edit_text_insert3)
        insert4 = view.findViewById(R.id.id_edit_text_insert4)
        next = view.findViewById(R.id.id_button_next_drag_drop)

        var text = questionText.text.toString()

        val regex1 = """#\$(insert1)\$#""".toRegex()
        val regex2 = """#\$(insert2)\$#""".toRegex()
        val regex3 = """#\$(insert3)\$#""".toRegex()
        val regex4 = """#\$(insert4)\$#""".toRegex()

        next.setOnClickListener {

            val builder = AlertDialog.Builder(context)
            builder.setTitle("Wrong input")
            builder.setMessage("One or more of the required #\$answerX\$# values missing from the input question")

            builder.setPositiveButton(android.R.string.yes) { _, _ ->
                Toast.makeText(
                    context,
                    "OK", Toast.LENGTH_SHORT
                ).show()
            }

            var text = questionText.text.toString()
            var ok = true
            if (!regex1.containsMatchIn(text)) {
                insert1.background = resources.getDrawable(R.drawable.rounded_button_red)
                ok = false
            } else {
                insert1.background = resources.getDrawable(R.drawable.rounded_button)
            }

            if (!regex2.containsMatchIn(text)) {
                insert2.background = resources.getDrawable(R.drawable.rounded_button_red)
                ok = false
            }
            {
                insert2.background = resources.getDrawable(R.drawable.rounded_button)
            }

            if (!regex3.containsMatchIn(text)) {
                insert3.background = resources.getDrawable(R.drawable.rounded_button_red)
                ok = false
            } else {
                insert3.background = resources.getDrawable(R.drawable.rounded_button)
            }

            if (!regex4.containsMatchIn(text)) {
                insert4.background = resources.getDrawable(R.drawable.rounded_button_red)
                ok = false;
            } else {
                insert4.background = resources.getDrawable(R.drawable.rounded_button)
            }
            if (ok) {
                activity?.supportFragmentManager?.beginTransaction()?.apply {
                    replace(R.id.id_frame_layout_fragment, createFillTheGapFragment)
                    addToBackStack("drop downs create fragment")
                    commit()
                }
            } else {
                builder.show()
            }
        }

        return view
    }
}