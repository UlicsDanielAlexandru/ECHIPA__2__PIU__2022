package com.example.gamificationapp.fragments

import android.Manifest
import android.content.ContentValues
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.gamificationapp.R


class CreateVideoAttentionRecordFragment : Fragment(R.layout.fragment_create_video_attention_record) {

    lateinit var camera: PreviewView
    lateinit var time: TextView
    lateinit var clock: TextView
    lateinit var next: TextView

    var createVideoAttentionQuestionsFragment: CreateVideoAttentionQuestionsFragment = CreateVideoAttentionQuestionsFragment()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_create_video_attention_record, container, false)
        camera = view.findViewById(R.id.id_preview_view_camera)
        time = view.findViewById(R.id.id_text_view_time)
        clock = view.findViewById(R.id.id_text_view_clock_icon)
        next = view.findViewById(R.id.id_button_next_video_attention)

        activity?.let { ActivityCompat.requestPermissions(it, arrayOf(Manifest.permission.CAMERA), 1888) }

        val timer = object: CountDownTimer(30000, 100)
        {
            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished / 100

                val minutes = seconds / 60
                val sec = seconds % 60

                var res = ""
                if(minutes < 10)
                {
                    res += "0$minutes"
                }else{
                    res += minutes
                }
                res += ":"
                if(sec < 10)
                {
                    res += "0$sec"
                }else {
                    res += sec
                }
                if(millisUntilFinished < 10000)
                {
                    time.setTextColor(resources.getColor(R.color.red, null))
                    clock.background = resources.getDrawable(R.drawable.clock_icon_red, null)


                }else if(millisUntilFinished in 10001..19999)
                {
                    time.setTextColor(resources.getColor(R.color.clock_orange, null))
                    clock.background = resources.getDrawable(R.drawable.clock_icon_orange, null)
                }else
                {
                    time.setTextColor(resources.getColor(R.color.green, null))
                    clock.background = resources.getDrawable(R.drawable.clock_icon, null)
                }
                time.text = res
            }

            override fun onFinish() {
                activity?.supportFragmentManager?.beginTransaction()?.apply {
                    replace(R.id.id_frame_layout_fragment, createVideoAttentionQuestionsFragment)
                    addToBackStack("video attention questions create fragment")
                    commit()
                }
            }

        }

        timer.start()

        val cameraProviderFuture = context?.let { ProcessCameraProvider.getInstance(it) }
        context?.let { ContextCompat.getMainExecutor(it) }?.let { executor ->
            cameraProviderFuture?.addListener({
                val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
                val preview = Preview.Builder()
                    .build()
                    .also {
                        it.setSurfaceProvider(camera.surfaceProvider)
                    }
                val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA
                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        this, cameraSelector, preview)
                } catch(exc: Exception) {
                    Log.e(ContentValues.TAG, "Use case binding failed", exc)
                }
            }, executor)
        }

        next.setOnClickListener{
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.id_frame_layout_fragment, createVideoAttentionQuestionsFragment)
                addToBackStack("video attention  questions create fragment")
                commit()
            }
        }

        return view
    }
}