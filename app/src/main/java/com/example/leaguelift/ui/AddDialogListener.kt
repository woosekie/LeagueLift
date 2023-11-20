package com.example.leaguelift.ui

import com.example.leaguelift.data.db.entities.Club

interface AddDialogListener {
    fun onAddButtonClicker(item: Club)
}