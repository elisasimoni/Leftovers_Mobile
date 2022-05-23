package com.example.leftovers

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.lang.reflect.Constructor
@Entity
data class User(

    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "Full_name") var fullName: String?,
    @ColumnInfo(name = "Email") val email: String?,
    @ColumnInfo(name = "Password") val password: String?,
    @ColumnInfo(name = "Conf_password") val confPassword: String?


)
