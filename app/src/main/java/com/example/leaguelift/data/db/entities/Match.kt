package com.example.leaguelift.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "matches")
data class Match(
    val homeClubId: String,
    val awayClubId: String,
    val homeGoals: Int,
    val awayGoals: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
