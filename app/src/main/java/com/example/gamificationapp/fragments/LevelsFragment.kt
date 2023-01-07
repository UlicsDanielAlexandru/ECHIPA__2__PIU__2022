package com.example.gamificationapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gamificationapp.R
import com.example.gamificationapp.adapters.LevelsRecycleAdapter


class LevelsFragment : Fragment(R.layout.fragment_levels) {

    private lateinit var recyclerView: RecyclerView
    private val dataSource = mutableListOf<Int>() as ArrayList<Int>
    private val recyclerViewAdapter : LevelsRecycleAdapter by lazy {
        LevelsRecycleAdapter(dataSource)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_levels, container, false)
        recyclerView = view.findViewById(R.id.id_recycle_view_levels)
        recyclerView.apply {
            layoutManager = GridLayoutManager(activity, 3)
            adapter = recyclerViewAdapter
            setHasFixedSize(true)
        }
        (1 .. 12).forEach{
            dataSource.add(it)
        }
        recyclerViewAdapter.notifyDataSetChanged()
        return view
    }


}