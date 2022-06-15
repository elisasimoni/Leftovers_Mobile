package com.example.leftovers


import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun toString(list: ArrayList<String>): String {
        return list.joinToString(",")
    }

    @TypeConverter
    fun toArrayList(string: String): ArrayList<String> {
        return ArrayList(string.split(";").toMutableList())
    }


}