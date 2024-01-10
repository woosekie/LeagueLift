package com.example.leaguelift.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.leaguelift.R
import com.example.leaguelift.data.db.ClubDatabase
import com.example.leaguelift.data.db.entities.Club
import com.example.leaguelift.data.db.entities.Match
import com.example.leaguelift.data.repositories.ClubRepository
import com.example.leaguelift.databinding.ActivityInputMatchBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_input_match.club_1_selection
import kotlinx.android.synthetic.main.activity_input_match.club_2_selection
import kotlinx.android.synthetic.main.activity_input_match.score_1_selection
import kotlinx.android.synthetic.main.activity_input_match.score_2_selection

class InputMatchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInputMatchBinding
    private lateinit var clubName: List<String>
    private val selectedOptions = mutableMapOf<TextView, Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputMatchBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val database = ClubDatabase(this)
        val repository = ClubRepository(database)
        val factory = ClubViewModelFactory(repository)
        val viewModel = ViewModelProviders.of(this, factory).get(ClubViewModel::class.java)

        viewModel.getClubName().observe(this, Observer {
            clubName = it
        })

        setOnClickListenerForTextView(binding.club1Selection)
        setOnClickListenerForTextView(binding.club2Selection)


        binding.buttonSend.setOnClickListener {
            val club1 = club_1_selection.text.toString()
            val club2 = club_2_selection.text.toString()
            val score1 = score_1_selection.text.toString()
            val score2 = score_2_selection.text.toString()
            if (club1 == "Klub 1" || club2 == "Klub 2") {
                Toast.makeText(this, "Harap masukan klub yang bertanding", Toast.LENGTH_LONG).show()
            } else if (club1 == club2) {
                Toast.makeText(this, "Harap masukan klub yang berbeda", Toast.LENGTH_LONG).show()
            } else if (score1.isEmpty() || score2.isEmpty()) {
                Toast.makeText(this, "Harap masukan skor pertandingan", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Berhasil menambahkan pertandingan baru", Toast.LENGTH_LONG).show()
                val item = Match(club1, club2, score1.toInt(), score2.toInt())
                viewModel.insert(item)
            }
        }


        binding.addClub.setOnClickListener {
            addView()
        }

    }

    private fun setOnClickListenerForTextView(textView: TextView) {
        textView.setOnClickListener {
            if (clubName.isEmpty()) {
                Toast.makeText(this, getString(R.string.empty_club), Toast.LENGTH_LONG).show()
            } else {
                showSelectorDialog(textView, clubName)
            }
        }
    }

    private fun addView() {
        val view: View
        view = layoutInflater.inflate(R.layout.multiple_input_item, null, false)
        binding.layoutVertical.addView(view)

        val deleteItem: ImageView = view.findViewById(R.id.button_delete)

        deleteItem.setOnClickListener {
            deleteView(view)
        }
    }

    private fun deleteView(view: View) {
        binding.layoutVertical.removeView(view)
    }

    private fun showSelectorDialog(textView: TextView, clubName: List<String>) {
        val items = clubName.toTypedArray()

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Choose an option")

        val selectedOptionIndex = selectedOptions[textView] ?: -1

        builder.setSingleChoiceItems(items, selectedOptionIndex) { _, which ->
            selectedOptions[textView] = which
        }

        builder.setPositiveButton("OK") { _, _ ->
            val selectedOptionIndexFinal = selectedOptions[textView] ?: -1

            if (selectedOptionIndexFinal != -1) {
                val selectedValue = items[selectedOptionIndexFinal]
                textView.text = selectedValue
            }
        }

        builder.setNegativeButton("Cancel") { _, _ ->
            // Do nothing or handle cancel action
        }

        val dialog = builder.create()
        dialog.show()
    }
}