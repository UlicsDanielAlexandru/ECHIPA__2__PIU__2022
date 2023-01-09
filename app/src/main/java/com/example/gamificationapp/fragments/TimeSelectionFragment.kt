package com.example.gamificationapp.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.gamificationapp.R


class TimeSelectionFragment : Fragment(R.layout.fragment_time_selection) {

    lateinit var arrowUpMin1: TextView
    lateinit var arrowUpMin2: TextView
    lateinit var arrowDownMin1: TextView
    lateinit var arrowDownMin2: TextView

    lateinit var arrowUpSec1: TextView
    lateinit var arrowUpSec2: TextView
    lateinit var arrowDownSec1: TextView
    lateinit var arrowDownSec2: TextView

    lateinit var min1: TextView
    lateinit var min2: TextView
    lateinit var sec1: TextView
    lateinit var sec2: TextView

    lateinit var next: TextView

    private var createDragDropFragment: CreateDragDropFragment = CreateDragDropFragment()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_time_selection, container, false)
        arrowDownMin1 = view.findViewById(R.id.id_text_view_down_arrow_min1)
        arrowDownMin2 = view.findViewById(R.id.id_text_view_down_arrow_min2)
        arrowDownSec1 = view.findViewById(R.id.id_text_view_down_arrow_sec1)
        arrowDownSec2 = view.findViewById(R.id.id_text_view_down_arrow_sec2)

        arrowUpMin1 = view.findViewById(R.id.id_text_view_up_arrow_min1)
        arrowUpMin2 = view.findViewById(R.id.id_text_view_up_arrow_min2)
        arrowUpSec1 = view.findViewById(R.id.id_text_view_up_arrow_sec1)
        arrowUpSec2 = view.findViewById(R.id.id_text_view_up_arrow_sec2)

        min1 = view.findViewById(R.id.id_text_view_min1)
        min2 = view.findViewById(R.id.id_text_view_min2)
        sec1 = view.findViewById(R.id.id_text_view_sec1)
        sec2 = view.findViewById(R.id.id_text_view_sec2)

        next = view.findViewById(R.id.id_text_view_next_time_selection)

        next.setOnClickListener{
            if(activity?.intent?.getBooleanExtra("quiz", false) == false)
            {
                activity?.supportFragmentManager?.beginTransaction()?.apply {
                    replace(R.id.id_frame_layout_fragment, createDragDropFragment)
                    addToBackStack("create drag and drop fragment")
                    commit()
                }
            }else{
                val createMultipleChoiceFragment = CreateMultipleChoiceFragment()
                activity?.supportFragmentManager?.beginTransaction()?.apply {
                    replace(R.id.id_frame_layout_fragment, createMultipleChoiceFragment)
                    addToBackStack("create quiz multiple choice fragment")
                    commit()
                }
            }

        }

        arrowDownMin1.setOnClickListener{
            var k = min1.text.toString().toInt()
            if(k > 0)
                k--
            min1.text = k.toString()
        }
        arrowDownMin2.setOnClickListener{
            var k = min2.text.toString().toInt()
            if(k > 0)
                k--
            min2.text = k.toString()
        }
        arrowDownSec1.setOnClickListener{
            var k = sec1.text.toString().toInt()
            if(k > 0)
                k--
            sec1.text = k.toString()
        }
        arrowDownSec2.setOnClickListener{
            var k = sec2.text.toString().toInt()
            if(k > 0)
                k--
            sec2.text = k.toString()
        }

        arrowUpMin1.setOnClickListener{
            var k = min1.text.toString().toInt()
            if(k < 5)
                k++;
            min1.text = k.toString();
        }
        arrowUpMin2.setOnClickListener{
            var k = min2.text.toString().toInt()
            if(k < 9)
                k++;
            min2.text = k.toString();
        }
        arrowUpSec1.setOnClickListener{
            var k = sec1.text.toString().toInt()
            if(k < 5)
                k++;
            sec1.text = k.toString();
        }
        arrowUpSec2.setOnClickListener{
            var k = sec2.text.toString().toInt()
            if(k < 9)
                k++;
            sec2.text = k.toString();
        }



        return view
    }
}