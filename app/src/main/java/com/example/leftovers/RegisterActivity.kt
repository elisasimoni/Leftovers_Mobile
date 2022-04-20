package com.example.leftovers

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton


class RegisterActivity : AppCompatActivity() {
    var passwordShowing = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val email = findViewById<EditText>(R.id.emailET)
        val mobile = findViewById<EditText>(R.id.mobileET)
        val password = findViewById<EditText>(R.id.passwordET)
        val confirmPassword = findViewById<EditText>(R.id.confirm_passwordET)
        //val passwordIcon= findViewById<ImageView>(R.id.password_icon)
        //val confirmPasswordIcon= findViewById<ImageView>(R.id.confirm_password_icon)

        val signUpButton = findViewById<AppCompatButton>(R.id.sign_up_button)
        //val signInButton = findViewById<AppCompatButton>(R.id.sign_in_button)
/*
        passwordIcon.setOnClickListener {

            if(passwordShowing){
                passwordShowing = false
                password.inputType=(InputType.TYPE_CLASS_TEXT) ; (InputType.TYPE_TEXT_VARIATION_PASSWORD)
                passwordIcon.setImageResource(R.drawable.password_show)
            }
            else{
                passwordShowing = true
                password.inputType=(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)
                passwordIcon.setImageResource(R.drawable.password_hide)
            }
            password.setSelection(password.text.length)
        }
        confirmPasswordIcon.setOnClickListener {

            if(passwordShowing){
                passwordShowing = false
                confirmPassword.inputType=(InputType.TYPE_CLASS_TEXT) ; (InputType.TYPE_TEXT_VARIATION_PASSWORD)
                confirmPasswordIcon.setImageResource(R.drawable.password_show)
            }
            else{
                passwordShowing = true
                confirmPassword.inputType=(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)
                confirmPasswordIcon.setImageResource(R.drawable.password_hide)
            }
            confirmPassword.setSelection(confirmPassword.text.length)
        }*/
        signUpButton.setOnClickListener {


            val intent = Intent(this, OTPVerification::class.java).also {
                //OTP Verification
                intent.putExtra("mobile", mobile.text.toString())
                intent.putExtra("email", email.text.toString())
                startActivity(intent)

            }


        }
       /* signInButton.setOnClickListener {
            finish()
        }*/
    }
}

