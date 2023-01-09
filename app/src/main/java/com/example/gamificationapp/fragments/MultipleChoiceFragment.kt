package com.example.gamificationapp.fragments

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Im
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Group
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.gamificationapp.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList
import kotlin.concurrent.timerTask
import kotlin.properties.Delegates

class MultipleChoiceFragment(var millis : Long = 0): Fragment(), ConfirmationBackFragment {

    private val answers : ArrayList<String> = arrayListOf("Remote mail invocation", "Remote message invocation",
        "Remaining method invocation", "Remote method invocation")
    private val question : String = "What does RMI stand for?"
    private val correctAnswerIndex = 3
    private var selectedAnswerIndex : Int = -1
    private lateinit var textViewQuestion : TextView
    private lateinit var textViewAnswer1 : TextView
    private lateinit var textViewAnswer2 : TextView
    private lateinit var textViewAnswer3 : TextView
    private lateinit var textViewAnswer4 : TextView
    private lateinit var imageViewAnswer1 : ImageView
    private lateinit var imageViewAnswer2 : ImageView
    private lateinit var imageViewAnswer3 : ImageView
    private lateinit var imageViewAnswer4 : ImageView
    private lateinit var textViewClock : TextView
    private lateinit var submitButton: Button
    private lateinit var buttonConfirmationYes : Button
    private lateinit var buttonConfirmationNo : Button
    private lateinit var groupConfirmation : Group
    private lateinit var imageViewXmark : ImageView
    private lateinit var imageViewCheck : ImageView
    private lateinit var imageViewClock : ImageView
    private lateinit var timer: Timer
    private lateinit var learningMethodsFragment : LearningMethodsFragment
    private lateinit var videoFragment: VideoFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_multiple_choice, container, false)
        textViewQuestion = view.findViewById(R.id.id_text_view_question)
        textViewQuestion.text = question
        textViewAnswer1 = view.findViewById(R.id.id_text_view_answer_1)
        textViewAnswer1.text = answers[0]
        textViewAnswer2 = view.findViewById(R.id.id_text_view_answer_2)
        textViewAnswer2.text = answers[1]
        textViewAnswer3 = view.findViewById(R.id.id_text_view_answer_3)
        textViewAnswer3.text = answers[2]
        textViewAnswer4 = view.findViewById(R.id.id_text_view_answer_4)
        textViewAnswer4.text = answers[3]
        textViewClock = view.findViewById(R.id.id_text_view_clock)
        imageViewAnswer1 = view.findViewById(R.id.id_image_view_answer_1)
        imageViewAnswer2 = view.findViewById(R.id.id_image_view_answer_2)
        imageViewAnswer3 = view.findViewById(R.id.id_image_view_answer_3)
        imageViewAnswer4 = view.findViewById(R.id.id_image_view_answer_4)
        submitButton = view.findViewById(R.id.id_button_submit)
        buttonConfirmationYes = view.findViewById(R.id.id_button_confirmation_yes)
        buttonConfirmationNo = view.findViewById(R.id.id_button_confirmation_no)
        groupConfirmation = view.findViewById(R.id.id_group_confirmation)
        imageViewXmark = view.findViewById(R.id.id_image_view_xmark)
        imageViewCheck = view.findViewById(R.id.id_image_view_check)
        imageViewClock = view.findViewById(R.id.id_image_view_clock)
        learningMethodsFragment = LearningMethodsFragment()
        videoFragment = VideoFragment()
        setListeners()
        initializeTimer()
        return view
    }

    private fun setListeners() {
        buttonConfirmationNo.setOnClickListener {
            groupConfirmation.visibility = View.INVISIBLE
        }
        buttonConfirmationYes.setOnClickListener {
            (1..3).forEach{ _ -> activity?.supportFragmentManager?.popBackStack()}
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.id_frame_layout_fragment, learningMethodsFragment)
                addToBackStack("learning methods fragment")
                commit()
            }
        }
        imageViewAnswer1.setOnClickListener {
            selectedAnswerIndex = 0
            submitButton.visibility = View.VISIBLE
            resetColor()
            imageViewAnswer1.backgroundTintList = context?.let { ContextCompat.getColorStateList(it, R.color.quiz_answer_blue) }
        }
        imageViewAnswer2.setOnClickListener {
            selectedAnswerIndex = 1
            submitButton.visibility = View.VISIBLE
            resetColor()
            imageViewAnswer2.backgroundTintList = context?.let { ContextCompat.getColorStateList(it, R.color.quiz_answer_blue) }
        }
        imageViewAnswer3.setOnClickListener {
            selectedAnswerIndex = 2
            submitButton.visibility = View.VISIBLE
            resetColor()
            imageViewAnswer3.backgroundTintList = context?.let { ContextCompat.getColorStateList(it, R.color.quiz_answer_blue) }
        }
        imageViewAnswer4.setOnClickListener {
            selectedAnswerIndex = 3
            submitButton.visibility = View.VISIBLE
            resetColor()
            imageViewAnswer4.backgroundTintList = context?.let { ContextCompat.getColorStateList(it, R.color.quiz_answer_blue) }
        }
        submitButton.setOnClickListener {
            submitButton.visibility = View.INVISIBLE
            if (selectedAnswerIndex == correctAnswerIndex)
                imageViewCheck.visibility = View.VISIBLE
            else
                imageViewXmark.visibility = View.VISIBLE
            GlobalScope.launch {
                delay(600)
                timer.cancel()
                videoFragment.millis = millis
                activity?.supportFragmentManager?.beginTransaction()?.apply {
                    replace(R.id.id_frame_layout_fragment, videoFragment)
                    addToBackStack("multiple choice fragment")
                    commit()
                }
            }
        }
    }

    private fun resetColor() {
        imageViewAnswer1.backgroundTintList = context?.let { ContextCompat.getColorStateList(it, R.color.yellow) }
        imageViewAnswer2.backgroundTintList = context?.let { ContextCompat.getColorStateList(it, R.color.yellow) }
        imageViewAnswer3.backgroundTintList = context?.let { ContextCompat.getColorStateList(it, R.color.yellow) }
        imageViewAnswer4.backgroundTintList = context?.let { ContextCompat.getColorStateList(it, R.color.yellow) }
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
                    (1..3).forEach{ _ -> activity?.supportFragmentManager?.popBackStack()}
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
        groupConfirmation.visibility = View.VISIBLE
    }
}