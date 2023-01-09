package com.example.gamificationapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.helper.widget.Flow
import androidx.constraintlayout.widget.Group
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.gamificationapp.R
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.timerTask


class Question1QuizFragment(var millis : Long = 0) : Fragment(), ConfirmationBackFragment {
    lateinit var first_answer: TextView
    lateinit var second_answer: TextView
    lateinit var third_answer: TextView
    lateinit var forth_answer: TextView
    lateinit var x_2: ImageView
    lateinit var x_3: ImageView
    lateinit var x_4: ImageView
    var x: String = "0"
    private lateinit var buttonConfirmationYes : Button
    private lateinit var buttonConfirmationNo : Button
    private lateinit var textViewConfirmation: TextView
    private lateinit var imageViewConfirmation: ImageView
    private lateinit var learningMethodsFragment: LearningMethodsFragment
    private lateinit var textViewClock : TextView
    private lateinit var imageViewClock : ImageView

    private lateinit var timer: Timer

    lateinit var wrongAnswerQuiz1Fragment: WrongAnswerQuiz1Fragment
    lateinit var correctAnswerQuiz1Fragment: CorrectAnswerQuiz1Fragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_question_1_quiz,container,false)
        val view1 = inflater.inflate(R.layout.fragment_wrong_answer_quiz_1,container,false)

        wrongAnswerQuiz1Fragment = WrongAnswerQuiz1Fragment(x)
        correctAnswerQuiz1Fragment = CorrectAnswerQuiz1Fragment()
        first_answer = view.findViewById(R.id.first_answer)
        second_answer = view.findViewById(R.id.second_answer)
        third_answer = view.findViewById(R.id.third_answer)
        forth_answer = view.findViewById(R.id.forth_answer)
        x_2 = view1.findViewById(R.id.x_2)
        x_3 = view1.findViewById(R.id.x_3)
        x_4 = view1.findViewById(R.id.x_4)

        learningMethodsFragment = LearningMethodsFragment()
        buttonConfirmationYes = view.findViewById(R.id.id_button_confirmation_yes)
        buttonConfirmationNo = view.findViewById(R.id.id_button_confirmation_no)
        textViewConfirmation = view.findViewById(R.id.id_text_view_confirmation)
        imageViewConfirmation = view.findViewById(R.id.id_image_view_confirmation)

        textViewClock = view.findViewById(R.id.id_text_view_clock)
        imageViewClock = view.findViewById(R.id.id_image_view_clock)

        initializeTimer()

        setListeners()
        return view
    }

    private fun setListeners(){
        first_answer.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.id_frame_layout_fragment,correctAnswerQuiz1Fragment)
                addToBackStack("correct answer quiz fragment")
                commit()
            }
        }
        second_answer.setOnClickListener {
            wrongAnswerQuiz1Fragment.x="2"
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.id_frame_layout_fragment,wrongAnswerQuiz1Fragment)
                addToBackStack("wrong answer quiz fragment")
                commit()
            }
        }
        third_answer.setOnClickListener {
            wrongAnswerQuiz1Fragment.x="3"
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.id_frame_layout_fragment,wrongAnswerQuiz1Fragment)
                addToBackStack("wrong answer quiz fragment")
                commit()
            }
        }
        forth_answer.setOnClickListener {
            wrongAnswerQuiz1Fragment.x="4"
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.id_frame_layout_fragment,wrongAnswerQuiz1Fragment)
                addToBackStack("wrong answer quiz fragment")
                commit()
            }
        }

        buttonConfirmationNo.setOnClickListener {
            textViewConfirmation.visibility = View.INVISIBLE
            imageViewConfirmation.visibility = View.INVISIBLE
            buttonConfirmationNo.visibility = View.INVISIBLE
            buttonConfirmationYes.visibility = View.INVISIBLE
        }
        buttonConfirmationYes.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.id_frame_layout_fragment, learningMethodsFragment)
                addToBackStack("learning methods fragment")
                commit()
            }
        }
    }

    private fun initializeTimer() {
        timer = Timer()
        timer.scheduleAtFixedRate(timerTask {
            activity?.runOnUiThread {
                millis -= 1000
                textViewClock.text = String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millis),
                    TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1))
                if(millis in 300001..450000) {
                    textViewClock.setTextColor(context?.let { AppCompatResources.getColorStateList(it, R.color.yellow) })
                    imageViewClock.backgroundTintList = context?.let { ContextCompat.getColorStateList(it, R.color.yellow) }
                }
                if(millis in 150001..300000) {
                    textViewClock.setTextColor(context?.let { AppCompatResources.getColorStateList(it, R.color.clock_orange) })
                    imageViewClock.backgroundTintList = context?.let { ContextCompat.getColorStateList(it, R.color.clock_orange) }
                }
                if(millis < 150000) {
                    textViewClock.setTextColor(context?.let { AppCompatResources.getColorStateList(it, R.color.red) })
                    imageViewClock.backgroundTintList = context?.let { ContextCompat.getColorStateList(it, R.color.red) }
                }
                if(millis == 0L) {
                    activity?.supportFragmentManager?.popBackStack()
                    activity?.supportFragmentManager?.beginTransaction()?.apply {
                        replace(R.id.id_frame_layout_fragment, learningMethodsFragment)
                        addToBackStack("learning methods fragment")
                        commit()
                    }
                    this.cancel()
                }
            }
        }, 0, 1000)
    }

    override fun showConfirmationBack() {
        textViewConfirmation.visibility = View.VISIBLE
        imageViewConfirmation.visibility = View.VISIBLE
        buttonConfirmationNo.visibility = View.VISIBLE
        buttonConfirmationYes.visibility = View.VISIBLE
    }
}