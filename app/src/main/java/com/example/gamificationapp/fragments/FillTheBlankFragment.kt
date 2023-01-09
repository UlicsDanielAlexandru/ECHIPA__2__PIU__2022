package com.example.gamificationapp.fragments

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Im
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.helper.widget.Flow
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Group
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.gamificationapp.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList
import kotlin.concurrent.timerTask

class FillTheBlankFragment(var millis : Long = 0): Fragment(), ConfirmationBackFragment {

    private val answers : ArrayList<String> = arrayListOf("SCALABILITY", "SYSTEM", "TOLERANCE", "AVAILABILITY")
    private val question : String = "The main advantage of a distributed ?? is as follows : ??, fault, ?? and ??"
    private lateinit var textViewClock : TextView
    private lateinit var flow: Flow
    private lateinit var buttonConfirmationYes : Button
    private lateinit var buttonConfirmationNo : Button
    private lateinit var groupConfirmation : Group
    private lateinit var learningMethodsFragment : LearningMethodsFragment
    private lateinit var timer: Timer
    private val itemsId = ArrayList<Int>()
    private lateinit var editTextList : List<EditText>
    private lateinit var submitButton : Button
    private lateinit var imageViewXmark : ImageView
    private lateinit var imageViewCheck : ImageView
    private lateinit var imageViewClock : ImageView
    private lateinit var multipleChoiceFragment: MultipleChoiceFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_fill_the_blank, container, false)
        textViewClock = view.findViewById(R.id.id_text_view_clock)
        buttonConfirmationYes = view.findViewById(R.id.id_button_confirmation_yes)
        buttonConfirmationNo = view.findViewById(R.id.id_button_confirmation_no)
        groupConfirmation = view.findViewById(R.id.id_group_confirmation)
        learningMethodsFragment = LearningMethodsFragment()
        flow = view.findViewById(R.id.id_flow)
        val layout : ConstraintLayout = view.findViewById(R.id.id_constraint_layout_drag_and_drop)
        submitButton= view.findViewById(R.id.id_button_submit)
        imageViewXmark = view.findViewById(R.id.id_image_view_xmark)
        imageViewCheck = view.findViewById(R.id.id_image_view_check)
        imageViewClock = view.findViewById(R.id.id_image_view_clock)
        multipleChoiceFragment = MultipleChoiceFragment()
        initializeQuestionsGap(layout, flow)
        setListeners()
        initializeTimer()
        return view
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
                    (1..2).forEach{ _ -> activity?.supportFragmentManager?.popBackStack()}
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

    private fun initializeQuestionsGap(layout: ConstraintLayout, flow: Flow) {
        editTextList = ArrayList()
        val questionParts = question.split("??");
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
                blankView.isSaveEnabled = false
                blankView.id = index + 2000000
                itemsId.add(blankView.id)
                blankView.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                blankView.background = activity?.let { ResourcesCompat.getDrawable(it.resources, R.drawable.blank_shape, null) }
                layout.addView(blankView)
                (editTextList as ArrayList<EditText>).add(blankView)
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

    private fun setListeners() {
        buttonConfirmationNo.setOnClickListener {
            groupConfirmation.visibility = View.INVISIBLE
            flow.visibility = View.VISIBLE
        }
        buttonConfirmationYes.setOnClickListener {
            (1..2).forEach{ _ -> activity?.supportFragmentManager?.popBackStack()}
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.id_frame_layout_fragment, learningMethodsFragment)
                addToBackStack("learning methods fragment")
                commit()
            }
        }
        editTextList.forEach { x -> x.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(verifySubmitCondition())
                    submitButton.visibility = View.VISIBLE
                else
                    submitButton.visibility = View.INVISIBLE
            }
            override fun afterTextChanged(p0: Editable?) {}
        }) }
        submitButton.setOnClickListener {
            if(editTextList.map { x -> x.text.toString().uppercase() } == answers)
                imageViewCheck.visibility = View.VISIBLE
            else
                imageViewXmark.visibility = View.VISIBLE
            GlobalScope.launch {
                delay(600)
                timer.cancel()
                multipleChoiceFragment.millis = millis
                activity?.supportFragmentManager?.beginTransaction()?.apply {
                    replace(R.id.id_frame_layout_fragment, multipleChoiceFragment)
                    addToBackStack("multiple choice fragment")
                    commit()
                }
            }

        }
    }

    private fun verifySubmitCondition() : Boolean {
        return !editTextList.stream().anyMatch { x -> x.text.toString().isEmpty() }
    }

    override fun showConfirmationBack() {
        flow.visibility = View.INVISIBLE
        groupConfirmation.visibility = View.VISIBLE
    }

}