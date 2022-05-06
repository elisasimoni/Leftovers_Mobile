package com.example.leftovers

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView


class RegisterActivity : AppCompatActivity() {
    var passwordShowing = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val username = findViewById<CardView>(R.id.edit_username)
        val email = findViewById<CardView>(R.id.edit_email)
        val password = findViewById<CardView>(R.id.edit_password)
        val confirmPassword = findViewById<CardView>(R.id.edit_confirm)

        val signUpButton = findViewById<CardView>(R.id.btn_signup)

        if(password == confirmPassword) {
            //insert(1, username, email, password)
        }





    }


}

