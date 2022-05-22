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

    @Query("SELECT * FROM Recipe WHERE Tags = :tags")
    fun getRecipeByTags(tags: String?): Recipe

    @Query("SELECT Tags FROM Recipe WHERE ID = :id")
    fun getAllTags(id: Int): List<String>

    @Query("SELECT Ingredients FROM Recipe WHERE ID = :id")
    fun getAllIngredients(id: Int): List<String>

    @Query("SELECT * FROM Recipe WHERE ID = :id")
    fun getRecipeById(id: Int): Recipe



}