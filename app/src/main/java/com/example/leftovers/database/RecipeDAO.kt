package com.example.leftovers.database


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.leftovers.Recipe

@Dao
interface RecipeDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipe(recipe: Recipe)

    @Query("SELECT * FROM Recipe")
    fun getAllRecipes(): List<Recipe>

    @Query("SELECT * FROM Recipe WHERE Tags = :tags AND Ingredients = :food")
    fun getRecipeByFoodAndTags(food:String, tags:String): Recipe


}