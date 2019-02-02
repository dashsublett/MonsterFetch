package com.example.dsublett.monsterfetch

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.monster_item.view.*

class MonsterAdapter(var monsters: MutableList<Monster>)
    : RecyclerView.Adapter<MonsterAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create the view holder and inflate the monster_item layout for each monster
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
        // Set the reference to the text view that holds the monster name
        val tvMonsterName: TextView = mView.monsterName
    }
}
