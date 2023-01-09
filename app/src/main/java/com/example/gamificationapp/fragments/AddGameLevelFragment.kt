package com.example.gamificationapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gamificationapp.R
import com.example.gamificationapp.adapters.CreateLevelsRecyclerAdapter

class AddGameLevelFragment : Fragment(R.layout.fragment_add_game_level) {

    var dropDownsFragment: DropDownsFragment = DropDownsFragment()
    lateinit var recyclerView: RecyclerView
    private val dataSource = mutableListOf<String>() as ArrayList<String>
    private val recyclerViewAdapter : CreateLevelsRecyclerAdapter by lazy {
        CreateLevelsRecyclerAdapter(dataSource, clickListener = {activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.id_frame_layout_fragment, dropDownsFragment)
            addToBackStack("drop downs create fragment")
            commit()
        }})
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_game_level, container, false)
        recyclerView = view.findViewById(R.id.id_recycle_view_create_levels)
        recyclerView.apply {
            adapter = recyclerViewAdapter
            setHasFixedSize(true)
        }
        while(dataSource.isNotEmpty())
        {
            dataSource.removeFirst()
        }
        for(i in 1 .. 10)
            dataSource.add(i.toString())
        dataSource.add("+")
        return view
    }


}