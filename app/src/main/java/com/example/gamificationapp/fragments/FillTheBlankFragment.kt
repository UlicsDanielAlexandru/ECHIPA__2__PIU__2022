package com.example.gamificationapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.helper.widget.Flow
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.gamificationapp.R

class FillTheBlankFragment: Fragment() {

    private val answers : ArrayList<String> = arrayListOf("SCALABILITY", "SYSTEM", "TOLERANCE", "AVAILABILITY")
    private val question : String = "The main advantage of a distributed ?? is as follows : ??, fault, ?? and ??"
    private lateinit var flow: Flow

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_fill_the_blank, container, false)

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
                textView.setTextColor(ContextCompat.getColor(requireActivity(), R.color.white))
                layout.addView(textView)
                val blankView = EditText(this.activity)
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