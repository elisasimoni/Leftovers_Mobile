package com.example.leftovers

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.room.Room
import com.example.leftovers.database.RecipeDAO
import com.example.leftovers.database.StarredDAO

class FavoriteActivity : AppCompatActivity() {
    private lateinit var recipeDAO: RecipeDAO
    private lateinit var starredDAO: StarredDAO
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_recipe)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "leftovers.db"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
        recipeDAO = db.recipeDao()
        starredDAO = db.starredDao()

        }
}
/*
private fun showFavorite(){
   /*
   val favoriteRecipes = getFavoriteRecipes()

    if (favoriteRecipes.isEmpty()){
        TODO()//showEmptyFavorite()
    } else {
        TODO()//showFavoriteRecipes(favoriteRecipes)
    }

    */
}
private fun showFavoriteRecipes(favoriteRecipes: List<Recipe>){
    TODO()//showRecipes(favoriteRecipes)
}
private fun showEmptyFavorite(){
    TODO()//showEmptyFavorite()
}
private fun getFavoriteRecipes(uid:String) : Recipe{
    var recipe = recipeDao.getRecipeById(uid)
    return recipe
}*/
