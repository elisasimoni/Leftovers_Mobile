package com.example.leftovers


import android.os.Bundle
import android.transition.TransitionManager
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import com.example.leftovers.database.FoodDAO
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import org.json.JSONArray


class RecipeCreatorActivity : AppCompatActivity() {
    lateinit var foodDAO: FoodDAO


    var foodListRecipe = arrayListOf<String>()
    var filterListRecipe = arrayListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(com.example.leftovers.R.layout.activity_recipe)
        ingredientsLoad()
        recipeLoad()
        read_json()
        catchIngredients()
        catchFilters()

        /*val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "leftovers.db"
        ).allowMainThreadQueries().build()
        foodDAO = db.foodDao()*/


    }


    fun read_json() {
        var food: Food
        var foodList = arrayListOf<Food>()
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
                foodList.add(food)

            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    private fun catchIngredients() {

        var autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.dropdown_menu)
        autoCompleteTextView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, rowId ->
                val selection = parent.getItemAtPosition(position) as String

                autoCompleteTextView.setText("")
                chipFood(selection)
                foodListRecipe.add(selection)
                Log.i("FoodSelected.", selection)

            }
    }

    private fun catchFilters(){
        val chipGroup = findViewById<ChipGroup>(R.id.chipGroup)
        val chip = Chip(this)

        for (i in 0 until chipGroup.childCount) {


                val selection = (chipGroup.getChildAt(i) as Chip).text.toString()


        }

        Log.i("FilterSelected.", filterListRecipe.toString())

    }

    private fun chipFood(food: String) {
        val chipGroup = findViewById<ChipGroup>(R.id.chipGroupFood)
        val chip = Chip(this)
        chip.text = food
        chip.isCloseIconVisible = true
        chip.setOnCloseIconClickListener {
            // Smoothly remove chip from chip group
            TransitionManager.beginDelayedTransition(chipGroup)
            chipGroup.removeView(chip)
            foodListRecipe.remove(food)
            Log.i("FoodList", foodListRecipe.toString())
        }
        chipGroup.addView(chip)
    }

    private fun ingredientsLoad() {
        val foodList = resources.getStringArray(R.array.food_list)

        val editText = findViewById<AutoCompleteTextView>(R.id.dropdown_menu)
        val adapter = ArrayAdapter(
            this, R.layout.list_item_food, R.id.text_view_list_item, foodList
        )
        editText.setAdapter(adapter)

    }

    private fun recipeLoad() {
        var recipe: Recipe
        var recipeList = arrayListOf<Recipe>()
        try {
            val inputStream = assets.open("Recipes.json")
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
                    jsonobj.getString("category"),
                    jsonobj.getString("tags")
                )
                recipeList.add(recipe)


            }


            //foodDAO.insertFood(food)


        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}




