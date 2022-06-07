package com.example.leftovers

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Log.INFO
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.graphics.red
import androidx.room.Room
import com.example.leftovers.database.UserDAO
import java.util.logging.Level.INFO


class RegisterActivity : AppCompatActivity() {
    var passwordShowing = false
    val STRING_LENGTH = 5
    private val TAG = "RegisterActivity"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        var checkUser = false
        val username = findViewById<EditText>(R.id.edit_username)
        val email = findViewById<EditText>(R.id.edit_email)
        val password = findViewById<EditText>(R.id.edit_password)
        val confirmPassword = findViewById<EditText>(R.id.edit_confirm)
        var usernameCARD = findViewById<CardView>(R.id.edit_username_CARD)
        var emailCARD = findViewById<CardView>(R.id.edit_email_CARD)
        var passwordCARD = findViewById<CardView>(R.id.edit_password_CARD)
        var confirmPasswordCARD = findViewById<CardView>(R.id.edit_confirm_CARD)
        val id = getRandomString(STRING_LENGTH)
        val signUpButton = findViewById<CardView>(R.id.btn_signup)
        var user: User
        lateinit var userDao: UserDAO


        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "leftovers.db"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
        userDao = db.userDao()


        signUpButton.setOnClickListener {
            if (password.text.toString() == confirmPassword.text.toString() && username.text.toString() != "" && email.text.toString() != "" && password.text.toString() != "") {
                user = User(
                    1,
                    username.text.toString(),
                    email.text.toString(),
                    password.text.toString(),
                    confirmPassword.text.toString()
                )
                userDao.insertUser(user)
                checkUser = true
                if (checkUser) {
                    val intent = Intent(this, HomePageActivity::class.java)
                    intent.putExtra("Username", user.fullName)
                    intent.putExtra("EMAIL_PID", user.email)
                    startActivity(intent)

                }


            } else if (username.text.toString() == "" || email.text.toString() == "" || password.text.toString() == "" || confirmPassword.text.toString() == "") {
                if(username.text.toString() == ""){
                    usernameCARD.outlineSpotShadowColor = getColor(R.color.red)
                    usernameCARD.outlineAmbientShadowColor = getColor(R.color.red)
                }else{
                    usernameCARD.outlineSpotShadowColor = getColor(R.color.black)
                    usernameCARD.outlineAmbientShadowColor = getColor(R.color.black)
                }
                if(email.text.toString() == ""){
                    emailCARD.outlineSpotShadowColor = getColor(R.color.red)
                    emailCARD.outlineAmbientShadowColor = getColor(R.color.red)
                }else{
                    emailCARD.outlineSpotShadowColor = getColor(R.color.black)
                    emailCARD.outlineAmbientShadowColor = getColor(R.color.black)
                }

                if(password.text.toString() == ""){
                    passwordCARD.outlineSpotShadowColor = getColor(R.color.red)
                    passwordCARD.outlineAmbientShadowColor = getColor(R.color.red)
                }else{
                    passwordCARD.outlineSpotShadowColor = getColor(R.color.black)
                    passwordCARD.outlineAmbientShadowColor = getColor(R.color.black)
                }

                if(password.text.toString() == ""){
                    confirmPasswordCARD.outlineSpotShadowColor = getColor(R.color.red)
                    confirmPasswordCARD.outlineAmbientShadowColor = getColor(R.color.red)
                }else{
                    confirmPasswordCARD.outlineSpotShadowColor = getColor(R.color.black)
                    confirmPasswordCARD.outlineAmbientShadowColor = getColor(R.color.black)
                }

                password.setText("")
                confirmPassword.setText("")
            }else if(password.text.toString() != confirmPassword.text.toString()){

                if(password.text.toString() != confirmPassword.text.toString()){
                    passwordCARD.outlineSpotShadowColor = getColor(R.color.red)
                    passwordCARD.outlineAmbientShadowColor = getColor(R.color.red)
                    confirmPasswordCARD.outlineSpotShadowColor = getColor(R.color.red)
                    confirmPasswordCARD.outlineAmbientShadowColor = getColor(R.color.red)
                    password.setText("")
                    confirmPassword.setText("")
                }else{
                    passwordCARD.outlineSpotShadowColor = getColor(R.color.black)
                    passwordCARD.outlineAmbientShadowColor = getColor(R.color.black)
                }
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


