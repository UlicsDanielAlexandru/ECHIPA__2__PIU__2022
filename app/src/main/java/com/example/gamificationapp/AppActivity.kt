package com.example.gamificationapp

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.TaskStackBuilder
import androidx.fragment.app.Fragment

class AppActivity: AppCompatActivity(){
    lateinit var subjectFragment: SubjectFragment
    lateinit var buttonsFragment: ButtonsFragment
    lateinit var learningMethodsFragment: LearningMethodsFragment
    lateinit var insertQuizCodeFragment: InsertQuizCodeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)
        this.window.navigationBarColor = getColor(R.color.grey)
        initialiseViews()
    }

    private fun initialiseViews() {

        buttonsFragment = ButtonsFragment({
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, subjectFragment)
                addToBackStack("subjects fragment")
                commit()
            }
        },{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, learningMethodsFragment)
                addToBackStack("learning methods fragment")
                commit()
            }
        },{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, insertQuizCodeFragment)
                addToBackStack("insert quiz code fragment")
                commit()
            }
        })

        subjectFragment = SubjectFragment()
        learningMethodsFragment = LearningMethodsFragment()
        insertQuizCodeFragment = InsertQuizCodeFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.id_frame_layout_fragment, buttonsFragment)
            addToBackStack("buttons fragment")
            commit()
        }


    }


}