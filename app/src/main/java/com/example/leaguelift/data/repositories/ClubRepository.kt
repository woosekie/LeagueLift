package com.example.leaguelift.data.repositories

import com.example.leaguelift.data.db.ClubDatabase
import com.example.leaguelift.data.db.entities.Club
import com.example.leaguelift.data.db.entities.Match

class ClubRepository(
    private val db: ClubDatabase
) {
    suspend fun insert(item: Club) = db.getDao().insert(item)
    suspend fun delete(item: Club) = db.getDao().delete(item)
    suspend fun insert(item: Match) = db.getDao().insert(item)
    suspend fun delete(item: Match) = db.getDao().delete(item)

    fun getAllClub() = db.getDao().getAllClub()
    fun getTotalClub() = db.getDao().getTotalClub()
}