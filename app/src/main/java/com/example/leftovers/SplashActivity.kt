package com.example.leftovers

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.room.Room
import com.example.leftovers.database.AppDatabase
import com.example.leftovers.database.FoodDAO
import com.example.leftovers.database.RecipeDAO
import org.json.JSONArray


@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private lateinit var recipeDAO: RecipeDAO
    private lateinit var foodDAO: FoodDAO
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
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
                val ingredients = jsonobj.getString("ingredients")
                val ingredientsArr = ingredients.split(",")

                val ingredientsList = ArrayList<String>()
                for (element in ingredientsArr) {

                    ingredientsList.add(element)

                }




                val tags = jsonobj.getString("tags")
                val tagsArr = tags.split(",")
                val tagsList = ArrayList<String>()
                for (element in tagsArr) {
                    tagsList.add(element)
                }






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
                    ingredientsList,
                    tagsList
                )

                recipeDAO.insertRecipe(recipe)

            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}