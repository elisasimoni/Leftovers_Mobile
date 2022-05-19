package com.example.leftovers

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.leftovers.database.RecipeDAO
import org.json.JSONArray

class RecipeDetailActivity : AppCompatActivity() {
    private lateinit var recipeDAO: RecipeDAO
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)
        handleNumberOfRecipe()
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "leftovers.db"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
        recipeDAO = db.recipeDao()

        recipeJsonLoad()

    }

    private fun handleNumberOfRecipe() {
        var text = findViewById<TextView>(R.id.textViewTitle)
        text.text = "Number of recipes found: 2"

    }



    private fun recipeJsonLoad() {
        var recipe: Recipe
        try {
            val inputStream = applicationContext.assets.open("Recipes.json")
            val json = inputStream.bufferedReader().use { it.readText() }
            val jsonarr = JSONArray(json)
            for (i in 0 until jsonarr.length()) {
                val jsonobj = jsonarr.getJSONObject(i)
                recipe = Recipe(
                    i,
                    jsonobj.getString("name"),
                    jsonobj.getString("source"),
                    jsonobj.getString("servings"),
                    jsonobj.getString("comments"),
                    jsonobj.getString("calories"),
                    jsonobj.getString("fat"),
                    jsonobj.getString("satfat"),
                    jsonobj.getString("carbs"),
                    jsonobj.getString("fiber"),
                    jsonobj.getString("sugar"),
                    jsonobj.getString("protein"),
                    jsonobj.getString("instructions"),
                    jsonobj.getString("ingredients"),
                    jsonobj.getString("tags")
                )

                recipeDAO.insertRecipe(recipe)

            }




        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
