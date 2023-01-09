package com.example.gamificationapp

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.gamificationapp.fragments.*

class AppActivity : AppCompatActivity() {
    lateinit var textViewUsername: TextView
    lateinit var textViewLogout: TextView
    lateinit var subjectFragment: SubjectFragment


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
    }

    override fun onBackPressed() {
        val fragments = supportFragmentManager.fragments
        if (fragments[fragments.size - 1] is ConfirmationBackFragment)
            (fragments[fragments.size - 1] as ConfirmationBackFragment).showConfirmationBack()
        else
            super.onBackPressed()
    }

}