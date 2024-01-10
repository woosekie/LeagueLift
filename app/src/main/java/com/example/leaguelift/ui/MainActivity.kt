package com.example.leaguelift.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.leaguelift.ClubAdapter
import com.example.leaguelift.MatchAdapter
import com.example.leaguelift.data.db.ClubDatabase
import com.example.leaguelift.data.repositories.ClubRepository
import com.example.leaguelift.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val database = ClubDatabase(this)
        val repository = ClubRepository(database)
        val factory = ClubViewModelFactory(repository)
        val viewModel = ViewModelProviders.of(this, factory).get(ClubViewModel::class.java)


        val adapter = MatchAdapter(listOf())

        binding.recycleViewMatch.layoutManager = LinearLayoutManager(this)
        binding.recycleViewMatch.adapter = adapter


        viewModel.getAllMatch().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
            if (adapter.items.isNotEmpty()){
                binding.recycleViewMatch.visibility = View.VISIBLE
                binding.emptyMessage.visibility = View.GONE
            }
        })

        val dateViewModel = ViewModelProvider(this).get(DateViewModel::class.java)

        dateViewModel.formattedDate.observe(this, Observer { formattedDate ->
            binding.dateText.text = formattedDate
        })

        viewModel.getTotalClub().observe(this, Observer {
            binding.totalClub.text = it.toString()
        })

        viewModel.getTotalmatch().observe(this, Observer{
            binding.totalMatch.text = it.toString()
        })

        binding.addClub.setOnClickListener{
            startActivity(Intent(this, AddClubActivity::class.java))
        }
        binding.addMatch.setOnClickListener{
            startActivity(Intent(this, InputMatchActivity::class.java))
        }
        binding.standingMenu.setOnClickListener{
            startActivity(Intent(this, StandingActivity::class.java))
        }
    }
}