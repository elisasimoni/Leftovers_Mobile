package com.example.leftovers

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.room.Room
import com.example.leftovers.database.UserDAO


class RegisterActivity : AppCompatActivity() {
    var passwordShowing = false
    val STRING_LENGTH = 5;
    private val TAG = "RegisterActivity"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        var checkUser = false
        val username = findViewById<CardView>(R.id.edit_username)
        val email = findViewById<CardView>(R.id.edit_email)
        val password = findViewById<CardView>(R.id.edit_password)
        val confirmPassword = findViewById<CardView>(R.id.edit_confirm)
        val id = getRandomString(STRING_LENGTH)
        val signUpButton = findViewById<CardView>(R.id.btn_signup)

         lateinit var userDao: UserDAO



        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "leftovers.db"
        ).allowMainThreadQueries().build()
        userDao = db.userDao()


        if(password == confirmPassword) {
            val user = User(1,username.toString(),email.toString(), password.toString(),confirmPassword.toString())
            userDao.insertUser(user)
        }


        signUpButton.setOnClickListener {
            if(password == confirmPassword) {
                val user = User(
                    1,
                    username.toString(),
                    email.toString(),
                    password.toString(),
                    confirmPassword.toString()
                )
                userDao.insertUser(user)
                checkUser = true
            }

            if(checkUser){
                val intent = Intent(this, HomePageActivity::class.java)
                startActivity(intent)

            }

        }


    }

    fun getRandomString(length: Int) : String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }


}

