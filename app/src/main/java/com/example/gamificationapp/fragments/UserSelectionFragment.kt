package com.example.gamificationapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gamificationapp.R
import com.example.gamificationapp.adapters.UsersRecyclerAdapter
import com.example.gamificationapp.models.Status
import com.example.gamificationapp.models.UserItem

class UserSelectionFragment(var experimented : Boolean = false) : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var groupExperimented : Group
    private lateinit var groupCollaborative : Group
    private lateinit var buttonStart : Button
    private lateinit var textViewNrPeople : TextView
    private val dataSource = mutableListOf<UserItem>() as ArrayList<UserItem>
    private lateinit var userLobbyFragment: UserLobbyFragment
    private val recyclerViewAdapter : UsersRecyclerAdapter by lazy {
        UsersRecyclerAdapter(dataSource, true, {
            userLobbyFragment.experimented = true
            userLobbyFragment.userText = "Dorel Instalatorul"
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.id_frame_layout_fragment, userLobbyFragment)
                addToBackStack("experimented user lobby fragment")
                commit()
            }
        }, buttonStart, textViewNrPeople)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_selection, container, false)
        groupExperimented = view.findViewById(R.id.id_group_experimented)
        groupCollaborative = view.findViewById(R.id.id_group_collaborative)
        buttonStart = view.findViewById(R.id.id_button_start)
        textViewNrPeople = view.findViewById(R.id.id_text_view_nr_people)
        recyclerViewAdapter.button = buttonStart
        recyclerViewAdapter.textView = textViewNrPeople
        recyclerViewAdapter.selectedNr = 0
        if(!experimented) {
            groupCollaborative.visibility = View.VISIBLE
            groupExperimented.visibility = View.INVISIBLE
        }
        userLobbyFragment = UserLobbyFragment()
        dataSource.clear()
        recyclerView = view.findViewById(R.id.id_recycle_view_users)
        recyclerViewAdapter.experimented = experimented
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = recyclerViewAdapter
            setHasFixedSize(true)
        }
        dataSource.add(UserItem(context?.let { ContextCompat.getDrawable(it, R.drawable.avatar_icon) }, "Dorel Instalatorul", Status.ACTIVE))
        dataSource.add(UserItem(context?.let { ContextCompat.getDrawable(it, R.drawable.avatar_icon) }, "Floricia Printzesa", Status.BUSY))
        dataSource.add(UserItem(context?.let { ContextCompat.getDrawable(it, R.drawable.avatar_icon) }, "Ion Avion", Status.AWAY))
        dataSource.add(UserItem(context?.let { ContextCompat.getDrawable(it, R.drawable.avatar_icon) }, "Simona Cosmina", Status.ACTIVE))
        recyclerViewAdapter.notifyDataSetChanged()
        buttonStart.setOnClickListener {
            userLobbyFragment.nrPeople = recyclerViewAdapter.selectedNr - 1
            userLobbyFragment.userText = dataSource.first { x -> x.selected }.username.toString()
            userLobbyFragment.experimented = experimented
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.id_frame_layout_fragment, userLobbyFragment)
                addToBackStack("experimented user lobby fragment")
                commit()
            }
        }
        return view
    }

}