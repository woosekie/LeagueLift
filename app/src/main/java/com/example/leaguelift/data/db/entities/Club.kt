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

