package com.example.leaguelift.ui

import android.content.Context
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import com.example.leaguelift.R
import com.example.leaguelift.data.db.entities.Club

class AddClubDialog(
    context: Context,
    private var addDialogListener: AddDialogListener,
    private var viewModelnya: ClubViewModel
) : AppCompatDialog(context) {

    private lateinit var etName: EditText
    private lateinit var etCity: EditText
    private lateinit var clubName: List<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_club)

        val sendButton = findViewById<CardView>(R.id.button_send)
        etName = findViewById(R.id.club_name)!!
        etCity = findViewById(R.id.club_city)!!

        viewModelnya.getClubName().observe(this, Observer {
            clubName = it
        })

        nameValidate()
        cityValidate()

        sendButton!!.setOnClickListener {
            val name = etName.text.toString()
            val city = etCity.text.toString()

            if (name.isEmpty() || city.isEmpty()) {
                Toast.makeText(context, "Kolom tidak boleh kosong !", Toast.LENGTH_LONG).show()
            } else {
                if (clubName.contains(name)) {
                    Toast.makeText(context, "Nama tidak boleh sama dengan klub lain !", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(context, "Berhasil menambahkan klub", Toast.LENGTH_LONG).show()
                    val item = Club(name, city)
                    addDialogListener.onAddButtonClicker(item)
                    dismiss()
                }
            }
        }
    }


    private fun nameValidate() {
        etName.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                if (etName.text.toString().isEmpty()) {
                    etName.error = "Nama tidak boleh kosong"
                }
            }
        }
    }

    private fun cityValidate() {
        etCity.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                if (etCity.text.toString().isEmpty()) {
                    etCity.error = "Kota tidak boleh kosong"
                }
            }
        }
    }


}