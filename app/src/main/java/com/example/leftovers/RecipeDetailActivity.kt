package com.example.leftovers

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.room.Room
import com.example.leftovers.database.RecipeDAO
import org.json.JSONArray


class RecipeDetailActivity : AppCompatActivity() {
    private lateinit var recipeDAO: RecipeDAO
    var tags = arrayListOf<String>()
    var ingredients = arrayListOf<String>()
    var numberOfRecipe : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)


        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "leftovers.db"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
        recipeDAO = db.recipeDao()

        tags  = intent.getStringArrayListExtra("choosenFilter")as ArrayList<String>
        ingredients = intent.getStringArrayListExtra("choosenIngredients")as ArrayList<String>
        if (ingredients != null) {
            Log.i("ingredients", ingredients.toString())
        }
        if (tags != null) {
            Log.i("tags", tags.toString())
        }


        numberOfRecipe = getFilteredRecipe(getFilteredRecipeByIngredients(), getFilteredRecipeByTags()).size
        handleNumberOfRecipe()
        //createCardFromRecipe(getFilteredRecipe(getFilteredRecipeByIngredients(), getFilteredRecipeByTags()))


    }

    @SuppressLint("SetTextI18n")
    private fun handleNumberOfRecipe() {
        val text = findViewById<TextView>(R.id.textViewTitle)

        text.text = "Number of recipes found: $numberOfRecipe"

    }

    
    private fun getFilteredRecipeByTags(): ArrayList<Int> {
        var x = 0
        val idList = arrayListOf<Int>()

        while(x<recipeDAO.getAllRecipes().size){
            val checkTags  = recipeDAO.getAllTags(x) as ArrayList<String>
            for(i in checkTags){
                val checkTags2 = i
                Log.i("checkTags2", checkTags2)
                for(i in tags) {
                    if (checkTags2.contains(i.lowercase())) {
                        Log.i("PROVA", "true")
                        idList.add(x)
                    }
                }

            }
            x++
        }
        Log.i("idList", idList.toString())
        return idList
    }

    private fun getFilteredRecipeByIngredients(): ArrayList<Int> {
        var x = 0
        val idList = arrayListOf<Int>()

        while(x<recipeDAO.getAllRecipes().size){
            val checkIngredients  = recipeDAO.getAllIngredients(x) as ArrayList<String>
            for(i in checkIngredients){
                val checkIngredients2 = i
                Log.i("checkTags2", checkIngredients2)
                for(i in ingredients) {
                    Log.i("ingredients", i)
                    if (checkIngredients2.contains(i.lowercase())) {
                        Log.i("PROVA", "true")
                        idList.add(x)

                    }
                }

            }
            x++
        }
        Log.i("idList", idList.toString())
        return idList
    }

    private fun getFilteredRecipe(idList: ArrayList<Int>, idList2 : ArrayList<Int>): ArrayList<Int> {
        val idList3 = arrayListOf<Int>()
        for(i in idList){
            for(j in idList2){
                if(i == j){
                    idList3.add(i)
                }
            }
        }
        return idList3

    }

    /*private fun createCardFromRecipe(idList: ArrayList<Int>){
        var i = 0
        while (i<=idList.size){
            var recipe = recipeDAO.getRecipeById(idList[i])
            val mainLinearLayout = findViewById<LinearLayout>(R.id.constraintLayout)
            val text = TextView(this)
            text.text = recipe.name
            mainLinearLayout.addView(text)
        }

    }*/

}
