package com.example.leftovers

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.lang.reflect.Constructor
@Entity
data class User(

    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "Full_name") val fullName: String?,
    @ColumnInfo(name = "Email") val email: String?,
    @ColumnInfo(name = "Mobile_number") val mobileNumber: Int?,
    @ColumnInfo(name = "Password") val password: String?,
    @ColumnInfo(name = "Conf_password") val confPassword: String?


)
