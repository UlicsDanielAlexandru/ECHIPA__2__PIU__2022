package com.example.gamificationapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.gamificationapp.fragments.*
import java.util.logging.Level

class AppActivity: AppCompatActivity(){
    lateinit var textViewUsername : TextView
    lateinit var textViewLogout : TextView
    lateinit var subjectFragment: SubjectFragment
    lateinit var buttonsFragment: ButtonsFragment
    lateinit var learningMethodsFragment: LearningMethodsFragment
    lateinit var insertQuizCodeFragment: InsertQuizCodeFragment
    lateinit var dropDownsFragment: DropDownsFragment
    lateinit var timeSelectionFragment: TimeSelectionFragment
    lateinit var createDragDropFragment: CreateDragDropFragment
    lateinit var createFillTheGapFragment: CreateFillTheGapFragment
    lateinit var createMultipleChoiceFragment: CreateMultipleChoiceFragment
    lateinit var levelsFragment: LevelsFragment
    lateinit var dragAndDropFragment: DragAndDropFragment
    lateinit var fillTheBlankFragment: FillTheBlankFragment
    lateinit var multipleChoiceFragment: MultipleChoiceFragment
    lateinit var videoFragment: VideoFragment
    lateinit var videoQuestionsFragment: VideoQuestionsFragment
    lateinit var collaborativeMethodSelectionFragment: CollaborativeMethodSelectionFragment
    lateinit var experimentedUserSelectionFragment: ExperimentedUserSelectionFragment
    lateinit var experimentedUserLobbyFragment: ExperimentedUserLobbyFragment
    lateinit var experimentedUserCallFragment: ExperimentedUserCallFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)
        this.window.navigationBarColor = getColor(R.color.grey)
        initialiseViews()
        setListeners()
    }

    private fun initialiseViews() {

        textViewUsername = findViewById(R.id.id_text_view_username)
        textViewUsername.text = intent.getStringExtra("username")

        textViewLogout = findViewById(R.id.id_text_view_logout)

        subjectFragment = SubjectFragment()
        learningMethodsFragment = LearningMethodsFragment()
        insertQuizCodeFragment = InsertQuizCodeFragment()
        dropDownsFragment = DropDownsFragment()
        timeSelectionFragment = TimeSelectionFragment()
        createDragDropFragment = CreateDragDropFragment()
        createFillTheGapFragment = CreateFillTheGapFragment()
        createMultipleChoiceFragment = CreateMultipleChoiceFragment()
        levelsFragment = LevelsFragment()
        dragAndDropFragment = DragAndDropFragment()
        fillTheBlankFragment = FillTheBlankFragment()
        multipleChoiceFragment = MultipleChoiceFragment()
        videoFragment = VideoFragment()
        videoQuestionsFragment = VideoQuestionsFragment()
        collaborativeMethodSelectionFragment = CollaborativeMethodSelectionFragment()
        experimentedUserSelectionFragment = ExperimentedUserSelectionFragment()
        experimentedUserLobbyFragment = ExperimentedUserLobbyFragment()
        experimentedUserCallFragment = ExperimentedUserCallFragment()

        buttonsFragment = ButtonsFragment({
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
                replace(R.id.id_frame_layout_fragment, experimentedUserCallFragment)
                addToBackStack("levels fragment")
                commit()
            }},{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, dragAndDropFragment)
                addToBackStack("dragAndDrop fragment")
                commit()
            }},{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, fillTheBlankFragment)
                addToBackStack("fill the blank fragment")
                commit()
            }},{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, multipleChoiceFragment)
                addToBackStack("multiple choice fragment")
                commit()
            }},{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, videoFragment)
                addToBackStack("video fragment")
                commit()
            }},{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, videoQuestionsFragment)
                addToBackStack("video questions fragment")
                commit()
            }},{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, collaborativeMethodSelectionFragment)
                addToBackStack("collaborative method selection fragment")
                commit()
            }},{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, experimentedUserSelectionFragment)
                addToBackStack("experimented user selection fragment")
                commit()
            }},{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, experimentedUserLobbyFragment)
                addToBackStack("experimented user lobby fragment")
                commit()
            }},{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, subjectFragment)
                addToBackStack("create game video attention1 fragment")
                commit()
            }},{
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

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.id_frame_layout_fragment, buttonsFragment)
            commit()
        }
    }

    private fun setListeners() {
        textViewLogout.setOnClickListener { super.onBackPressed() }
    }

}