package com.example.gamificationapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.gamificationapp.R
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.timerTask


class QuestionQuizFragment : Fragment(), ConfirmationBackFragment {
    lateinit var false_answer: TextView
    lateinit var correct_answer: TextView
    lateinit var correctAnswerQuizFragment: CorrectAnswerQuizFragment
    lateinit var wrongAnswerQuizFragment: WrongAnswerQuizFragment

    private lateinit var buttonConfirmationYes : Button
    private lateinit var buttonConfirmationNo : Button
    private lateinit var textViewConfirmation: TextView
    private lateinit var imageViewConfirmation: ImageView
    private lateinit var learningMethodsFragment: LearningMethodsFragment
    private lateinit var textViewClock : TextView
    private lateinit var imageViewClock : ImageView

    private var millis = 600000L
    private lateinit var timer: Timer

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_question_quiz,container,false)
        correctAnswerQuizFragment = CorrectAnswerQuizFragment()
        wrongAnswerQuizFragment = WrongAnswerQuizFragment()

        false_answer = view.findViewById(R.id.false_answer)
        correct_answer = view.findViewById(R.id.correct_answer)

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
        correct_answer.setOnClickListener {
            correctAnswerQuizFragment.millis = millis
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.id_frame_layout_fragment,correctAnswerQuizFragment)
                addToBackStack("correct answer quiz fragment")
                commit()
            }
        }
        false_answer.setOnClickListener {
            wrongAnswerQuizFragment.millis = millis
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.id_frame_layout_fragment,wrongAnswerQuizFragment)
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