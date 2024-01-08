package com.example.leaguelift.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.leaguelift.ClubAdapter
import com.example.leaguelift.data.db.ClubDatabase
import com.example.leaguelift.data.db.entities.Club
import com.example.leaguelift.data.repositories.ClubRepository
import com.example.leaguelift.databinding.ActivityAddClubBinding

class AddClubActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddClubBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddClubBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val database = ClubDatabase(this)
        val repository = ClubRepository(database)
        val factory = ClubViewModelFactory(repository)
        val viewModel = ViewModelProviders.of(this, factory).get(ClubViewModel::class.java)

        val adapter = ClubAdapter(listOf(),viewModel)

        binding.recycleViewClub.layoutManager = LinearLayoutManager(this)
        binding.recycleViewClub.adapter = adapter


        viewModel.getAllClub().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
            if (adapter.items.isNotEmpty()){
                binding.recycleViewClub.visibility = View.VISIBLE
                binding.emptyMessage.visibility = View.GONE
            }
        })

        binding.addClub.setOnClickListener {
            AddClubDialog(this,
            object : AddDialogListener{
                override fun onAddButtonClicker(item: Club) {
                    viewModel.insert(item)
                }
            }, viewModel ).show()
        }
    }
}