package com.example.leaguelift.ui

import androidx.lifecycle.ViewModel
import com.example.leaguelift.data.db.entities.Club
import com.example.leaguelift.data.db.entities.Match
import com.example.leaguelift.data.repositories.ClubRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ClubViewModel(
    private val repository: ClubRepository
): ViewModel() {
    fun insert(item: Club)= CoroutineScope(Dispatchers.Main).launch {
        repository.insert(item)
    }
    fun insert(item: Match)= CoroutineScope(Dispatchers.Main).launch {
        repository.insert(item)
    }
    fun delete(item: Club)= CoroutineScope(Dispatchers.Main).launch {
        repository.delete(item)
    }

    fun getAllClub() = repository.getAllClub()
    fun getAllMatch() = repository.getAllMatch()
    fun getTotalClub() = repository.getTotalClub()
    fun getTotalmatch()= repository.getTotalMatch()
    fun getClubName() = repository.getClubName()
}