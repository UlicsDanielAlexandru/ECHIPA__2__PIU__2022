package com.example.gamificationapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    lateinit var loginButton: Button
    lateinit var usernameInput: EditText
    lateinit var passwordInput: EditText
    lateinit var textViewError: TextView

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
        usernameInput = findViewById(R.id.id_edit_text_username)
        passwordInput = findViewById(R.id.id_edit_text_password)
        loginButton = findViewById(R.id.id_button_sign_in)
        textViewError = findViewById(R.id.id_text_view_error)
    }

    private fun login() {
//        if(usernameInput.text.toString() == "admin" && passwordInput.text.toString() == "admin") {
//            val intent = Intent(this, AppActivity::class.java)
//            intent.putExtra("professor", true)
//            intent.putExtra("username", usernameInput.text.toString())
//            usernameInput.setText("")
//            passwordInput.setText("")
//            textViewError.visibility = View.INVISIBLE
//            startActivity(intent)
//        }
//        else {
//            if(usernameInput.text.toString() == "dorel" && passwordInput.text.toString() == "dorelmerelafuratdemere") {
//                val intent = Intent(this, AppActivity::class.java)
//                intent.putExtra("professor", false)
//                intent.putExtra("username", usernameInput.text.toString())
//                usernameInput.setText("")
//                passwordInput.setText("")
//                textViewError.visibility = View.INVISIBLE
//                startActivity(intent)
//            }
//            else {
//                textViewError.visibility = View.VISIBLE
//            }
//        }
        val intent = Intent(this, AppActivity::class.java)
        intent.putExtra("professor", false)
        intent.putExtra("username", usernameInput.text.toString())
        usernameInput.setText("")
        passwordInput.setText("")
        textViewError.visibility = View.INVISIBLE
        startActivity(intent)


    }
}