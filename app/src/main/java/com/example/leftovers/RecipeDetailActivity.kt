package com.example.leftovers


import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.room.Room
import com.example.leftovers.database.RecipeDAO
import com.example.leftovers.database.StarredDAO
import me.zhanghai.android.materialratingbar.MaterialRatingBar



class RecipeDetailActivity : AppCompatActivity() {
    private lateinit var recipeDAO: RecipeDAO
    private lateinit var starredDAO: StarredDAO
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
        starredDAO = db.starredDao()
        tags  = intent.getStringArrayListExtra("choosenFilter")as ArrayList<String>
        ingredients = intent.getStringArrayListExtra("choosenIngredients")as ArrayList<String>
        val returnBack = findViewById<ImageView>(R.id.returnBack)
        returnBack.setOnClickListener {
            val intent = Intent(this, RecipeCreatorActivity::class.java)
            startActivity(intent)
        }


        getRecipeByTagsAndIngredients(tags,ingredients)

        handleNumberOfRecipe()


    }

    @SuppressLint("SetTextI18n")
    private fun handleNumberOfRecipe() {
        val text = findViewById<TextView>(R.id.textViewTitle)

        text.text = "Number of recipes found: $numberOfRecipe"

    }


    private fun randomRating() : Float {
        val list = arrayListOf(0.0f,0.5f,1.0f,1.5f,2.0f,2.5f,3.0f,3.5f,4.0f,4.5f,5.0f)
            //list of possible ratings
        return  list.random()
    }

    @SuppressLint("WrongViewCast")
    private fun getRecipeByTagsAndIngredients(tags: ArrayList<String>, ingredients: ArrayList<String>) {
        val cards = findViewById<LinearLayoutCompat>(R.id.cards)
        var recipe = recipeDAO.getRecipeByTags(tags,ingredients)

        numberOfRecipe = recipe.size
        for(i in recipe){
            val newCard = CardView(this)
            newCard.background = ContextCompat.getDrawable(this, R.drawable.trasparent2)

            layoutInflater.inflate(R.layout.default_card, newCard)
            val back: ImageView = newCard.findViewById(R.id.relativeBackground)
            val star: MaterialRatingBar = newCard.findViewById(R.id.ratingBar)
            star.rating = randomRating()
            for (j in i.tags!!){

                if(j.contains("soup")){
                    back.background = getDrawable(R.drawable.soup)
                }else if(j.contains("meat")) {
                    back.background = getDrawable(R.drawable.meat)
                }else if(j.contains("dessert")) {
                    back.background = getDrawable(R.drawable.dessert)
                }else if(j.contains("vegetarian") or j.contains("vegan") or j.contains("salad")) {
                    back.background = getDrawable(R.drawable.vegetarian)
                }else if(j.contains("pizza")) {
                    back.background = getDrawable(R.drawable.pizza)
                }else if(j.contains("pasta")) {
                    back.background = getDrawable(R.drawable.pasta)
                }else if(j.contains("fish") or j.contains("shrimp") or j.contains("crab") or j.contains("squid") or j.contains("lobster")) {
                    back.background = getDrawable(R.drawable.fish)
                }else if(j.contains("appetizer")) {
                    back.background = getDrawable(R.drawable.appetizer)
                }else if(j.contains("snack") or j.contains("sides")) {
                    back.background = getDrawable(R.drawable.snack)
                }else if(j.contains("breakfast")) {
                    back.background = getDrawable(R.drawable.breakfast)
                }else if(j.contains("sauce")) {
                    back.background = getDrawable(R.drawable.sauce)
                }else{
                    back.background = getDrawable(R.drawable.generic_food)
                }
            }

            val t: TextView = newCard.findViewById(R.id.title)
            val s: TextView = newCard.findViewById(R.id.servants)
            val kcal: TextView = newCard.findViewById(R.id.kcal)


            t.text = "${i.name}"
            s.text ="Servings: ${i.serving}"
            if(i.calories!= "0") {
                kcal.text = "KCal: ${i.calories}"
            }else{
                kcal.text = "KCal: //"
            }
            var buttonState : Int = 0
            val heart = newCard.findViewById<ImageButton>(R.id.favoriteHeart)

            heart.setOnClickListener {
                val userPid = intent.getStringExtra("EMAIL_PID")

                if(buttonState == 0){
                    heart.setBackgroundResource(R.drawable.ic_favorite_red)
                    var starred = Starred(i.uid,userPid,i.uid)
                    starredDAO.insertStarred(starred)
                    buttonState = 1
                }else{
                    heart.setBackgroundResource(R.drawable.ic_heart)
                    var starred = Starred(i.uid,userPid,i.uid)
                    starredDAO.deleteStarred(starred)
                    Log.i("DELETE",starred.toString())
                    buttonState = 0
                }



            }

            cards.addView(newCard)
            }





        }
    }



    









