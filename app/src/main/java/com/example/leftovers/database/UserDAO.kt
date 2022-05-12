package com.example.leftovers.database

import androidx.cardview.widget.CardView
import androidx.room.*
import com.example.leftovers.User

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE email = :email AND password = :pw")
    fun catchUser(email: String, pw: String): User

    @Update
    fun updateUser(users: User)

    @Delete
    fun delete(user: User)
}