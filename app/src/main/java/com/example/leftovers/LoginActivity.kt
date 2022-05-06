package com.example.leftovers


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.cardview.widget.CardView

class LoginActivity : AppCompatActivity() {

    private var passwordShowing = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

       val usernameET = findViewById<CardView>(R.id.edit_email)
       val passwordET = findViewById<CardView>(R.id.edit_password)
        val signInBtn = findViewById<CardView>(R.id.btn_login)
        val signUpBtn = findViewById<TextView>(R.id.signdont)

        signInBtn.setOnClickListener{
            val intent = Intent(this, HomePageActivity::class.java)
            startActivity(intent)
        }

        signUpBtn.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)

        }


    }
}
