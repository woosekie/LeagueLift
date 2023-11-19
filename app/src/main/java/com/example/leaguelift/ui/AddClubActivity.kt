package com.example.leaguelift.ui

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.leaguelift.R
import com.example.leaguelift.databinding.ActivityAddClubBinding

class AddClubActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddClubBinding
    private lateinit var builderDialog: AlertDialog.Builder
    private lateinit var alertDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddClubBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.addClub.setOnClickListener {
            addClubDialog(R.layout.dialog_add_club)
        }
    }

    private fun addClubDialog(layout: Int) {
        builderDialog = AlertDialog.Builder(this)
        val layoutDialog: View = layoutInflater.inflate(layout, null)
        val editName: EditText = layoutDialog.findViewById(R.id.club_name)
        val editCity: EditText = layoutDialog.findViewById(R.id.club_city)
        val sendButton: CardView = layoutDialog.findViewById(R.id.button_send)

        builderDialog.setView(layout)
        alertDialog = builderDialog.create()
        alertDialog.show()

        editName.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                editName.error = validName(editName)
            }
        }

        editCity.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                editCity.error = validCity(editCity)
            }
        }

        sendButton.setOnClickListener {
            if (editName.text.toString().isNotEmpty() && editCity.text.toString().isNotEmpty()) {
                Toast.makeText(
                    this,
                    "Berhasil menambahkan klub",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(
                    this,
                    "Gagal menambahkan klub",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }


    private fun validName(editName: EditText): String? {
        val nameValue = editName.text.toString()
        if (nameValue.isEmpty()) {
            return "Kolom masih kosong"
        }
        return null
    }

    private fun validCity(editName: EditText): String? {
        val passValue = editName.text.toString()
        if (passValue.isEmpty()) {
            return "Kolom masih kosong"
        }
        return null
    }


}