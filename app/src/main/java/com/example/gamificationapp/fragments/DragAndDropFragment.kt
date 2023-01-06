package com.example.gamificationapp.fragments

import android.content.ClipData
import android.content.ClipDescription
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Im
import android.util.Log
import android.view.DragEvent.*
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.View.DragShadowBuilder
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.helper.widget.Flow
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Group
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.gamificationapp.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.timerTask


class DragAndDropFragment : Fragment(), ConfirmationBackFragment {

    private val answers : ArrayList<String> = arrayListOf("SCALABILITY", "SYSTEM", "TOLERANCE", "AVAILABILITY")
    private val question : String = "The main advantage of a distributed ?? is as follows : ??, fault, ?? and ??"
    private lateinit var textViewAnswer1 : TextView
    private lateinit var textViewAnswer2 : TextView
    private lateinit var textViewAnswer3 : TextView
    private lateinit var textViewAnswer4 : TextView
    private lateinit var textViewClock : TextView
    private lateinit var flow: Flow
    private var millis = 600000L
    private lateinit var timer: Timer
    private lateinit var fillTheBlankFragment : FillTheBlankFragment
    private lateinit var learningMethodsFragment: LearningMethodsFragment
    private lateinit var buttonConfirmationYes : Button
    private lateinit var buttonConfirmationNo : Button
    private lateinit var groupConfirmation : Group
    private lateinit var buttonSubmit : Button
    private lateinit var imageViewXmark : ImageView
    private lateinit var imageViewCheck : ImageView
    private lateinit var imageViewClock : ImageView
    private lateinit var textViewList : List<TextView>
    private val textViewSetted = mutableListOf(false, false, false, false)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_drag_and_drop, container, false)
        textViewAnswer1 = view.findViewById(R.id.id_text_view_answer_1)
        textViewAnswer1.text = answers[0]
        textViewAnswer2 = view.findViewById(R.id.id_text_view_answer_2)
        textViewAnswer2.text = answers[1]
        textViewAnswer3 = view.findViewById(R.id.id_text_view_answer_3)
        textViewAnswer3.text = answers[2]
        textViewAnswer4 = view.findViewById(R.id.id_text_view_answer_4)
        textViewAnswer4.text = answers[3]
        textViewClock = view.findViewById(R.id.id_text_view_clock)
        flow = view.findViewById(R.id.id_flow)
        buttonConfirmationYes = view.findViewById(R.id.id_button_confirmation_yes)
        buttonConfirmationNo = view.findViewById(R.id.id_button_confirmation_no)
        groupConfirmation = view.findViewById(R.id.id_group_confirmation)
        buttonSubmit = view.findViewById(R.id.id_button_submit)
        imageViewXmark = view.findViewById(R.id.id_image_view_xmark)
        imageViewCheck = view.findViewById(R.id.id_image_view_check)
        imageViewClock = view.findViewById(R.id.id_image_view_clock)
        fillTheBlankFragment = FillTheBlankFragment()
        learningMethodsFragment = LearningMethodsFragment()
        val layout : ConstraintLayout = view.findViewById(R.id.id_constraint_layout_drag_and_drop)
        initializeQuestionsGap(layout, flow)
        setListeners()
        initializeTimer()
        return view
    }

    private fun setListeners() {
        buttonConfirmationNo.setOnClickListener {
            groupConfirmation.visibility = View.INVISIBLE
            flow.visibility = View.VISIBLE
        }
        buttonConfirmationYes.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.id_frame_layout_fragment, learningMethodsFragment)
                addToBackStack("learning methods fragment")
                commit()
            }
        }
        textViewAnswer1.setOnLongClickListener {
            v -> run {
            val dragShadow = DragShadowBuilder(v)
            v.startDragAndDrop(null,
                dragShadow,
                v,
                0
            )
            return@run true
        }
        }
        textViewAnswer2.setOnLongClickListener {
                v -> run {
            val dragShadow = DragShadowBuilder(v)
            v.startDragAndDrop(null,
                dragShadow,
                v,
                0
            )
            return@run true
        }
        }
        textViewAnswer3.setOnLongClickListener {
                v -> run {
            val dragShadow = DragShadowBuilder(v)
            v.startDragAndDrop(null,
                dragShadow,
                v,
                0
            )
            return@run true
        }
        }
        textViewAnswer4.setOnLongClickListener {
                v -> run {
            val dragShadow = DragShadowBuilder(v)
            v.startDragAndDrop(null,
                dragShadow,
                v,
                0
            )
            return@run true
        }
        }

        textViewList.forEachIndexed {
            index, x -> x.setOnDragListener { view, event ->
            run {
                when (event.action) {
                    ACTION_DRAG_STARTED -> return@run true
                    ACTION_DRAG_ENTERED -> return@run true
                    ACTION_DRAG_LOCATION -> return@run true
                    ACTION_DRAG_EXITED -> {
                        return@run true
                    }
                    ACTION_DROP -> {
                        val draggedView = event.localState as TextView
                        val textView = (view as TextView)
                        textView.text = draggedView.text
                        textView.backgroundTintList = context?.let { ContextCompat.getColorStateList(it, R.color.yellow) }
                        draggedView.visibility = View.INVISIBLE
                        textViewSetted[index] = true
                        if(verifyTextViewSetted())
                            buttonSubmit.visibility = View.VISIBLE
                        return@setOnDragListener true
                    }
                    ACTION_DRAG_ENDED -> {
                        return@run false
                    }
                    else -> {
                        Log.e("DragDrop Example", "Unknown action type received by View.OnDragListener.")
                        return@setOnDragListener false
                    }
                }
            }
        }
        }
        buttonSubmit.setOnClickListener {
            if(textViewList[0].text.toString() == "SYSTEM") {
                buttonSubmit.visibility = View.INVISIBLE
                imageViewCheck.visibility = View.VISIBLE
            }
            else {
                buttonSubmit.visibility = View.INVISIBLE
                imageViewXmark.visibility = View.VISIBLE
            }
            GlobalScope.launch {
                delay(600)
                timer.cancel()
                fillTheBlankFragment.millis = millis
                activity?.supportFragmentManager?.beginTransaction()?.apply {
                    replace(R.id.id_frame_layout_fragment, fillTheBlankFragment)
                    addToBackStack("fill the blank fragment")
                    commit()
                }
            }

        }
    }

    private fun verifyTextViewSetted() : Boolean {
        return !textViewSetted.any { x -> !x }
    }

    private fun initializeQuestionsGap(layout: ConstraintLayout, flow: Flow) {
        textViewList = ArrayList()
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
                blankView.gravity = Gravity.CENTER
                blankView.setTextColor(context?.let { AppCompatResources.getColorStateList(it, R.color.black) })
                blankView.background = activity?.let { ResourcesCompat.getDrawable(it.resources, R.drawable.blank_shape, null) }
                layout.addView(blankView)
                (textViewList as ArrayList<TextView>).add(blankView)
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
                    textViewClock.backgroundTintList = context?.let { ContextCompat.getColorStateList(it, R.color.yellow) }
                }
                if(millis in 150001..300000) {
                    textViewClock.setTextColor(context?.let { AppCompatResources.getColorStateList(it, R.color.clock_orange) })
                    textViewClock.backgroundTintList = context?.let { ContextCompat.getColorStateList(it, R.color.clock_orange) }
                }
                if(millis < 150000) {
                    textViewClock.setTextColor(context?.let { AppCompatResources.getColorStateList(it, R.color.red) })
                    textViewClock.backgroundTintList = context?.let { ContextCompat.getColorStateList(it, R.color.red) }
                }
            }
        }, 0, 1000)
    }

    override fun showConfirmationBack() {
        flow.visibility = View.INVISIBLE
        groupConfirmation.visibility = View.VISIBLE
    }
}