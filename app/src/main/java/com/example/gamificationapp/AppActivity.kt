package com.example.gamificationapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.TaskStackBuilder
import androidx.fragment.app.Fragment

class AppActivity: AppCompatActivity(){
    lateinit var subjectFragment: Fragment
    lateinit var buttonsFragment: ButtonsFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)
        this.window.navigationBarColor = getColor(R.color.grey)
        initialiseViews()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.id_frame_layout_fragment, buttonsFragment)
            addToBackStack("buttons fragment")
            commit()
        }

    }

    private fun initialiseViews() {
        subjectFragment = SubjectFragment()
        buttonsFragment = ButtonsFragment()

        buttonsFragment.screen1Listener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, subjectFragment)
                addToBackStack("subjects fragment")
                commit()
            }
        }
    }


}