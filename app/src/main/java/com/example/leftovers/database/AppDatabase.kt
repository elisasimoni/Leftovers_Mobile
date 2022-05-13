package com.example.leftovers

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.test.core.app.ApplicationProvider
import com.example.leftovers.database.FoodDAO
import com.example.leftovers.database.RecipeDAO
import com.example.leftovers.database.UserDAO


@Database(entities = [User::class]   , version = 1)
abstract  class AppDatabase : RoomDatabase(){

    abstract fun userDao(): UserDAO
    abstract fun foodDao(): FoodDAO
    abstract fun recipeDao(): RecipeDAO

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, "leftovers.db").allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

}