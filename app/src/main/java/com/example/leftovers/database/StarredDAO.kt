package com.example.leftovers.database

import androidx.cardview.widget.CardView
import androidx.room.*
import com.example.leftovers.Recipe
import com.example.leftovers.Starred
import com.example.leftovers.User

@Dao
interface StarredDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStarred(starred: Starred)

    @Delete
    fun deleteStarred(starred: Starred)

    @Query("SELECT uid FROM Starred WHERE Email = :userPid")
    fun getStarred(userPid: String): List<Int>

}