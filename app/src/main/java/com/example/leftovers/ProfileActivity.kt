package com.example.leftovers

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.cardview.widget.CardView
import androidx.room.Room
import com.example.leftovers.database.UserDAO

class ProfileActivity : AppCompatActivity() {

    lateinit var userDao: UserDAO
    var user : User? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val pid = intent.getStringExtra("userPid")
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "leftovers.db"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
        userDao = db.userDao()
        user = pid?.let { userDao.getUser(it) }
        val usernametx = findViewById<TextView>(R.id.username)
        usernametx.text = user?.fullName ?: "No Name"
        val emailtx = findViewById<TextView>(R.id.email)
        emailtx.text = user?.email ?: "No Email"
        val passwordtx = findViewById<TextView>(R.id.password)
        var pass = user?.password ?: "No Password"
        pass = "*".repeat(pass.length)
        passwordtx.text = pass
        val eye = findViewById<ImageView>(R.id.eye)
        eye.setOnClickListener {
            var password = user?.password ?: "No Password"
            if(pass == "*".repeat(password.length)){
                pass = password
                passwordtx.text = pass
            }
            else{
                pass = "*".repeat(password.length)
                passwordtx.text = pass
            }

            }

        val exitButton = findViewById<ImageButton>(R.id.exitButton)

        exitButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }


    }
}