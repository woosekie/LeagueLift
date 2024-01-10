package com.example.leaguelift.ui

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import com.example.leaguelift.R

class StandingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_standing)

        val tableLayout: TableLayout = findViewById(R.id.tableLayout)
        val borderThickness = 5 // Sesuaikan ketebalan garis sesuai keinginan Anda

        // Mendapatkan font family dari resources

        // Gantilah dengan cara mendapatkan data dari SQLite dan menampilkannya
        val clubData = arrayOf(
            arrayOf("1", "Manchester United", "75"),
            arrayOf("2", "Liverpool", "68"),
            arrayOf("3", "Chelsea", "64"),
            arrayOf("4", "Manchester City", "72"),
            arrayOf("5", "Arsenal", "56")
        )

        for (data in clubData) {
            val tableRow = TableRow(this)
            val layoutParams = TableRow.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            tableRow.layoutParams = layoutParams
            tableRow.setBackgroundColor(resources.getColor(android.R.color.white))

            for ((index, value) in data.withIndex()) {
                val textView = TextView(this)
                textView.text = value
                textView.setPadding(8, 8, 8, 8)

                // Menerapkan berat sesuai dengan indeks kolom
                when (index) {
                    0 -> {
                        textView.layoutParams =
                            TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 0.3f)
                        textView.gravity = Gravity.CENTER
                    }
                    1 -> {
                        textView.layoutParams =
                            TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f)
                        textView.gravity = Gravity.START
                    }
                    2 -> {
                        textView.layoutParams =
                            TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 0.5f)
                        textView.gravity = Gravity.CENTER
                    }
                }

                // Mengatur font family

                tableRow.addView(textView)

            }

            tableLayout.addView(tableRow)
        }
    }
}