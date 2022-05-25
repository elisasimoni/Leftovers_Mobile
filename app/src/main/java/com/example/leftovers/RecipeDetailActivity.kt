package com.example.leftovers


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.cardview.widget.CardView
import androidx.room.Room
import com.example.leftovers.database.RecipeDAO
import com.example.leftovers.database.StarredDAO


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

        getRecipeByTagsAndIngredients(tags,ingredients)

        handleNumberOfRecipe()


    }

    @SuppressLint("SetTextI18n")
    private fun handleNumberOfRecipe() {
        val text = findViewById<TextView>(R.id.textViewTitle)

        text.text = "Number of recipes found: $numberOfRecipe"

    }
    private fun handleFavoriteRecipe(Recipe: Recipe) {
        val favorite = findViewById<ImageButton>(R.id.favoriteHeart)
        val userPid = intent.getStringExtra("EMAIL_PID")
        favorite.setOnClickListener {
            val recipeFavorite = Recipe
            val starred = Starred(0,userPid, userPid)
            favorite.setBackgroundResource(R.drawable.ic_favorite_red)
            starredDAO.insertStarred(starred)




            Log.d("RecipeDetailActivity", "Recipe added to favorites")
        }
    }

    @SuppressLint("WrongViewCast")
    private fun getRecipeByTagsAndIngredients(tags: ArrayList<String>, ingredients: ArrayList<String>) {
        val cards = findViewById<LinearLayoutCompat>(R.id.cards)
        var background = findViewById<RelativeLayout>(R.id.relativeBackground)
        var recipe = recipeDAO.getRecipeByTags(tags,ingredients)
        val bookmarks = findViewById<ImageView>(R.id.bookmarks)
        numberOfRecipe = recipe.size
        for(i in recipe){
            i.name?.let { Log.d("recipe", it) }
            val newCard = CardView(this)
            layoutInflater.inflate(R.layout.default_card, newCard);


            if(tags.contains("soup")){
                background.background = getDrawable(R.drawable.soup)
            }else if(tags.contains("meat")) {
                background.background = getDrawable(R.drawable.meat)
            }else if(tags.contains("dessert")) {
                background.background = getDrawable(R.drawable.dessert)
            }else if(tags.contains("vegetarian") or tags.contains("vegan") or tags.contains("salad")) {
                background.background = getDrawable(R.drawable.vegetarian)
            }else if(tags.contains("pizza")) {
                background.background = getDrawable(R.drawable.pizza)
            }else if(tags.contains("pasta")) {
                background.background = getDrawable(R.drawable.soup)
            }else if(tags.contains("fish") or tags.contains("shrimp")) {
                background.background = getDrawable(R.drawable.fish)
            }else if(tags.contains("appetizer")) {
                background.background = getDrawable(R.drawable.appetizer)
            }else if(tags.contains("snacks") or tags.contains("sides")) {
                background.background = getDrawable(R.drawable.snack)
            }else if(tags.contains("breakfast")) {
                background.background = getDrawable(R.drawable.breakfast)
            }else if(tags.contains("sauce")) {
                background.background = getDrawable(R.drawable.sauce)
            }
            val t: TextView = newCard.findViewById(R.id.title)
            val s: TextView = newCard.findViewById(R.id.servants)
            val kcal: TextView = newCard.findViewById(R.id.kcal)

            t.text = "${i.name}"
            s.text ="Servings: ${i.serving}"
            kcal.text ="KCal: ${i.calories}"



        /*var servings  = i.serving?.toInt()
                      if (servings != null) {
                          while(servings>0){

                              numberServings.setImageResource(R.drawable.ic_username)




                          }
                      }*/
            cards.addView(newCard)
            }





        }
    }



    









