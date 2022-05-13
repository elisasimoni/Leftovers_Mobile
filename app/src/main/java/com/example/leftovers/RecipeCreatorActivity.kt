package com.example.leftovers

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.leftovers.database.FoodDAO
import com.google.android.material.textfield.TextInputLayout
import org.json.JSONArray


class RecipeCreatorActivity : AppCompatActivity() {
    lateinit var foodDAO: FoodDAO


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.leftovers.R.layout.activity_recipe)

        read_json()



        /*val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "leftovers.db"
        ).allowMainThreadQueries().build()
        foodDAO = db.foodDao()*/




    }


    fun read_json() {
        var food : Food
        var foodList = arrayListOf<Food>()
        try {
            val inputStream = assets.open("Food.json")
            val json = inputStream.bufferedReader().use { it.readText() }
            val jsonarr = JSONArray(json)
            for (i in 0 until jsonarr.length()) {
                val jsonobj = jsonarr.getJSONObject(i)
                food = Food(i,jsonobj.getString("Name"), jsonobj.getString("Name_scientific"), jsonobj.getString("Description"),
                jsonobj.getString("Food_group"), jsonobj.getString("Food_subgroup"))
                foodList.add(food)
                Log.i("Food", foodList.toString())

                //foodDAO.insertFood(food)

            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }




    private fun ingredientsLoad() {

        }

        private fun recipeLoad() {
            TODO()
        }

}


