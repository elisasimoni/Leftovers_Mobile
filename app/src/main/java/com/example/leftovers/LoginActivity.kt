package com.example.leftovers


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class LoginActivity : AppCompatActivity() {

    private var passwordShowing = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

       //val usernameET = findViewById<EditText>(R.id.usernameET)
       val passwordET = findViewById<EditText>(R.id.passwordET)
        val signUnBtn = findViewById<TextView>(R.id.signUpBtn)
        //val passwordIcon = findViewById<ImageView>(R.id.password_icon)
        /*passwordIcon.setOnClickListener {
           // passwordShowing = !passwordShowing
            if(passwordShowing){
               passwordShowing = false
               passwordET.inputType=(InputType.TYPE_CLASS_TEXT) ; (InputType.TYPE_TEXT_VARIATION_PASSWORD)
               passwordIcon.setImageResource(R.drawable.password_show)
           }
            else{
               passwordShowing = true
               passwordET.inputType=(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)
               passwordIcon.setImageResource(R.drawable.password_hide)
           }
            passwordET.setSelection(passwordET.text.length)
        }*/

        signUnBtn.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)

        }
    }
}
