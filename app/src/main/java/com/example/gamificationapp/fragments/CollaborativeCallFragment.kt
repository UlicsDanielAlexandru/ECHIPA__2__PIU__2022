package com.example.gamificationapp.fragments

import android.content.ContentValues
import android.os.Bundle
import android.provider.Contacts.Intents.UI
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.Group
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.gamificationapp.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CollaborativeCallFragment : Fragment() {

    lateinit var previewViewCamera : PreviewView
    private lateinit var textViewWaiting : TextView
    private lateinit var groupUsers : Group
    lateinit var textViewCamera : TextView
    lateinit var imageViewPhone : ImageView
    lateinit var imageViewCamera : ImageView
    lateinit var imageViewCameraSlash : ImageView
    lateinit var imageViewMicrophone : ImageView
    lateinit var imageViewMicrophoneSlash : ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_collaborative_call, container, false)
        initializeViews(view)
        setListeners()
        return view
    }

    private fun initializeViews(view : View) {
        previewViewCamera = view.findViewById(R.id.id_preview_view_camera)
        textViewWaiting = view.findViewById(R.id.id_text_view_waiting)
        textViewCamera = view.findViewById(R.id.id_text_view_camera)
        imageViewPhone = view.findViewById(R.id.id_image_view_phone)
        imageViewCamera = view.findViewById(R.id.id_image_view_camera_icon)
        imageViewCameraSlash = view.findViewById(R.id.id_image_view_camera_icon_slash)
        imageViewMicrophone = view.findViewById(R.id.id_image_view_microphone_icon)
        imageViewMicrophoneSlash = view.findViewById(R.id.id_image_view_microphone_icon_slash)
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
        GlobalScope.launch(Dispatchers.Main) {
            delay(1000)
            groupUsers = view.findViewById(R.id.id_group_users)
            groupUsers.visibility = View.VISIBLE
            textViewWaiting.visibility = View.INVISIBLE
        }
    }
    private fun setListeners() {
        imageViewCamera.setOnClickListener {
            imageViewCameraSlash.visibility = View.VISIBLE
            imageViewCamera.visibility = View.INVISIBLE
            (previewViewCamera.parent as CardView).visibility = View.INVISIBLE
            textViewCamera.visibility = View.VISIBLE
        }
        imageViewCameraSlash.setOnClickListener {
            imageViewCameraSlash.visibility = View.INVISIBLE
            imageViewCamera.visibility = View.VISIBLE
            (previewViewCamera.parent as CardView).visibility = View.VISIBLE
            textViewCamera.visibility = View.INVISIBLE
        }
        imageViewMicrophone.setOnClickListener {
            imageViewMicrophone.visibility = View.INVISIBLE
            imageViewMicrophoneSlash.visibility = View.VISIBLE
        }
        imageViewMicrophoneSlash.setOnClickListener {
            imageViewMicrophone.visibility = View.VISIBLE
            imageViewMicrophoneSlash.visibility = View.INVISIBLE
        }
        imageViewPhone.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
            activity?.onBackPressed()
        }
    }

}