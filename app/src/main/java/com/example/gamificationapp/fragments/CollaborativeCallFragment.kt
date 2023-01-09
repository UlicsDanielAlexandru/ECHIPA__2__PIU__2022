package com.example.gamificationapp.fragments

import android.os.Bundle
import android.provider.Contacts.Intents.UI
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.Group
import androidx.fragment.app.Fragment
import com.example.gamificationapp.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CollaborativeCallFragment : Fragment() {

    private lateinit var textViewWaiting : TextView
    private lateinit var groupUsers : Group

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_collaborative_call, container, false)
        initializeViews(view)
        return view
    }

    private fun initializeViews(view : View) {
        textViewWaiting = view.findViewById(R.id.id_text_view_waiting)
        GlobalScope.launch(Dispatchers.Main) {
            delay(1000)
            groupUsers = view.findViewById(R.id.id_group_users)
            groupUsers.visibility = View.VISIBLE
            textViewWaiting.visibility = View.INVISIBLE
        }
    }

}