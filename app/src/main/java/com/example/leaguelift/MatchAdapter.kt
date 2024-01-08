package com.example.leaguelift

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.leaguelift.data.db.entities.Club
import com.example.leaguelift.data.db.entities.Match
import com.example.leaguelift.ui.ClubViewModel

class MatchAdapter(
    var items: List<Match>
): RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(com.example.leaguelift.R.layout.match_item_row, parent, false)
        return MatchViewHolder(view)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val match = items[position]
        holder.tvClubName1.text = match.homeClubId
        holder.tvClubName2.text = match.awayClubId
        holder.tvScore1.text = match.homeGoals.toString()
        holder.tvScore2.text = match.awayGoals.toString()
    }

    override fun getItemCount(): Int {
        return items.size

    }
    inner class MatchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var tvClubName1: TextView = itemView.findViewById(R.id.clubName1)
        var tvClubName2: TextView = itemView.findViewById(R.id.clubName2)
        var tvScore1: TextView = itemView.findViewById(R.id.score1)
        var tvScore2: TextView = itemView.findViewById(R.id.score2)
    }

}