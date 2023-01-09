package com.example.gamificationapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.gamificationapp.fragments.*

class AppActivity : AppCompatActivity() {
    private lateinit var textViewUsername: TextView
    lateinit var textViewLogout: TextView
    lateinit var userStatus: ImageView
    lateinit var play: ImageView
    lateinit var scores: ImageView

    lateinit var subjectFragment: SubjectFragment
    lateinit var scoreboardFragment: ScoreboardFragment

    var makeUserStatusFragment = MakeUserStatusFragment()

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

        userStatus = findViewById(R.id.id_circle_user_icon)
        play = findViewById(R.id.id_circle_play_solid)
        scores = findViewById(R.id.id_circle_settings_icon)

        textViewLogout = findViewById(R.id.id_text_view_logout)
        subjectFragment = SubjectFragment()
        scoreboardFragment = ScoreboardFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.id_frame_layout_fragment, subjectFragment)
            commit()
        }
    }

    private fun setListeners() {
        textViewLogout.setOnClickListener {
            supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            startActivity(Intent(this@AppActivity, MainActivity::class.java))
            this.finish()
        }

        userStatus.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, makeUserStatusFragment)
                addToBackStack("user availability fragment")
                commit()
            }
        }

        scores.setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.id_frame_layout_fragment, scoreboardFragment)
                addToBackStack("scoreboard fragment")
                commit()
            }
        }

        play.setOnClickListener {
            val fragments = supportFragmentManager.fragments
            if (fragments[fragments.size - 1] is ConfirmationBackFragment)
                (fragments[fragments.size - 1] as ConfirmationBackFragment).showConfirmationBack()
            else {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.id_frame_layout_fragment, subjectFragment)
                    addToBackStack("subject fragment")
                    commit()
                }
            }

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