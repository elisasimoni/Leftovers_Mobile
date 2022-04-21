package com.example.leftovers

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class HomePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        val plusRecipeBtn = findViewById<Button>(R.id.plusButton)

        plusRecipeBtn.setOnClickListener(){
            goToRecipe()
        }

/*
        bottomAppBar.setNavigationOnClickListener {
            // Handle navigation icon press
        }


        bottomAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.search -> {
                    // Handle search icon press
                    true
                }
                R.id.more -> {
                    // Handle more item (inside overflow menu) press
                    true
                }
                else -> false
            }
        }*/
    }

    private fun goToRecipe(){
        val intent = Intent(this,RecipeCreatorActivity::class.java)
        startActivity(intent)

    }
}