package com.example.gamificationapp.fragments

import android.app.AlertDialog
import android.opengl.Visibility
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.gamificationapp.R


class MakeStatusAvailableFragment : Fragment(R.layout.fragment_make_status_available_disabled) {

    lateinit var enable: TextView
    lateinit var disable: TextView

    lateinit var startHours: EditText
    lateinit var finishHours: EditText

    lateinit var startMinutes: EditText
    lateinit var finishMinutes: EditText

    lateinit var backTime: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_make_status_available_disabled, container, false)
        enable = view.findViewById(R.id.id_text_view_enabled)
        disable = view.findViewById(R.id.id_text_view_disabled)

        startHours = view.findViewById(R.id.id_text_view_start_hours)
        finishHours = view.findViewById(R.id.id_text_view_stop_hours)

        startMinutes = view.findViewById(R.id.id_text_view_start_minutes)
        finishMinutes = view.findViewById(R.id.id_text_view_stop_minutes)

        backTime = view.findViewById(R.id.id_text_view_status_interval_box)

        disable.setOnClickListener {
            var sth: Int = 0
            var fnh: Int = 0
            var stm: Int = 0
            var fnm: Int = 0

            sth = startHours.text.toString().toInt()
            fnh = finishHours.text.toString().toInt()
            stm = startMinutes.text.toString().toInt()
            fnm = finishMinutes.text.toString().toInt()

            if (sth < fnh) {
                if (sth > 23 || fnh > 23) {
                    val builder = AlertDialog.Builder(context)
                    builder.setTitle("Wrong input")
                    builder.setMessage("Start and stop must be numbers below 24")
                    builder.setPositiveButton(android.R.string.yes) { _, _ ->
                        Toast.makeText(
                            context, "OK", Toast.LENGTH_SHORT
                        ).show()
                    }
                    builder.show()
                } else {
                    disable.visibility = View.INVISIBLE
                    enable.visibility = View.VISIBLE
                    backTime.background = resources.getDrawable(R.drawable.rounded_button, null)
                    startHours.isFocusable = true
                    startHours.isFocusableInTouchMode = true
                    startHours.inputType = InputType.TYPE_CLASS_NUMBER
                    finishHours.isFocusable = true
                    finishHours.isFocusableInTouchMode = true
                    finishHours.inputType = InputType.TYPE_CLASS_NUMBER
                    startMinutes.isFocusable = true
                    startMinutes.isFocusableInTouchMode = true
                    startMinutes.inputType = InputType.TYPE_CLASS_NUMBER
                    finishMinutes.isFocusable = true
                    finishMinutes.isFocusableInTouchMode = true
                    finishMinutes.inputType = InputType.TYPE_CLASS_NUMBER

                }

            } else {
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Wrong input")
                builder.setMessage("Start must be smaller than stop")
                builder.setPositiveButton(android.R.string.yes) { _, _ ->
                    Toast.makeText(
                        context, "OK", Toast.LENGTH_SHORT
                    ).show()
                }
                builder.show()
            }
        }

        enable.setOnClickListener {
            backTime.background = resources.getDrawable(R.drawable.rounded_button_grey, null)
            startHours.isFocusable = false
            startHours.isFocusableInTouchMode = false
            startHours.inputType = InputType.TYPE_NULL
            finishHours.isFocusable = false
            finishHours.isFocusableInTouchMode = false
            finishHours.inputType = InputType.TYPE_NULL
            startMinutes.isFocusable = false
            startMinutes.isFocusableInTouchMode = false
            startMinutes.inputType = InputType.TYPE_NULL
            finishMinutes.isFocusable = false
            finishMinutes.isFocusableInTouchMode = false
            finishMinutes.inputType = InputType.TYPE_NULL

            disable.visibility = View.VISIBLE
            enable.visibility = View.INVISIBLE
            startHours.setText("00")
            finishHours.setText("23")
            startMinutes.setText("00")
            finishMinutes.setText("00")
        }

        return view
    }

}