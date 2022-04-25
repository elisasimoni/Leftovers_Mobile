package com.example.leftovers
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Recipe(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "Name") val name: String?,
    @ColumnInfo(name = "Source") val source: String?,
    @ColumnInfo(name = "Servings") val serving: Int?,
    @ColumnInfo(name = "Comments") val password: String?,
    @ColumnInfo(name = "Calories") val calories: Int?,
    @ColumnInfo(name = "fat") val fat: Int?,
    @ColumnInfo(name = "Sat_fat") val satFat: Int?,
    @ColumnInfo(name = "Carbs") val carbs: Int?,
    @ColumnInfo(name = "Fiber") val fiber: Int?,
    @ColumnInfo(name = "Sugar") val sugar: Int?,
    @ColumnInfo(name = "Protein") val protein: Int?,
    @ColumnInfo(name = "Instruction") val instruction: String?,
    @ColumnInfo(name = "Ingredients") val ingredients: String?,
    @ColumnInfo(name = "Category") val category: String?,


)
