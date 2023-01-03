package com.example.gamificationapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.MediaController
import android.widget.SeekBar
import android.widget.TextView
import android.widget.VideoView
import androidx.fragment.app.Fragment
import com.example.gamificationapp.R


class VideoFragment: Fragment() {

    private lateinit var videoView : VideoView
    private lateinit var textViewPaused : TextView
    private lateinit var imageViewPlay : ImageView
    private var playing : Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_video, container, false)
        videoView = view.findViewById(R.id.id_video_view)
        videoView.setVideoPath("android.resource://" + view.context.packageName + "/" + R.raw.video)
        videoView.start()
        videoView.setOnClickListener { controlVideoPlay() }
        textViewPaused = view.findViewById(R.id.id_text_view_paused)
        imageViewPlay = view.findViewById(R.id.id_image_view_play)
        return view
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

}