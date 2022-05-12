package com.example.leftovers


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.room.Room
import com.example.leftovers.database.UserDAO

class LoginActivity : AppCompatActivity() {

    private var passwordShowing = false
    lateinit var userDao: UserDAO
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

       val emailET = findViewById<CardView>(R.id.edit_email)
       val passwordET = findViewById<CardView>(R.id.edit_password)
        val signInBtn = findViewById<CardView>(R.id.btn_login)
        val signUpBtn = findViewById<TextView>(R.id.signdont)
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "leftovers.db"
        ).allowMainThreadQueries().build()
        userDao = db.userDao()
        var user:User

        signInBtn.setOnClickListener{

            user = userDao.catchUser(emailET.toString(),passwordET.toString())

                val intent = Intent(this, HomePageActivity::class.java)
                intent.putExtra("Username",user.fullName)
                startActivity(intent)

        }

        signUpBtn.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)

        }


    }
}
