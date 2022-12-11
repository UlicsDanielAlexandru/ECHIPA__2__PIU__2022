package com.example.gamificationapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    lateinit var loginButton: Button
    lateinit var usernameInput: EditText
    lateinit var passwordInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.window.navigationBarColor = getColor(R.color.grey)
        initialiseViews()

        loginButton.setOnClickListener {
            val intent = Intent(this, AppActivity::class.java)
            intent.putExtra("professor", false)
            startActivity(intent)
        }
    }

    private fun initialiseViews() {
        loginButton = findViewById(R.id.id_button_sign_in)
    }
}