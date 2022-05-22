package com.example.leftovers

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.leftovers.database.FoodDAO
import com.example.leftovers.database.RecipeDAO
import org.json.JSONArray


class SplashActivity : AppCompatActivity() {
    private lateinit var recipeDAO: RecipeDAO
    lateinit var foodDAO: FoodDAO
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, LoginActivity::class.java))
        finish()

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "leftovers.db"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
        recipeDAO = db.recipeDao()
        foodDAO = db.foodDao()

        recipeJsonLoad()
        ingredientsJsonLoad()


    }

    private fun ingredientsJsonLoad() {
        var food: Food
        try {
            val inputStream = assets.open("Food.json")
            val json = inputStream.bufferedReader().use { it.readText() }
            val jsonarr = JSONArray(json)
            for (i in 0 until jsonarr.length()) {
                val jsonobj = jsonarr.getJSONObject(i)
                food = Food(
                    i,
                    jsonobj.getString("name"),
                    jsonobj.getString("name_scientific"),
                    jsonobj.getString("description"),
                    jsonobj.getString("food_group"),
                    jsonobj.getString("food_subgroup")
                )
                foodDAO.insertFood(food)

            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
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