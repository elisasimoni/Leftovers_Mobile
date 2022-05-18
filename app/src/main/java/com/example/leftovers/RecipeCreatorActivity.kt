package com.example.leftovers


import android.os.Bundle
import android.transition.TransitionManager
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import com.example.leftovers.database.FoodDAO
import com.example.leftovers.database.RecipeDAO
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import org.json.JSONArray


class RecipeCreatorActivity : AppCompatActivity() {
    lateinit var foodDAO: FoodDAO
    lateinit var recipeDao: RecipeDAO
    var foodListRecipe = arrayListOf<String>()
    private var filterListRecipe = arrayListOf<String>()
    var filter = arrayListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)
        ingredientsLoad()
        recipeLoad()
        read_json()
        catchIngredients()
        catchFilters("Starter")
        catchFilters("Main")
        catchFilters("Second")
        catchFilters("Dessert")
        catchFilters("Drink")
        catchFilters("Snack")
        catchFilters("Breakfast")
        catchFilters("Vegetarian")
        catchFilters("Vegan")
        catchFilters("Gluten Free")
        catchFilters("Lactose Free")


        /*val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "leftovers.db"
        ).allowMainThreadQueries().build()
        foodDAO = db.foodDao()*/


    }


    private fun read_json() {
        var food: Food
        val foodList = arrayListOf<Food>()
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

        val autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.dropdown_menu)
        autoCompleteTextView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, rowId ->
                val selection = parent.getItemAtPosition(position) as String

                autoCompleteTextView.setText("")
                chipFood(selection)
                foodListRecipe.add(selection)
                Log.i("FoodSelected.", selection)

            }
    }

    private fun catchFilters(filter:String){
        val chipGroup = findViewById<ChipGroup>(R.id.chipGroupFilter)
        val chip = Chip(this)
        chip.text = filter
        chip.isCheckable = true
        chip.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                filterListRecipe.add(filter)
            }else{
                filterListRecipe.remove(filter)
            }

            Log.i("FilterSelected.", filterListRecipe.toString())
        }
        chipGroup.addView(chip)

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
        val recipeList = arrayListOf<Recipe>()
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




