package com.example.dsublett.monsterfetch

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.monster_item.view.*

class MonsterAdapter : RecyclerView.Adapter<MonsterAdapter.ViewHolder>() {
//    var monsters = mutableListOf<Monster>() // Data set for recycler view
    var monsters = mutableListOf( // Mock data
        Monster("Dragon", ""),
        Monster("Zombie", ""),
        Monster("Goblin", "")
    )
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create the view holder
        return ViewHolder(LayoutInflater
                .from(parent.context)
                .inflate(R.layout.monster_item, parent, false))
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Bind monster name to text view
        holder.tvMonsterName.text = monsters[position].name
    }
    override fun getItemCount(): Int {
        // Get size of List<Monster> (required to implement RecyclerView.Adapter)
        return monsters.size
    }
    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        // Reference the text view that holds the monster name
        val tvMonsterName: TextView = mView.monsterName
    }
}
