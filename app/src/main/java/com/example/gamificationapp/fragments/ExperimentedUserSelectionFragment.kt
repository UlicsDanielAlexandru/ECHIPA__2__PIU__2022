package com.example.gamificationapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gamificationapp.R
import com.example.gamificationapp.adapters.UsersRecyclerAdapter
import com.example.gamificationapp.models.Status
import com.example.gamificationapp.models.UserItem

class ExperimentedUserSelectionFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private val dataSource = mutableListOf<UserItem>() as ArrayList<UserItem>
    private val recyclerViewAdapter : UsersRecyclerAdapter by lazy {
        UsersRecyclerAdapter(dataSource)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_experimented_user_selection, container, false)
        dataSource.clear()
        recyclerView = view.findViewById(R.id.id_recycle_view_users)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = recyclerViewAdapter
            setHasFixedSize(true)
        }
        dataSource.add(UserItem(context?.let { ContextCompat.getDrawable(it, R.drawable.avatar_icon) }, "Dorel Instalatorul", Status.ACTIVE))
        dataSource.add(UserItem(context?.let { ContextCompat.getDrawable(it, R.drawable.avatar_icon) }, "Floricia Printeza", Status.BUSY))
        dataSource.add(UserItem(context?.let { ContextCompat.getDrawable(it, R.drawable.avatar_icon) }, "Ion Avion", Status.AWAY))
        dataSource.add(UserItem(context?.let { ContextCompat.getDrawable(it, R.drawable.avatar_icon) }, "Simona Cosmina", Status.ACTIVE))
        recyclerViewAdapter.notifyDataSetChanged()
        return view
    }

}