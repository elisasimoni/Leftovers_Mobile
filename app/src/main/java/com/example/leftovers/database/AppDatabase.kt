package com.example.leftovers

import android.content.Context
import androidx.room.*
import com.example.leftovers.database.FoodDAO
import com.example.leftovers.database.RecipeDAO
import com.example.leftovers.database.StarredDAO
import com.example.leftovers.database.UserDAO


@Database(entities = [User::class, Food::class, Recipe::class, Starred::class], version = 7, exportSchema = false)
@TypeConverters(Converters::class)
abstract  class AppDatabase : RoomDatabase(){

    abstract fun userDao(): UserDAO
    abstract fun foodDao(): FoodDAO
    abstract fun recipeDao(): RecipeDAO
    abstract fun starredDao(): StarredDAO

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, "leftovers.db").allowMainThreadQueries().fallbackToDestructiveMigration().build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

}