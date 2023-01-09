package com.example.gamificationapp.fragments

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.gamificationapp.R

class CreateQuizFinishScreen : Fragment(R.layout.fragment_create_quiz_finish_screen) {

    lateinit var next: TextView
    lateinit var time: TextView
    lateinit var timer: CountDownTimer

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_create_quiz_finish_screen, container, false)
        next = view.findViewById(R.id.id_text_view_see_results)
        time = view.findViewById(R.id.id_text_view_time_left_quiz)

        var timer = object: CountDownTimer(30000, 100)
        {
            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished / 100

                val minutes = seconds / 60
                val sec = seconds % 60

                var res = ""
                if(minutes < 10)
                {
                    res += "0$minutes"
                }else{
                    res += minutes
                }
                res += ":"
                if(sec < 10)
                {
                    res += "0$sec"
                }else {
                    res += sec
                }
                if(millisUntilFinished < 10000)
                {
                    time.setTextColor(resources.getColor(R.color.red, null))
                }else if(millisUntilFinished in 10001..19999)
                {
                    time.setTextColor(resources.getColor(R.color.clock_orange, null))
                }else
                {
                    time.setTextColor(resources.getColor(R.color.green, null))
                }
                time.text = res
            }

            override fun onFinish() {
                val scoreboardQuizCreateFragment = ScoreboardQuizCreateFragment()
                timer.cancel()
                activity?.supportFragmentManager?.beginTransaction()?.apply {
                    replace(R.id.id_frame_layout_fragment, scoreboardQuizCreateFragment)
                    addToBackStack("score board create fragment")
                    commit()
                }
            }
        }

        timer.start()


        next.setOnClickListener{
            timer.cancel()
            val scoreboardQuizCreateFragment = ScoreboardQuizCreateFragment()
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.id_frame_layout_fragment, scoreboardQuizCreateFragment)
                addToBackStack("score board create fragment")
                commit()
            }
        }

        return view
    }
}