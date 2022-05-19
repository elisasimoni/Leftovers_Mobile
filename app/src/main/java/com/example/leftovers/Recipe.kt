package com.example.leftovers
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Recipe(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "Name") val name: String?,
    @ColumnInfo(name = "Source") val source: String?,
    @ColumnInfo(name = "Servings") val serving: String?,
    @ColumnInfo(name = "Comments") val password: String?,
    @ColumnInfo(name = "Calories") val calories: String?,
    @ColumnInfo(name = "Fat") val fat: String?,
    @ColumnInfo(name = "Sat_fat") val satFat: String?,
    @ColumnInfo(name = "Carbs") val carbs: String?,
    @ColumnInfo(name = "Fiber") val fiber: String?,
    @ColumnInfo(name = "Sugar") val sugar: String?,
    @ColumnInfo(name = "Protein") val protein: String?,
    @ColumnInfo(name = "Instruction") val instruction: String?,
    @ColumnInfo(name = "Ingredients") val ingredients: String?,
    @ColumnInfo(name = "Tags") val tags: String?


    )
