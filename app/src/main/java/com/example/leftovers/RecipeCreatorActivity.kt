package com.example.leftovers


import android.content.Intent
import android.os.Bundle
import android.transition.TransitionManager
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.leftovers.database.FoodDAO
import com.example.leftovers.database.RecipeDAO
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import org.json.JSONArray


class RecipeCreatorActivity : AppCompatActivity() {
    lateinit var foodDAO: FoodDAO
    val a = "%"
    var foodListRecipe = arrayListOf<String>()
    private var filterListRecipe = arrayListOf<String>()
    var filter = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "leftovers.db"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
        foodDAO = db.foodDao()


        ingredientsLoad()
        catchIngredients()
        catchFilters("Main")
        catchFilters("Soup")
        catchFilters("Dessert")
        catchFilters("Sides")
        catchFilters("Snacks")
        catchFilters("Breakfast")
        catchFilters("Vegetarian")
        catchFilters("Vegan")
        catchFilters("Appetizer")
        catchFilters("Sauce")

        val findBtn = findViewById<Button>(R.id.findRecipe)
        val userPid = intent.getStringExtra("EMAIL_PID")
        findBtn.setOnClickListener {
            val intent = Intent(this, RecipeDetailActivity::class.java)
            intent.putExtra("choosenFilter", filterListRecipe)
            intent.putExtra("choosenIngredients", foodListRecipe)
            intent.putExtra("EMAIL_PID", userPid)
            startActivity(intent)
        }

    }


    private fun catchIngredients() {

        val autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.dropdown_menu)
        autoCompleteTextView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, rowId ->
                val selection = parent.getItemAtPosition(position) as String

                autoCompleteTextView.setText("")

                if(foodListRecipe.contains(a.plus(selection).plus(a))){
                    //do nothing
                }else{
                    chipFood(selection)
                    foodListRecipe.add(a.plus(selection).plus(a))
                    Log.i("FoodSelected.", selection)
                }

            }
    }

    private fun catchFilters(filter:String){
        val chipGroup = findViewById<ChipGroup>(R.id.chipGroupFilter)
        val chip = Chip(this)
        chip.text = filter
        chip.isCheckable = true
        chip.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                filterListRecipe.add(a.plus(filter).plus(a))
            }else{
                filterListRecipe.remove(a.plus(filter).plus(a))
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
            foodListRecipe.remove(a.plus(food).plus(a))
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


}




