package com.example.leftovers

//import android.widget.Button




import android.accounts.Account
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.leftovers.barcodeAnalyzer.view.BarcodeScannerActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton


class HomePageActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)
        val username = intent.getStringExtra("Username")
        var userPid = intent.getStringExtra("userPid")

        handleUsername("Welcome, $username")




        val plusRecipeBtn = findViewById<FloatingActionButton>(R.id.plusButton)
        val topAppBar = findViewById<MaterialToolbar>(R.id.topAppBar)


        topAppBar.setOnMenuItemClickListener { item ->
            when (item?.itemId) {
                R.id.favoriteHeartButtonTopBar -> {
                    val intent = Intent(this@HomePageActivity, FavoriteActivity::class.java)
                    intent.putExtra("userPid", userPid)
                    startActivity(intent)
                }

                R.id.profileButtonTopBar -> {
                    val intent = Intent(this@HomePageActivity, ProfileActivity::class.java)
                    intent.putExtra("userPid", userPid)
                    startActivity(intent)
                }

            }
            true
        }




        plusRecipeBtn.setOnClickListener {
            if (userPid != null) {
                goToRecipe(userPid)
                val intent = Intent(this, RecipeCreatorActivity::class.java)
                intent.putExtra("userPid", userPid)
                startActivity(intent)
            }
        }
        val scanBtn = findViewById<FloatingActionButton>(R.id.scanButton)

        scanBtn.setOnClickListener {
            val intent = Intent(this, BarcodeScannerActivity::class.java)
            intent.putExtra("userPid", userPid)
            startActivity(intent)
        }

    }




    private fun handleUsername(username: String) {
        var text = findViewById<TextView>(R.id.usernameWelcome)
        text.text = username

    }



    private fun goToRecipe(userPid:String) {
        val intent = Intent(this, RecipeCreatorActivity::class.java)
        intent.putExtra("EMAIL_PID", userPid)
        startActivity(intent)

    }




}

