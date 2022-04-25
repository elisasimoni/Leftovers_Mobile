package com.example.leftovers

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.test.core.app.ApplicationProvider
import com.example.leftovers.Database.FoodDAO
import com.example.leftovers.Database.RecipeDAO


@Database(entities = [User::class], version = 1)
abstract  class AppDatabase : RoomDatabase(){

    abstract fun userDao(): UserDAO
    abstract fun foodDao(): FoodDAO
    abstract fun recipeDao(): RecipeDAO

    var db = Room.databaseBuilder(
        ApplicationProvider.getApplicationContext<Context>(),
        AppDatabase::class.java, "Leftovers"
    ).build()

}