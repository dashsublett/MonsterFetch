package com.example.dsublett.monsterfetch.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.dsublett.monsterfetch.R
import com.example.dsublett.monsterfetch.models.FavoritesList
import com.example.dsublett.monsterfetch.models.ResponseItem
import kotlinx.android.synthetic.main.list_item.view.*

class FavoritesAdapter(private val favoritesList: FavoritesList,
                       private val listener: OnItemClickListener) :
    RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (this.favoritesList.isEmpty()) {
            holder.tvItemName.text = "You don't have any favorites"
        } else {
            when (getItemViewType(position)) {
                0 -> { // Monster
                    holder.tvItemName.text = this.favoritesList.monsterFavorites[position].name
                    holder.bind(this.favoritesList.monsterFavorites[position], this.listener)
                }
                1 -> { // Class
                    holder.tvItemName.text = this.favoritesList.classFavorites[position -
                        this.favoritesList.monsterFavorites.size].name
                    holder.bind(this.favoritesList.classFavorites[position -
                        this.favoritesList.monsterFavorites.size], this.listener)
                }
                2 -> { // Spell
                    holder.tvItemName.text = this.favoritesList.spellFavorites[position -
                        (this.favoritesList.startOfSpells)].name
                    holder.bind(this.favoritesList.spellFavorites[position -
                        (this.favoritesList.startOfSpells)], this.listener)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return if (this.favoritesList.isEmpty()) {
            1
        } else {
            this.favoritesList.size
        }
    }

    override fun getItemViewType(position: Int): Int {
        super.getItemViewType(position)
        return when {
            (position < this.favoritesList.monsterFavorites.size) -> 0
            (position >= this.favoritesList.monsterFavorites.size) and
                (position < this.favoritesList.startOfSpells) -> 1
            else -> 2
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvItemName: TextView = itemView.itemName
        fun bind(responseItem: ResponseItem, listener: FavoritesAdapter.OnItemClickListener) {
            tvItemName.setOnClickListener {
                listener.onItemClick(responseItem)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(responseItem: ResponseItem)
    }
}
