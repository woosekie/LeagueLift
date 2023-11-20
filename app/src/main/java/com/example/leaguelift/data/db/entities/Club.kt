package com.example.leaguelift.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "club")
data class Club(
    @ColumnInfo(name = "club_name")
    var name: String,
    @ColumnInfo(name = "club_city")
    var city: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}

@Entity(tableName = "matches")
data class Match(
    val homeClubId: Long,
    val awayClubId: Long,
    val homeGoals: Int,
    val awayGoals: Int
){
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}

@Entity(tableName = "league_standings")
data class LeagueStanding(
    val clubId: Long,
    val totalMatches: Int,
    val wins: Int,
    val losses: Int,
    val goalsFor: Int,
    val goalsAgainst: Int,
    val points: Int
){
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}