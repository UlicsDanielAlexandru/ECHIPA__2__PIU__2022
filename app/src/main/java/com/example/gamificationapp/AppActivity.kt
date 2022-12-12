package com.example.gamificationapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gamificationapp.fragments.ButtonsFragment
import com.example.gamificationapp.fragments.DragAndDropFragment
import com.example.gamificationapp.fragments.LevelsFragment
import com.example.gamificationapp.fragments.SubjectFragment

class AppActivity : AppCompatActivity() {
    lateinit var subjectFragment: SubjectFragment
    lateinit var buttonsFragment: ButtonsFragment
    lateinit var levelsFragment: LevelsFragment
    lateinit var dragAndDropFragment: DragAndDropFragment


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
        }, {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, levelsFragment)
                addToBackStack("levels fragment")
                commit()
            }
        },
            {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.id_frame_layout_fragment, dragAndDropFragment)
                    addToBackStack("dragAndDrop fragment")
                    commit()
            }})

        subjectFragment = SubjectFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.id_frame_layout_fragment, buttonsFragment)
            addToBackStack("buttons fragment")
            commit()
        }

        levelsFragment = LevelsFragment()

        dragAndDropFragment = DragAndDropFragment()

    }


}