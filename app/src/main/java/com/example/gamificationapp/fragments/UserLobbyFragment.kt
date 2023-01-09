package com.example.gamificationapp.fragments

import android.Manifest
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.gamificationapp.R

class UserLobbyFragment(var experimented : Boolean = false, var userText : String = "", var nrPeople : Int = 0): Fragment() {

    private lateinit var buttonStart : Button
    private lateinit var buttonCancel : Button
    private lateinit var previewViewCamera : PreviewView
    private lateinit var imageViewUser : ImageView
    private lateinit var textViewUser : TextView
    private lateinit var textViewUsers : TextView
    private lateinit var experimentedUserCallFragment: ExperimentedUserCallFragment
    private lateinit var collaborativeCallFragment: CollaborativeCallFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_lobby, container, false)
        imageViewUser = view.findViewById(R.id.id_image_view_user_image)
        textViewUser = view.findViewById(R.id.id_text_view_user)
        textViewUsers = view.findViewById(R.id.id_text_view_users)
        if(!experimented) {
            imageViewUser.visibility = View.INVISIBLE
            textViewUser.visibility = View.INVISIBLE
            textViewUsers.visibility = View.VISIBLE
            if(nrPeople > 0)
                textViewUsers.text = userText + "and " + nrPeople + " others"
            else
                textViewUsers.text = userText
        }
        else
            textViewUser.text = userText
        experimentedUserCallFragment = ExperimentedUserCallFragment()
        collaborativeCallFragment = CollaborativeCallFragment()
        activity?.let { ActivityCompat.requestPermissions(it, arrayOf(Manifest.permission.CAMERA), 1888) }
        initializeViews(view)
        setListeners()
        return view
    }

    private fun initializeViews(view : View) {
        buttonStart = view.findViewById(R.id.id_button_start)
        buttonCancel = view.findViewById(R.id.id_button_cancel)
        previewViewCamera = view.findViewById(R.id.id_preview_view_camera)
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
                    Log.e(TAG, "Use case binding failed", exc)
                }
            }, executor)
        }
    }

    private fun setListeners() {
        buttonStart.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                if(experimented)
                    replace(R.id.id_frame_layout_fragment, experimentedUserCallFragment)
                else
                    replace(R.id.id_frame_layout_fragment, collaborativeCallFragment)
                addToBackStack("call fragment")
                commit()
            }
        }
        buttonCancel.setOnClickListener {
            activity?.onBackPressed()
        }
    }

}
