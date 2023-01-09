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

class ExperimentedUserCallFragment : Fragment() {

    lateinit var previewViewCamera : PreviewView
    lateinit var textViewQuestion : TextView
    private val question : String = "What is blockchain?"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_experimented_user_call, container, false)
        initializeViews(view)
        return view
    }

    private fun initializeViews(view: View) {
        previewViewCamera = view.findViewById(R.id.id_preview_view_camera)
        textViewQuestion = view.findViewById(R.id.id_text_view_question)
        textViewQuestion.text = question
        val cameraProviderFuture = context?.let { ProcessCameraProvider.getInstance(it) }
        context?.let { ContextCompat.getMainExecutor(it) }?.let { executor ->
            cameraProviderFuture?.addListener({
                val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
                val preview = Preview.Builder()
                    .build()
                    .also {
                        it.setSurfaceProvider(previewViewCamera.surfaceProvider)
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
    }

}