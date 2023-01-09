package com.example.gamificationapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.Group
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.gamificationapp.R
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.timerTask

class VideoQuestionsFragment(var millis : Long = 0): Fragment(), ConfirmationBackFragment {

    private lateinit var textViewQuestion1 : TextView
    private lateinit var textViewQuestion2 : TextView
    private lateinit var textViewQuestion3 : TextView
    private lateinit var buttonConfirmationYes : Button
    private lateinit var buttonConfirmationNo : Button
    private lateinit var groupConfirmation : Group
    private lateinit var groupQuestion : Group
    private lateinit var textViewClock : TextView
    private lateinit var timer: Timer
    private lateinit var buttonSubmit : Button
    private lateinit var buttonViewResults : Button
    private lateinit var imageViewClock : ImageView
    private val questions = listOf(Pair("Is RMI a java specific technology?", false), Pair("Does the client side invoke methods from the server?", true)
    , Pair("Is RMI used in distributed systems?", true))
    private val answers = mutableListOf<Boolean?>(null, null, null)
    private val buttons = ArrayList<Pair<Button, Button>>()
    private val imageViewsXmark = ArrayList<Pair<ImageView, ImageView>>()
    private lateinit var learningMethodsFragment : LearningMethodsFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_video_questions, container, false)
        learningMethodsFragment = LearningMethodsFragment()
        initializeViews(view)
        initializeTimer()
        setListeners()
        return view
    }

    private fun initializeViews(view : View) {
        textViewQuestion1 = view.findViewById(R.id.id_text_view_question_1)
        textViewQuestion2 = view.findViewById(R.id.id_text_view_question_2)
        textViewQuestion3 = view.findViewById(R.id.id_text_view_question_3)
        textViewQuestion1.text = questions[0].first
        textViewQuestion2.text = questions[1].first
        textViewQuestion3.text = questions[2].first
        textViewClock = view.findViewById(R.id.id_text_view_clock)
        buttonConfirmationYes = view.findViewById(R.id.id_button_confirmation_yes)
        buttonConfirmationNo = view.findViewById(R.id.id_button_confirmation_no)
        groupConfirmation = view.findViewById(R.id.id_group_confirmation)
        groupQuestion = view.findViewById(R.id.id_group_questions)
        imageViewClock = view.findViewById(R.id.id_image_view_clock)
        (1..3).forEach { x -> run {
            val idButtonTrue = resources.getIdentifier("id_button_true_" + x, "id", activity?.packageName)
            val idButtonFalse = resources.getIdentifier("id_button_false_" + x, "id", activity?.packageName)
            buttons.add(Pair(view.findViewById(idButtonTrue), view.findViewById(idButtonFalse)))
        } }
        (1..3).forEach { x -> run {
            val idImageViewTrue = resources.getIdentifier("id_image_view_xmark_true_" + x, "id", activity?.packageName)
            val idImageViewFalse = resources.getIdentifier("id_image_view_xmark_false_" + x, "id", activity?.packageName)
            imageViewsXmark.add(Pair(view.findViewById(idImageViewTrue), view.findViewById(idImageViewFalse)))
        } }
        buttonSubmit = view.findViewById(R.id.id_button_submit)
        buttonViewResults = view.findViewById(R.id.id_button_view_results)
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
                    (1..5).forEach{ _ -> activity?.supportFragmentManager?.popBackStack()}
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

    private fun setListeners() {
        buttonConfirmationNo.setOnClickListener {
            groupQuestion.visibility = View.VISIBLE
            groupConfirmation.visibility = View.INVISIBLE
        }
        buttonConfirmationYes.setOnClickListener {
            (1..5).forEach{ _ -> activity?.supportFragmentManager?.popBackStack()}
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.id_frame_layout_fragment, learningMethodsFragment)
                addToBackStack("learning methods fragment")
                commit()
            }
        }
        buttons.forEachIndexed { index, (x, y) ->
            run {
                x.setOnClickListener {
                    answers[index] = true
                    x.backgroundTintList = context?.let { ContextCompat.getColorStateList(it, R.color.green) }
                    y.backgroundTintList = context?.let { ContextCompat.getColorStateList(it, R.color.grey) }
                    if(!answers.any { x-> x == null })
                        buttonSubmit.visibility = View.VISIBLE
                }
                y.setOnClickListener {
                    answers[index] = false
                    x.backgroundTintList = context?.let { ContextCompat.getColorStateList(it, R.color.grey) }
                    y.backgroundTintList = context?.let { ContextCompat.getColorStateList(it, R.color.red) }
                    if(!answers.any { x-> x == null })
                        buttonSubmit.visibility = View.VISIBLE
                }
            }
        }
        buttonSubmit.setOnClickListener {
            buttonSubmit.visibility = View.INVISIBLE
            questions.map { x -> x.second }.forEachIndexed { index, y ->
                run {
                    if(y != answers[index]) {
                        if(answers[index] == true) {
                            buttons[index].first.backgroundTintList = context?.let { ContextCompat.getColorStateList(it, R.color.grey)}
                            imageViewsXmark[index].first.visibility = View.VISIBLE
                        }
                        else {
                            buttons[index].second.backgroundTintList = context?.let { ContextCompat.getColorStateList(it, R.color.grey)}
                            imageViewsXmark[index].second.visibility = View.VISIBLE
                        }
                    }
                }
            }
            buttonViewResults.visibility = View.VISIBLE
        }
        buttonViewResults.setOnClickListener {
            timer.cancel()
            (1..5).forEach{ _ -> activity?.supportFragmentManager?.popBackStack()}
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.id_frame_layout_fragment, learningMethodsFragment)
                addToBackStack("learning methods fragment")
                commit()
            }
        }
    }

    override fun showConfirmationBack() {
        groupQuestion.visibility = View.INVISIBLE
        groupConfirmation.visibility = View.VISIBLE
    }
}