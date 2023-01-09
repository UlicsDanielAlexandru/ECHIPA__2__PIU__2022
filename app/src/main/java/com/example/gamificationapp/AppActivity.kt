package com.example.gamificationapp

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.gamificationapp.fragments.*

class AppActivity : AppCompatActivity() {
    lateinit var textViewUsername: TextView
    lateinit var textViewLogout: TextView
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
    lateinit var scoareboardFragment: ScoreboardFragment
    lateinit var questionQuizFragment: QuestionQuizFragment
    lateinit var correctAnswerQuizFragment: CorrectAnswerQuizFragment
    lateinit var question1QuizFragment: Question1QuizFragment
    //    lateinit var correctAnswer1QuizFragment: CorrectAnswerQuiz1Fragment
    lateinit var wrongAnswerQuiz1Fragment: WrongAnswerQuiz1Fragment
    lateinit var scoreboardQuizFragment: ScoreboardQuizFragment
    lateinit var fillTheBlankFragment: FillTheBlankFragment
    lateinit var multipleChoiceFragment: MultipleChoiceFragment
    lateinit var videoFragment: VideoFragment
    lateinit var videoQuestionsFragment: VideoQuestionsFragment
    lateinit var collaborativeMethodSelectionFragment: CollaborativeMethodSelectionFragment
    lateinit var userSelectionFragment: UserSelectionFragment
    lateinit var experimentedUserLobbyFragment: ExperimentedUserLobbyFragment
    lateinit var experimentedUserCallFragment: ExperimentedUserCallFragment
    lateinit var createMethodsFragment: CreatingMethodsFragment
    lateinit var scoreboardFragment: ScoreboardFragment

    var x: String = "2"

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
        userSelectionFragment = UserSelectionFragment()
        experimentedUserLobbyFragment = ExperimentedUserLobbyFragment()
        experimentedUserCallFragment = ExperimentedUserCallFragment()
        createMethodsFragment = CreatingMethodsFragment()
        scoareboardFragment = ScoreboardFragment()
        questionQuizFragment = QuestionQuizFragment()
        correctAnswerQuizFragment = CorrectAnswerQuizFragment()
        question1QuizFragment = Question1QuizFragment()
        wrongAnswerQuiz1Fragment = WrongAnswerQuiz1Fragment(x)
        scoreboardQuizFragment = ScoreboardQuizFragment()

        buttonsFragment = ButtonsFragment({
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, scoareboardFragment)
                addToBackStack("create game select level fragment")
                commit()
            }
        }, {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, dropDownsFragment)
                addToBackStack("create game drop downs fragment")
                commit()
            }
        }, {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, timeSelectionFragment)
                addToBackStack("create game select time fragment")
                commit()
            }
        }, {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, createDragDropFragment)
                addToBackStack("create game drag and drop fragment")
                commit()
            }
        }, {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, createFillTheGapFragment)
                addToBackStack("create game fill the gap fragment")
                commit()
            }
        }, {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, createMultipleChoiceFragment)
                addToBackStack("create game multiple choice fragment")
                commit()
            }
        }, {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, createMethodsFragment)
                addToBackStack("create methods fragment")
                commit()
            }
        }, {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, subjectFragment)
                addToBackStack("create game video attention1 fragment")
                commit()
            }
        }, {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, subjectFragment)
                addToBackStack("create game video attention1 fragment")
                commit()
            }
        }, {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, subjectFragment)
                addToBackStack("create game video attention1 fragment")
                commit()
            }
        }, {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, levelsFragment)
                addToBackStack("levels fragment")
                commit()
            }
        }, {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, dragAndDropFragment)
                addToBackStack("dragAndDrop fragment")
                commit()
            }
        }, {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, fillTheBlankFragment)
                addToBackStack("fill the blank fragment")
                commit()
            }
        }, {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, multipleChoiceFragment)
                addToBackStack("multiple choice fragment")
                commit()
            }
        }, {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, videoFragment)
                addToBackStack("video fragment")
                commit()
            }
        }, {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, videoQuestionsFragment)
                addToBackStack("video questions fragment")
                commit()
            }
        }, {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, collaborativeMethodSelectionFragment)
                addToBackStack("collaborative method selection fragment")
                commit()
            }
        }, {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, userSelectionFragment)
                addToBackStack("experimented user selection fragment")
                commit()
            }
        }, {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, experimentedUserLobbyFragment)
                addToBackStack("experimented user lobby fragment")
                commit()
            }
        }, {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, subjectFragment)
                addToBackStack("create game video attention1 fragment")
                commit()
            }
        }, {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, scoreboardFragment)
                addToBackStack("subjects fragment")
                commit()
            }
        }, {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, learningMethodsFragment)
                addToBackStack("learning methods fragment")
                commit()
            }
        }, {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, insertQuizCodeFragment)
                addToBackStack("insert quiz code fragment")
                commit()
            }
        },{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, questionQuizFragment)
                addToBackStack("question quiz fragment")
                commit()
            }
        }, {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, correctAnswerQuizFragment)
                addToBackStack("correct answer quiz fragment")
                commit()
            }
        }, {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, question1QuizFragment)
                addToBackStack("question 1 quiz fragment")
                commit()
            }
        }, {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, wrongAnswerQuiz1Fragment)
                addToBackStack("question 1 quiz fragment")
                commit()
            }
        }, {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, scoreboardQuizFragment)
                addToBackStack("scoreboard quiz fragment")
                commit()
            }
        }
        )

        supportFragmentManager.beginTransaction().apply {
            if (intent.getBooleanExtra("professor", false)) {
                replace(R.id.id_frame_layout_fragment, createMethodsFragment)
            }
            else
                replace(R.id.id_frame_layout_fragment, buttonsFragment)
            commit()
        }
    }

    private fun setListeners() {
        textViewLogout.setOnClickListener {
            supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            startActivity(Intent(this@AppActivity, MainActivity::class.java))
            this.finish()
        }
    }

    override fun onBackPressed() {
        val fragments = supportFragmentManager.fragments
        if (fragments[fragments.size - 1] is ConfirmationBackFragment)
            (fragments[fragments.size - 1] as ConfirmationBackFragment).showConfirmationBack()
        else
            super.onBackPressed()
    }

}