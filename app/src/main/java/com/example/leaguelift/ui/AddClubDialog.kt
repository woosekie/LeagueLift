package com.example.leaguelift.ui

import android.content.Context
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import androidx.cardview.widget.CardView
import com.example.leaguelift.R
import com.example.leaguelift.data.db.entities.Club

class AddClubDialog(context: Context, var addDialogListener: AddDialogListener) : AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_club)

        val sendButton = findViewById<CardView>(R.id.button_send)
        val etName = findViewById<EditText>(R.id.club_name)
        val etCity = findViewById<EditText>(R.id.club_city)

        sendButton!!.setOnClickListener {
            val name = etName!!.text.toString()
            val city = etCity!!.text.toString()

            if(name.isEmpty() || city.isEmpty()){
                Toast.makeText(
                    context,
                    "Masih terdapat kolom kosong !",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            } else {
                Toast.makeText(
                    context,
                    "Berhasil menambahkan klub",
                    Toast.LENGTH_LONG
                ).show()
                val item = Club(name, city)
                addDialogListener.onAddButtonClicker(item)
                dismiss()
            }
        }

    }
}