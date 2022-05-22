package com.example.leftovers.database

import androidx.room.*
import com.example.leftovers.Food



@Dao
interface FoodDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFood(food: Food)





}



