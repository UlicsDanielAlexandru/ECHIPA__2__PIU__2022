package com.example.gamificationapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gamificationapp.fragments.*

class AppActivity : AppCompatActivity() {
    lateinit var subjectFragment: SubjectFragment
    lateinit var buttonsFragment: ButtonsFragment

    //play game fragments
    lateinit var dropDownsFragment: DropDownsFragment
    lateinit var timeSelectionFragment: TimeSelectionFragment
    lateinit var createDragDropFragment: CreateDragDropFragment
    lateinit var createFillTheGapFragment: CreateFillTheGapFragment
    lateinit var createMultipleChoiceFragment: CreateMultipleChoiceFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)
        this.window.navigationBarColor = getColor(R.color.grey)
        initialiseViews()
    }

    private fun initialiseViews() {
        dropDownsFragment = DropDownsFragment()
        timeSelectionFragment = TimeSelectionFragment()
        createDragDropFragment = CreateDragDropFragment()
        createFillTheGapFragment = CreateFillTheGapFragment()
        createMultipleChoiceFragment = CreateMultipleChoiceFragment()

        buttonsFragment = ButtonsFragment ({
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, subjectFragment)
                addToBackStack("create game select level fragment")
                commit()
            }},{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, dropDownsFragment)
                addToBackStack("create game drop downs fragment")
                commit()
            }},{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, timeSelectionFragment)
                addToBackStack("create game select time fragment")
                commit()
            }},{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, createDragDropFragment)
                addToBackStack("create game drag and drop fragment")
                commit()
            }},{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, createFillTheGapFragment)
                addToBackStack("create game fill the gap fragment")
                commit()
            }},{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, createMultipleChoiceFragment)
                addToBackStack("create game multiple choice fragment")
                commit()
            }},{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, subjectFragment)
                addToBackStack("create game video attention1 fragment")
                commit()
            }},{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, subjectFragment)
                addToBackStack("create game video attention1 fragment")
                commit()
            }},{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, subjectFragment)
                addToBackStack("create game video attention1 fragment")
                commit()
            }},{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, subjectFragment)
                addToBackStack("create game video attention1 fragment")
                commit()
            }},{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, subjectFragment)
                addToBackStack("create game video attention1 fragment")
                commit()
            }},{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, subjectFragment)
                addToBackStack("create game video attention1 fragment")
                commit()
            }},{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, subjectFragment)
                addToBackStack("create game video attention1 fragment")
                commit()
            }},{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, subjectFragment)
                addToBackStack("create game video attention1 fragment")
                commit()
            }},{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, subjectFragment)
                addToBackStack("create game video attention1 fragment")
                commit()
            }},{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, subjectFragment)
                addToBackStack("create game video attention1 fragment")
                commit()
            }},{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, subjectFragment)
                addToBackStack("create game video attention1 fragment")
                commit()
            }},{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, subjectFragment)
                addToBackStack("create game video attention1 fragment")
                commit()
            }},{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, subjectFragment)
                addToBackStack("create game video attention1 fragment")
                commit()
            }},{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, subjectFragment)
                addToBackStack("create game video attention1 fragment")
                commit()
            }},{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, subjectFragment)
                addToBackStack("create game video attention1 fragment")
                commit()
            }}
        )

        subjectFragment = SubjectFragment ()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.id_frame_layout_fragment, buttonsFragment)
            addToBackStack("buttons fragment")
            commit()
        }
    }


}