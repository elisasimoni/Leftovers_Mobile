package com.example.leftovers
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class SpecificRecipe(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "Ingredients") val ingredients: String?,
    @ColumnInfo(name = "Tags") val tags: String?


)