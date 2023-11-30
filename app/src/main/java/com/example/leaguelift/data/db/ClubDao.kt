package com.example.leaguelift.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.leaguelift.data.db.entities.Club
import com.example.leaguelift.data.db.entities.Match

@Dao
interface ClubDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(item: Club)

    @Delete
    suspend fun delete(item: Club)

    @Query("SELECT * FROM club")
    fun getAllClub(): LiveData<List<Club>>

    @Query("SELECT COUNT(*) FROM club")
    fun getTotalClub(): LiveData<Int>

    @Query("SELECT club_name FROM club")
    fun getClubName(): LiveData<List<String>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(item: Match)

    @Delete
    suspend fun delete(item: Match)
}