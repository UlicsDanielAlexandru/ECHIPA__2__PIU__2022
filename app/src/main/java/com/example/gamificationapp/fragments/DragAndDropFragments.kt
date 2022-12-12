package com.example.gamificationapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.helper.widget.Flow
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gamificationapp.R


class DragAndDropFragment : Fragment() {

    private val answers : ArrayList<String> = arrayListOf("SCALABILITY", "SYSTEM", "TOLERANCE", "AVAILABILITY")
    private val question : String = "The main advantage of a distributed ?? is as follows : ??, fault, ?? and ??"
    private lateinit var textViewAnswer1 : TextView
    private lateinit var textViewAnswer2 : TextView
    private lateinit var textViewAnswer3 : TextView
    private lateinit var textViewAnswer4 : TextView
    private lateinit var flow: Flow

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_drag_and_drop, container, false)
        textViewAnswer1 = view.findViewById(R.id.id_text_view_answer_1)
        textViewAnswer1.text = answers[0]
        textViewAnswer2 = view.findViewById(R.id.id_text_view_answer_2)
        textViewAnswer2.text = answers[1]
        textViewAnswer3 = view.findViewById(R.id.id_text_view_answer_3)
        textViewAnswer3.text = answers[2]
        textViewAnswer4 = view.findViewById(R.id.id_text_view_answer_4)
        textViewAnswer4.text = answers[3]
        flow = view.findViewById(R.id.id_flow)
        val layout : ConstraintLayout = view.findViewById(R.id.id_constraint_layout_drag_and_drop)
        initializeQuestionsGap(layout, flow)
        return view
    }

    private fun initializeQuestionsGap(layout: ConstraintLayout, flow: Flow) {
        val questionParts = question.split("??");
        val itemsId = ArrayList<Int>()
        for (index in questionParts.indices) {
            if(index != (questionParts.size - 1)) {
                val textView = TextView(this.activity)
                textView.id = index + 1000000;
                itemsId.add(textView.id)
                textView.text = questionParts[index]
                textView.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                textView.setTextColor(getColor(requireActivity(), R.color.white))
                layout.addView(textView)
                val blankView = TextView(this.activity)
                blankView.id = index + 2000000
                itemsId.add(blankView.id)
                blankView.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                blankView.background = activity?.let { ResourcesCompat.getDrawable(it.resources, R.drawable.blank_shape, null) }
                layout.addView(blankView)
            }
            else {
                if(questionParts[index] != "") {
                    val textView = TextView(this.activity)
                    textView.id = index + 1000000;
                    itemsId.add(textView.id)
                    textView.text = questionParts[index]
                    textView.width = ViewGroup.LayoutParams.WRAP_CONTENT
                    textView.height = ViewGroup.LayoutParams.WRAP_CONTENT
                    layout.addView(textView)
                }
            }
        }
        flow.referencedIds = itemsId.toIntArray();
    }

}