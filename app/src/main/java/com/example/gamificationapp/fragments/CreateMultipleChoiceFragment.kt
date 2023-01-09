package com.example.gamificationapp.fragments

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.gamificationapp.R


class CreateMultipleChoiceFragment : Fragment(R.layout.fragment_create_multiple_choice) {


    lateinit var questionInput: EditText
    lateinit var correctButton: TextView
    lateinit var nextButton: TextView

    lateinit var input1: EditText
    lateinit var input2: EditText
    lateinit var input3: EditText
    lateinit var input4: EditText

    lateinit var two: TextView
    lateinit var four: TextView

    var createVideoAttentionScreenFragment: CreateVideoAttentionScreenFragment =
        CreateVideoAttentionScreenFragment()

    var noQuestions: Int = 4
    var correctBool: Boolean = false
    var correct: Int = 0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_create_multiple_choice, container, false)
        questionInput = view.findViewById(R.id.id_text_view_create_multiple_choice_text)
        correctButton = view.findViewById(R.id.id_button_correct_multiple_choice)
        nextButton = view.findViewById(R.id.id_button_next_multiple_choice)
        input1 = view.findViewById(R.id.id_edit_text_insert1)
        input2 = view.findViewById(R.id.id_edit_text_insert2)
        input3 = view.findViewById(R.id.id_edit_text_insert3)
        input4 = view.findViewById(R.id.id_edit_text_insert4)

        two = view.findViewById(R.id.id_text_view_2_answers)
        four = view.findViewById(R.id.id_text_view_4_answers)
        four.background = resources.getDrawable(R.drawable.rounded_button_blue)

        two.setOnClickListener {
            noQuestions = 2
            correct = 0
            input1.background = resources.getDrawable(R.drawable.rounded_button)
            input2.background = resources.getDrawable(R.drawable.rounded_button)
            input3.background = resources.getDrawable(R.drawable.rounded_button_grey)
            input4.background = resources.getDrawable(R.drawable.rounded_button_grey)
            four.background = resources.getDrawable(R.drawable.rounded_button_white)
            two.background = resources.getDrawable(R.drawable.rounded_button_blue)
            input3.isEnabled = false
            input4.isEnabled = false
        }

        four.setOnClickListener {
            noQuestions = 4
            correct = 0
            input1.background = resources.getDrawable(R.drawable.rounded_button)
            input2.background = resources.getDrawable(R.drawable.rounded_button)
            input3.background = resources.getDrawable(R.drawable.rounded_button)
            input4.background = resources.getDrawable(R.drawable.rounded_button)
            four.background = resources.getDrawable(R.drawable.rounded_button_blue)
            two.background = resources.getDrawable(R.drawable.rounded_button_white)
            input3.isEnabled = true
            input4.isEnabled = true
        }



        correctButton.setOnClickListener {
            correctBool = !correctBool
            if (correctBool) {
                correctButton.background = resources.getDrawable(R.drawable.rounded_button_green)
                input1.isFocusable = false
                input1.isFocusableInTouchMode = false
                input1.inputType = InputType.TYPE_NULL
                input2.isFocusable = false
                input2.isFocusableInTouchMode = false
                input2.inputType = InputType.TYPE_NULL
                input3.isFocusable = false
                input3.isFocusableInTouchMode = false
                input3.inputType = InputType.TYPE_NULL
                input4.isFocusable = false
                input4.isFocusableInTouchMode = false
                input4.inputType = InputType.TYPE_NULL
            } else {
                correctButton.background = resources.getDrawable(R.drawable.rounded_button_blue)
                input1.isFocusable = true
                input1.isFocusableInTouchMode = true
                input1.inputType = InputType.TYPE_CLASS_TEXT
                input2.isFocusable = true
                input2.isFocusableInTouchMode = true
                input2.inputType = InputType.TYPE_CLASS_TEXT
                input3.isFocusable = true
                input3.isFocusableInTouchMode = true
                input3.inputType = InputType.TYPE_CLASS_TEXT
                input4.isFocusable = true
                input4.isFocusableInTouchMode = true
                input4.inputType = InputType.TYPE_CLASS_TEXT
            }
        }

        input1.setOnClickListener {
            if (correctBool) {
                if (noQuestions == 4) {
                    correct = 1
                    input1.background = resources.getDrawable(R.drawable.rounded_button_green)
                    input2.background = resources.getDrawable(R.drawable.rounded_button)
                    input3.background = resources.getDrawable(R.drawable.rounded_button)
                    input4.background = resources.getDrawable(R.drawable.rounded_button)
                    correctBool = false
                    correctButton.background = resources.getDrawable(R.drawable.rounded_button_blue)
                    input1.isFocusable = true
                    input1.isFocusableInTouchMode = true
                    input1.inputType = InputType.TYPE_CLASS_TEXT
                    input2.isFocusable = true
                    input2.isFocusableInTouchMode = true
                    input2.inputType = InputType.TYPE_CLASS_TEXT
                    input3.isFocusable = true
                    input3.isFocusableInTouchMode = true
                    input3.inputType = InputType.TYPE_CLASS_TEXT
                    input4.isFocusable = true
                    input4.isFocusableInTouchMode = true
                    input4.inputType = InputType.TYPE_CLASS_TEXT
                } else {
                    correct = 1
                    input1.background = resources.getDrawable(R.drawable.rounded_button_green)
                    input2.background = resources.getDrawable(R.drawable.rounded_button)
                    input3.background = resources.getDrawable(R.drawable.rounded_button_grey)
                    input4.background = resources.getDrawable(R.drawable.rounded_button_grey)
                    correctBool = false
                    correctButton.background = resources.getDrawable(R.drawable.rounded_button_blue)
                    input1.isFocusable = true
                    input1.isFocusableInTouchMode = true
                    input1.inputType = InputType.TYPE_CLASS_TEXT
                    input2.isFocusable = true
                    input2.isFocusableInTouchMode = true
                    input2.inputType = InputType.TYPE_CLASS_TEXT
                    input3.isFocusable = false
                    input3.isFocusableInTouchMode = false
                    input3.inputType = InputType.TYPE_NULL
                    input4.isFocusable = false
                    input4.isFocusableInTouchMode = false
                    input4.inputType = InputType.TYPE_NULL
                }

            }
        }

        input2.setOnClickListener {
            if (correctBool) {
                if (noQuestions == 4) {
                    correct = 2
                    input2.background = resources.getDrawable(R.drawable.rounded_button_green)
                    input1.background = resources.getDrawable(R.drawable.rounded_button)
                    input3.background = resources.getDrawable(R.drawable.rounded_button)
                    input4.background = resources.getDrawable(R.drawable.rounded_button)
                    correctBool = false
                    correctButton.background = resources.getDrawable(R.drawable.rounded_button_blue)
                    input1.isFocusable = true
                    input1.isFocusableInTouchMode = true
                    input1.inputType = InputType.TYPE_CLASS_TEXT
                    input2.isFocusable = true
                    input2.isFocusableInTouchMode = true
                    input2.inputType = InputType.TYPE_CLASS_TEXT
                    input3.isFocusable = true
                    input3.isFocusableInTouchMode = true
                    input3.inputType = InputType.TYPE_CLASS_TEXT
                    input4.isFocusable = true
                    input4.isFocusableInTouchMode = true
                    input4.inputType = InputType.TYPE_CLASS_TEXT
                } else {
                    correct = 2
                    input2.background = resources.getDrawable(R.drawable.rounded_button_green)
                    input1.background = resources.getDrawable(R.drawable.rounded_button)
                    input3.background = resources.getDrawable(R.drawable.rounded_button_grey)
                    input4.background = resources.getDrawable(R.drawable.rounded_button_grey)
                    correctBool = false
                    correctButton.background = resources.getDrawable(R.drawable.rounded_button_blue)
                    input1.isFocusable = true
                    input1.isFocusableInTouchMode = true
                    input1.inputType = InputType.TYPE_CLASS_TEXT
                    input2.isFocusable = true
                    input2.isFocusableInTouchMode = true
                    input2.inputType = InputType.TYPE_CLASS_TEXT
                    input3.isFocusable = false
                    input3.isFocusableInTouchMode = false
                    input3.inputType = InputType.TYPE_NULL
                    input4.isFocusable = false
                    input4.isFocusableInTouchMode = false
                    input4.inputType = InputType.TYPE_NULL

                }

            }
        }

        input3.setOnClickListener {
            if (correctBool) {
                if (noQuestions == 4) {
                    correct = 3
                    input3.background = resources.getDrawable(R.drawable.rounded_button_green)
                    input1.background = resources.getDrawable(R.drawable.rounded_button)
                    input2.background = resources.getDrawable(R.drawable.rounded_button)
                    input4.background = resources.getDrawable(R.drawable.rounded_button)
                    correctBool = false
                    correctButton.background = resources.getDrawable(R.drawable.rounded_button_blue)
                    input1.isFocusable = true
                    input1.isFocusableInTouchMode = true
                    input1.inputType = InputType.TYPE_CLASS_TEXT
                    input2.isFocusable = true
                    input2.isFocusableInTouchMode = true
                    input2.inputType = InputType.TYPE_CLASS_TEXT
                    input3.isFocusable = true
                    input3.isFocusableInTouchMode = true
                    input3.inputType = InputType.TYPE_CLASS_TEXT
                    input4.isFocusable = true
                    input4.isFocusableInTouchMode = true
                    input4.inputType = InputType.TYPE_CLASS_TEXT
                }

            }
        }

        input4.setOnClickListener {
            if (correctBool) {
                if (noQuestions == 4) {
                    correct = 4
                    input1.background = resources.getDrawable(R.drawable.rounded_button)
                    input2.background = resources.getDrawable(R.drawable.rounded_button)
                    input3.background = resources.getDrawable(R.drawable.rounded_button)
                    input4.background = resources.getDrawable(R.drawable.rounded_button_green)
                    correctBool = false
                    correctButton.background = resources.getDrawable(R.drawable.rounded_button_blue)
                    input1.isFocusable = true
                    input1.isFocusableInTouchMode = true
                    input1.inputType = InputType.TYPE_CLASS_TEXT
                    input2.isFocusable = true
                    input2.isFocusableInTouchMode = true
                    input2.inputType = InputType.TYPE_CLASS_TEXT
                    input3.isFocusable = true
                    input3.isFocusableInTouchMode = true
                    input3.inputType = InputType.TYPE_CLASS_TEXT
                    input4.isFocusable = true
                    input4.isFocusableInTouchMode = true
                    input4.inputType = InputType.TYPE_CLASS_TEXT
                }
            }
        }



        nextButton.setOnClickListener {
            if (correct > 0 && !correctBool) {

                if (activity?.intent?.getBooleanExtra("quiz", false) == false) {
                    activity?.supportFragmentManager?.beginTransaction()?.apply {
                        replace(R.id.id_frame_layout_fragment, createVideoAttentionScreenFragment)
                        addToBackStack("video attention screen create fragment")
                        commit()
                    }

                } else {
                    val createQuizFinishScreen = CreateQuizFinishScreen()
                    activity?.supportFragmentManager?.beginTransaction()?.apply {
                        replace(R.id.id_frame_layout_fragment, createQuizFinishScreen)
                        addToBackStack("quiz finish screen create fragment")
                        commit()
                    }
                }
            }
        }

        return view
    }
}