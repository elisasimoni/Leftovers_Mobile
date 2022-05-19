package com.example.leftovers


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.room.Room
import com.example.leftovers.database.UserDAO

class LoginActivity : AppCompatActivity() {

    lateinit var userDao: UserDAO
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val wrongText = findViewById<TextView>(R.id.wrongEPText)
       val emailET = findViewById<EditText>(R.id.edit_email)
       val passwordET = findViewById<EditText>(R.id.edit_password)
        val emailETCARD = findViewById<CardView>(R.id.edit_email_CARD)
        val passwordETCARD = findViewById<CardView>(R.id.edit_password_CARD)
        val signInBtn = findViewById<CardView>(R.id.btn_login)
        val signUpBtn = findViewById<TextView>(R.id.signdont)
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "leftovers.db"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
        userDao = db.userDao()
        var user:User
        var existUser:Boolean


        signInBtn.setOnClickListener{

            existUser = userDao.existUser(emailET.text.toString(),passwordET.text.toString())
            user = userDao.catchUser(emailET.text.toString(),passwordET.text.toString())
            if(existUser){
                wrongText.visibility = View.INVISIBLE

                val intent = Intent(this, HomePageActivity::class.java)
                intent.putExtra("Username", user.fullName)
                startActivity(intent)
            }else{

                if(emailET.text.toString() == "" ){
                    emailETCARD.outlineSpotShadowColor = getColor(R.color.red)
                    emailETCARD.outlineAmbientShadowColor = getColor(R.color.red)
                }else{
                    emailETCARD.outlineSpotShadowColor = getColor(R.color.black)
                    emailETCARD.outlineAmbientShadowColor = getColor(R.color.black)
                }

                if(passwordET.text.toString() == ""){
                    passwordETCARD.outlineSpotShadowColor = getColor(R.color.red)
                    passwordETCARD.outlineAmbientShadowColor = getColor(R.color.red)
                }else{
                    passwordETCARD.outlineSpotShadowColor = getColor(R.color.black)
                    passwordETCARD.outlineAmbientShadowColor = getColor(R.color.black)
                }
                if(!existUser){
                    passwordETCARD.outlineSpotShadowColor = getColor(R.color.red)
                    passwordETCARD.outlineAmbientShadowColor = getColor(R.color.red)
                    emailETCARD.outlineSpotShadowColor = getColor(R.color.red)
                    emailETCARD.outlineAmbientShadowColor = getColor(R.color.red)
                }else{
                    emailETCARD.outlineSpotShadowColor = getColor(R.color.black)
                    emailETCARD.outlineAmbientShadowColor = getColor(R.color.black)
                    passwordETCARD.outlineSpotShadowColor = getColor(R.color.black)
                    passwordETCARD.outlineAmbientShadowColor = getColor(R.color.black)
                }
                wrongText.visibility = View.VISIBLE
            }

        }

        signUpBtn.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)

        }


    }
}
