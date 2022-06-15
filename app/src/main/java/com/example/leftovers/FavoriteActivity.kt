package com.example.leftovers

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.room.Room
import com.example.leftovers.database.AppDatabase
import com.example.leftovers.database.RecipeDAO
import com.example.leftovers.database.StarredDAO
import com.google.android.material.appbar.MaterialToolbar


class FavoriteActivity : AppCompatActivity() {
    private lateinit var recipeDAO: RecipeDAO
    private lateinit var starredDAO: StarredDAO
    var favoriteRecipesList : ArrayList<Recipe> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_recipe)
        val topAppBar = findViewById<MaterialToolbar>(R.id.topAppBar)
        val userPid = intent.getStringExtra("userPid")

        topAppBar.setNavigationOnClickListener {
            val intent = Intent(this@FavoriteActivity, HomePageActivity::class.java)
            intent.putExtra("userPid", userPid)
            startActivity(intent)
        }



        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "leftovers.db"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
        recipeDAO = db.recipeDao()
        starredDAO = db.starredDao()

        if (userPid != null) {
            Log.i("PID", userPid)
        }else{
            Log.i("PID", "null")
        }


        if (userPid != null) {
            getFavoriteRecipes(userPid)
            showFavorite()?.let { showFavoriteRecipes(it) }
        }


    }



    private fun showFavorite() : ArrayList<Recipe>? {

        return if (favoriteRecipesList.isEmpty()) {
            showEmptyFavorite()
            null
        } else {
            favoriteRecipesList
        }



    }
    @SuppressLint("SetTextI18n")
    private fun showFavoriteRecipes(favoriteRecipes: ArrayList<Recipe>){
        val cards = findViewById<LinearLayout>(R.id.cards)
        val intent = Intent(this, RecipeDetailCompleteActivity::class.java)
        for(i in favoriteRecipes){
            val newCard = CardView(this)
            newCard.background = ContextCompat.getDrawable(this, R.drawable.trasparent2)
            layoutInflater.inflate(R.layout.defaullt_card_favorite, newCard)

            val t: TextView = newCard.findViewById(R.id.title)
            val s: TextView = newCard.findViewById(R.id.servants)
            val kcal: TextView = newCard.findViewById(R.id.kcal)
            val topAppBar = findViewById<MaterialToolbar>(R.id.topAppBar)
            val userPid = intent.getStringExtra("userPid")
            topAppBar.setNavigationOnClickListener {
                val intent = Intent(this@FavoriteActivity, HomePageActivity::class.java)
                intent.putExtra("userPid", userPid)
                startActivity(intent)
            }
            topAppBar.setOnMenuItemClickListener { item ->
                when (item?.itemId) {
                    R.id.favoriteHeartButtonTopBar -> {
                        val intent = Intent(this, FavoriteActivity::class.java)
                        intent.putExtra("userPid", userPid)
                        startActivity(intent)
                    }

                    R.id.profileButtonTopBar -> {
                        val intent = Intent(this, ProfileActivity::class.java)
                        intent.putExtra("userPid", userPid)
                        startActivity(intent)
                    }


                }
                true
            }
            t.text = "${i.name}"
            s.text ="Servings: ${i.serving}"
            if(i.calories!= "0") {
                kcal.text = "KCal: ${i.calories}"
            }else{
                kcal.text = "KCal: //"
            }

            val heart = newCard.findViewById<ImageButton>(R.id.favoriteHeart)
            heart.setBackgroundResource(R.drawable.ic_heart)

            heart.setOnClickListener {
                Dialog(this)


                val starred = Starred(i.uid,userPid,i.uid)
                val alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(this)

                alertDialogBuilder.setMessage("DO YOU WANT TO REMOVE THIS RECIPE?")
                alertDialogBuilder.setNegativeButton("YES",
                    DialogInterface.OnClickListener { dialogInterface, i ->
                        Log.d("REMOVE FAVORITE", "Ok btn pressed")
                        starredDAO.deleteStarred(starred)
                        Log.i("DELETE", starred.toString())

                        cards.removeView(newCard)
                    })
                alertDialogBuilder.setPositiveButton("NO",
                    DialogInterface.OnClickListener { dialogInterface, i ->
                        Log.d("REMOVE FAVORITE", "Cancel btn pressed")
                        onDestroy()

                    })

                val alertDialog: AlertDialog = alertDialogBuilder.create()
                alertDialog.show()





            }

            newCard.setOnClickListener {

                intent.putExtra("Title",i.name)
                intent.putExtra("Serving",i.serving)
                intent.putExtra("Calories",i.calories)
                intent.putExtra("Ingredients",i.ingredients)

                intent.putExtra("Instruction",i.instruction)


                startActivity(intent)
            }

            cards.addView(newCard)

        }


    }


    @SuppressLint("SetTextI18n")
    private fun showEmptyFavorite(){
        val text = findViewById<TextView>(R.id.noRecipeFound)
        text.text = "NO FAVORITE RECIPE YET :("
        text.visibility
    }


    private fun getFavoriteRecipes(userPid : String) : ArrayList<Recipe>{
        val recipeCheck = starredDAO.getStarred(userPid) as ArrayList<Int>
        for(fr in recipeCheck){
            val recipe = recipeDAO.getRecipeById(fr)
            favoriteRecipesList.add(recipe)
        }
        return favoriteRecipesList
    }


}


