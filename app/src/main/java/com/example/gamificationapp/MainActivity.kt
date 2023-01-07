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
            login()
        }
    }

    private fun initialiseViews() {
        loginButton = findViewById(R.id.id_button_sign_in)
        usernameInput = findViewById(R.id.id_edit_text_username)
    }

    private fun login() {
        val intent = Intent(this, AppActivity::class.java)
        if(usernameInput.text.toString() == "admin") {
            intent.putExtra("professor", true)
            intent.putExtra("username", usernameInput.text.toString())
        }
        else {
            intent.putExtra("professor", false)
            intent.putExtra("username", usernameInput.text.toString())
        }
        startActivity(intent)
    }
}