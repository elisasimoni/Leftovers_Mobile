package com.example.leftovers


import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate


class RecipeDetailCompleteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.default_card_complete)

        val title = intent.getStringExtra("Title")
        val serving = intent.getStringExtra("Serving")
        val kcal = intent.getStringExtra("Calories")
        val ingredients = intent.getStringArrayListExtra("Ingredients")
        val instruction = intent.getStringExtra("Instruction")

        val titleTV = findViewById<TextView>(R.id.title)
        val servingTV = findViewById<TextView>(R.id.servants)
        val kcalTV = findViewById<TextView>(R.id.kcal)
        val instructionTV = findViewById<TextView>(R.id.instruction)

        titleTV.text = title
        servingTV.text = serving
        if(kcal == "0") {
            kcalTV.text = kcal
        }else{
            kcalTV.text = "N/A"
        }
        kcalTV.text = kcal

        instructionTV.text = instruction

        val ingr = findViewById<LinearLayout>(R.id.ingredientsList)



    val pattern1 = Regex("\\D\"")
        val pattern2 = Regex("\\\\")
        val pattern3 = Regex("[0-9]+[,]+[\\s]")
        val pattern4 = Regex("[]]\$")
        val pattern5 = Regex("[<]")
        val pattern6 = Regex("[>]")



        if (ingredients != null) {
            for(i in ingredients){
                val textView = TextView(this)
                val temp:String = pattern1.replace(i.toString(), "")
                val temp2 = pattern2.replace(temp, ", ")
                var temp3 = pattern3.replace(temp2, "1")
                temp3 = pattern4.replace(temp3, "")
                temp3 = pattern5.replace(temp3, "")
                temp3 = pattern6.replace(temp3, "")
                textView.text = temp3

                Log.i("ingredients", i.toString())
                ingr.addView(textView)

            }
        }









    }





}