package com.example.leaguelift

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.leaguelift.data.db.entities.Club
import com.example.leaguelift.ui.ClubViewModel

class ClubAdapter(
    var items: List<Club>,
    private val viewModel: ClubViewModel
): RecyclerView.Adapter<ClubAdapter.ClubViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClubViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(com.example.leaguelift.R.layout.club_item_row, parent, false)
        return ClubViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClubViewHolder, position: Int) {
        val curClub = items[position]

        holder.tvName.text = curClub.name
        holder.tvCity.text = curClub.city

        holder.btDelete.setOnClickListener{
            viewModel.delete(curClub)
        }
    }

    override fun getItemCount(): Int {
        return items.size

    }
    inner class ClubViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var tvName: TextView = itemView.findViewById(R.id.club_name)
        var tvCity: TextView = itemView.findViewById(R.id.club_city)
        var btDelete: ImageView = itemView.findViewById(R.id.button_delete)
    }

}