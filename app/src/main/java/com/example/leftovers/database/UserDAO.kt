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

    @Query("SELECT CASE WHEN EXISTS (\n" +
            "    SELECT *\n" +
            "    FROM [User]\n" +
            "    WHERE email = :email AND password = :pw\n" +
            ")\n" +
            "THEN CAST(1 AS BIT)\n" +
            "ELSE CAST(0 AS BIT) END")
    fun existUser(email: String, pw: String): Boolean


    @Update
    fun updateUser(users: User)

    @Delete
    fun delete(user: User)
}