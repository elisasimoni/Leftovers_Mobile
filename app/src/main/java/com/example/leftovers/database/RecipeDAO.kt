package com.example.leftovers.database


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.leftovers.Recipe

@Dao
interface RecipeDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipe(recipe: Recipe)


}