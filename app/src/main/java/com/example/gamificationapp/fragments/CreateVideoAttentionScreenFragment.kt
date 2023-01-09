package com.example.gamificationapp.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.gamificationapp.R


class CreateVideoAttentionScreenFragment :
    Fragment(R.layout.fragment_create_video_attention_screen) {

    lateinit var screen: TextView
    lateinit var video: TextView

    var createVideoAttentionRecordFragment: CreateVideoAttentionRecordFragment = CreateVideoAttentionRecordFragment()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_create_video_attention_screen, container, false)
        screen = view.findViewById(R.id.id_text_view_start_screen_record)
        video = view.findViewById(R.id.id_text_view_start_video_record)

        screen.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Wrong device type")
            builder.setMessage("This device does not support screen record")

            builder.setPositiveButton(android.R.string.yes) { _, _ ->
                Toast.makeText(context,
                    "OK", Toast.LENGTH_SHORT).show()
            }

            builder.show()
        }

        video.setOnClickListener {
                activity?.supportFragmentManager?.beginTransaction()?.apply {
                    replace(R.id.id_frame_layout_fragment, createVideoAttentionRecordFragment)
                    addToBackStack("video attention record create fragment")
                    commit()
                }
        }
        return view
    }
}