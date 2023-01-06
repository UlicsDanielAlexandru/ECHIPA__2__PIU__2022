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


class LevelsFragment : Fragment() {

    private lateinit var dragAndDropFragment: DragAndDropFragment
    private lateinit var recyclerView: RecyclerView
    private val dataSource = mutableListOf<Int>() as ArrayList<Int>
    private val recyclerViewAdapter : LevelsRecycleAdapter by lazy {
        LevelsRecycleAdapter(dataSource) {
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.id_frame_layout_fragment, dragAndDropFragment)
                addToBackStack("drag and drop fragment")
                commit()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_levels, container, false)
        dragAndDropFragment = DragAndDropFragment()
        recyclerView = view.findViewById(R.id.id_recycle_view_levels)
        recyclerView.apply {
            layoutManager = GridLayoutManager(activity, 3)
            adapter = recyclerViewAdapter
            setHasFixedSize(true)
        }
        dataSource.clear()
        (1 .. 12).forEach{
            dataSource.add(it)
        }
        recyclerViewAdapter.notifyDataSetChanged()
        return view
    }


}