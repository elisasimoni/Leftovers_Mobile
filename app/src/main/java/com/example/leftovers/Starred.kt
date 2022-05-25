package com.example.leftovers

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Starred(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "Email") val email: String?,
    @ColumnInfo(name = "RecipeID") val recipeID: String?,
)
