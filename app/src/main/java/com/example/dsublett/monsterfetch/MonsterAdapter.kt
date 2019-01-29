package com.example.dsublett.monsterfetch

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.monster_item.view.*

class MonsterAdapter : RecyclerView.Adapter<ViewHolder>() {
    var monsters = mutableListOf<Monster>() // Data set for recycler view

    override fun getItemCount(): Int {
        // Get size of List<Monster> (required to implement RecyclerView.Adapter)
        return monsters.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create the view holder
        return ViewHolder(LayoutInflater
                .from(parent.context)
                .inflate(R.layout.monster_item, parent, false))
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Bind monster name to text view
        holder.tvMonsterName?.text = monsters[position].name
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // Reference the text view that holds the monster name
    val tvMonsterName = view.monsterName
}
