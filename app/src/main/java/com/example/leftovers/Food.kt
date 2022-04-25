package com.example.leftovers

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Food(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "Name") val name: String?,
    @ColumnInfo(name = "Name_scientific") val nameScientific: String?,
    @ColumnInfo(name = "Description") val description: String?,
    @ColumnInfo(name = "Food_group") val foodGroup: String?,
    @ColumnInfo(name = "Food_subgroup") val foodSubgroup: Int?,

    )
