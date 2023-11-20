package com.example.leaguelift.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.leaguelift.R
import com.example.leaguelift.data.db.ClubDatabase
import com.example.leaguelift.data.repositories.ClubRepository
import com.example.leaguelift.databinding.ActivityAddClubBinding
import com.example.leaguelift.databinding.ActivityInputMatchBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class InputMatchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInputMatchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputMatchBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.club1Selection.setOnClickListener {
            showSelectorDialog()
        }

    }

    private fun showSelectorDialog() {

        val database = ClubDatabase(this)
        val repository = ClubRepository(database)
        val factory = ClubViewModelFactory(repository)
        val viewModel = ViewModelProviders.of(this, factory).get(ClubViewModel::class.java)

        viewModel.getClubName().observe(this, Observer {
            var clubName = arrayOf(it)
            var clubNameArray = clubName[0].joinToString(",")
            if(clubName.count() == 0){
                Toast.makeText(
                    this,
                    "Klub bola tidak tersedia, harap masukkan data klub",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                var selectedItemIndex = 0
                var selectedClub = clubName[selectedItemIndex]
                MaterialAlertDialogBuilder(this)
                    .setTitle("Pilih tim yang bertanding")
                    .setNeutralButton("Batal") { dialog, which ->
                        // Respond to neutral button press
                    }
                    .setPositiveButton("Pilih") { dialog, which ->
                        binding.club1Selection.setText(selectedClub.joinToString(","))
                    }
                    // Single-choice items (initialized with checked item)
                    .setSingleChoiceItems(selectedClub, selectedItemIndex) { dialog, which ->
                        selectedItemIndex = which
                        selectedClub = clubName[which]
                    }
                    .show()

            }
        })
    }
}