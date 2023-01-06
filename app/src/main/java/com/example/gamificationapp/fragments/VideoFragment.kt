package com.example.gamificationapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.Group
import androidx.fragment.app.Fragment
import com.example.gamificationapp.R
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.timerTask


class VideoFragment(var millis : Long = 0): Fragment(), ConfirmationBackFragment {

    private lateinit var videoView : VideoView
    private lateinit var textViewPaused : TextView
    private lateinit var imageViewPlay : ImageView
    private var playing : Boolean = true
    private lateinit var textViewClock : TextView
    private lateinit var timer: Timer
    private lateinit var buttonConfirmationYes : Button
    private lateinit var buttonConfirmationNo : Button
    private lateinit var groupConfirmation : Group
    private lateinit var learningMethodsFragment : LearningMethodsFragment
    private lateinit var videoQuestionsFragment : VideoQuestionsFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_video, container, false)
        videoView = view.findViewById(R.id.id_video_view)
        videoView.setVideoPath("android.resource://" + view.context.packageName + "/" + R.raw.video)
        videoView.start()
        textViewPaused = view.findViewById(R.id.id_text_view_paused)
        imageViewPlay = view.findViewById(R.id.id_image_view_play)
        textViewClock = view.findViewById(R.id.id_text_view_clock)
        buttonConfirmationYes = view.findViewById(R.id.id_button_confirmation_yes)
        buttonConfirmationNo = view.findViewById(R.id.id_button_confirmation_no)
        groupConfirmation = view.findViewById(R.id.id_group_confirmation)
        learningMethodsFragment = LearningMethodsFragment()
        videoQuestionsFragment = VideoQuestionsFragment()
        initializeTimer()
        setListeners()
        return view
    }

    private fun setListeners() {
        videoView.setOnClickListener { controlVideoPlay() }
        videoView.setOnCompletionListener {
            timer.cancel()
            videoQuestionsFragment.millis = millis
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.id_frame_layout_fragment, videoQuestionsFragment)
                addToBackStack("video questions fragment")
                commit()
            }
        }
        buttonConfirmationNo.setOnClickListener {
            groupConfirmation.visibility = View.INVISIBLE
            videoView.start()
        }
        buttonConfirmationYes.setOnClickListener {
            (1..4).forEach{ _ -> activity?.supportFragmentManager?.popBackStack()}
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.id_frame_layout_fragment, learningMethodsFragment)
                addToBackStack("learning methods fragment")
                commit()
            }
        }
    }

    private fun controlVideoPlay() {
        if(playing) {
            videoView.pause()
            textViewPaused.text = "PAUSED"
            imageViewPlay.visibility = View.VISIBLE
        }
        else {
            videoView.start()
            textViewPaused.text = ""
            imageViewPlay.visibility = View.INVISIBLE
        }
        playing = !playing
    }

    private fun initializeTimer() {
        timer = Timer()
        timer.scheduleAtFixedRate(timerTask {
            activity?.runOnUiThread {
                millis -= 1000
                textViewClock.text = String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millis),
                    TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1));
            }
        }, 0, 1000)
    }

    override fun showConfirmationBack() {
        groupConfirmation.visibility = View.VISIBLE
        videoView.pause()
    }
}