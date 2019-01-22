package com.example.dsublett.monsterfetch

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.monster_list_item.view.*

class MonsterAdapter(val context: Context)
    : RecyclerView.Adapter<ViewHolder>() {
    val monsters = mutableListOf<Monster>() // Data set for recycler view
    override fun getItemCount(): Int {
        return monsters.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create the view holder
        return ViewHolder(LayoutInflater
                .from(context)
                .inflate(R.layout.monster_list_item, parent, false))
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Bind monster name to text view
        holder.tvMonsterType?.text = monsters[position].name
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // Reference the text view that holds the monster name
    val tvMonsterType = view.tv_monster_type
}
