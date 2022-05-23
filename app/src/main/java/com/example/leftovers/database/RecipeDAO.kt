package com.example.leftovers.database


import androidx.lifecycle.LiveData
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

    @Query("SELECT * FROM Recipe WHERE Tags LIKE :tags AND Ingredients LIKE :ingredients")
    fun getRecipeByTags(tags: ArrayList<String>, ingredients:ArrayList<String>): List<Recipe>






}