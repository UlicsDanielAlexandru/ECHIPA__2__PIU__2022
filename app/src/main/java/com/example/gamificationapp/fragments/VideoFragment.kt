package com.example.gamificationapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.Group
import androidx.core.content.ContextCompat
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
    private lateinit var imageViewClock : ImageView
    private lateinit var scoreboardGameFragment: ScoreboardGameFragment
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
        imageViewClock = view.findViewById(R.id.id_image_view_clock)
        scoreboardGameFragment = ScoreboardGameFragment()
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
                replace(R.id.id_frame_layout_fragment, scoreboardGameFragment)
                addToBackStack("scoreboard fragment")
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
                    (1..4).forEach{ _ -> activity?.supportFragmentManager?.popBackStack()}
                    activity?.supportFragmentManager?.beginTransaction()?.apply {
                        replace(R.id.id_frame_layout_fragment, scoreboardGameFragment)
                        addToBackStack("scoreboard fragment")
                        commit()
                    }
                    this.cancel()
                }
            }
        }, 0, 1000)
    }

    override fun showConfirmationBack() {
        groupConfirmation.visibility = View.VISIBLE
        videoView.pause()
    }
}