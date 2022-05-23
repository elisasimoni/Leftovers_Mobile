package com.example.leftovers



import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.cardview.widget.CardView
import androidx.room.Room
import com.example.leftovers.database.RecipeDAO


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

        getRecipeByTagsAndIngredients(tags,ingredients)

        handleNumberOfRecipe()


    }

    @SuppressLint("SetTextI18n")
    private fun handleNumberOfRecipe() {
        val text = findViewById<TextView>(R.id.textViewTitle)

        text.text = "Number of recipes found: $numberOfRecipe"

    }

    private fun getRecipeByTagsAndIngredients(tags: ArrayList<String>, ingredients: ArrayList<String>) {
        var cards = findViewById<LinearLayoutCompat>(R.id.cards);
        var recipe = recipeDAO.getRecipeByTags(tags,ingredients)
        numberOfRecipe = recipe.size
        for(i in recipe){
            i.name?.let { Log.d("recipe", it) }
            val newCard = CardView(this)
            getLayoutInflater().inflate(R.layout.default_card, newCard);

            val t: TextView = newCard.findViewById(R.id.name)


            t.text = "${i.name}"



            cards.addView(newCard)
        }
    }

    

}






