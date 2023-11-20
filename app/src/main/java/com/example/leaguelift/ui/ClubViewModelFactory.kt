package com.example.leaguelift.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.leaguelift.data.repositories.ClubRepository

class ClubViewModelFactory(
    private val repository: ClubRepository
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ClubViewModel(repository) as T
    }
}