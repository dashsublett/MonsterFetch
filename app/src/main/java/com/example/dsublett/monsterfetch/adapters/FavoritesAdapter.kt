package com.example.dsublett.monsterfetch.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.dsublett.monsterfetch.R
import com.example.dsublett.monsterfetch.models.FavoritesList
import com.example.dsublett.monsterfetch.models.ResponseItem
import kotlinx.android.synthetic.main.list_item_wheader.view.*

class FavoritesAdapter(private val favoritesList: FavoritesList,
                       private val listener: OnItemClickListener) :
    RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        when (viewType) {
            in -1..5 step 2 -> ViewHolder(LayoutInflater
                .from(parent.context)
                .inflate(R.layout.list_item, parent, false))
            else -> ViewHolder(LayoutInflater
                .from(parent.context)
                .inflate(R.layout.list_item_wheader, parent, false))
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            -1 -> holder.tvItemName.text = "You don't have any favorites"
            0 -> { // Monster with header
                holder.tvHeader?.text = "MONSTERS"
                holder.tvItemName.text = this.favoritesList["monsterFavorites"][position].name
                holder.bind(this.favoritesList["monsterFavorites"][position])
            }
            1 -> { // Monster
                holder.tvItemName.text = this.favoritesList["monsterFavorites"][position].name
                holder.bind(this.favoritesList["monsterFavorites"][position])
            }
            2 -> { // Class with header
                holder.tvHeader?.text = "CLASSES"
                holder.tvItemName.text = this.favoritesList["classFavorites"][position -
                    this.favoritesList["monsterFavorites"].size].name
                holder.bind(this.favoritesList["classFavorites"][position -
                    this.favoritesList["monsterFavorites"].size])
            }
            3 -> { // Class
                holder.tvItemName.text = this.favoritesList["classFavorites"][position -
                    this.favoritesList["monsterFavorites"].size].name
                holder.bind(this.favoritesList["classFavorites"][position -
                    this.favoritesList["monsterFavorites"].size])
            }
            4 -> { // Spell with header
                holder.tvHeader?.text = "SPELLS"
                holder.tvItemName.text = this.favoritesList["spellFavorites"][position -
                    this.favoritesList.startOfSpells].name
                holder.bind(this.favoritesList["spellFavorites"][position -
                    this.favoritesList.startOfSpells])
            }
            5 -> { // Spell
                holder.tvItemName.text = this.favoritesList["spellFavorites"][position -
                    this.favoritesList.startOfSpells].name
                holder.bind(this.favoritesList["spellFavorites"][position -
                    this.favoritesList.startOfSpells])
            }
        }
    }

    override fun getItemCount(): Int = when {
        this.favoritesList.isEmpty() -> 1
        else -> this.favoritesList.size
    }

    override fun getItemViewType(position: Int): Int = when {
        this.favoritesList.isEmpty() -> -1
        (position == 0) -> 0
        ((position > 0) and (position < this.favoritesList["monsterFavorites"].size)) -> 1
        position == this.favoritesList["monsterFavorites"].size -> 2
        (position > this.favoritesList["monsterFavorites"].size) and
            (position < this.favoritesList.startOfSpells) -> 3
        position == this.favoritesList.startOfSpells -> 4
        else -> 5
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvHeader: TextView? = itemView.header
        val tvItemName: TextView = itemView.findViewById(R.id.itemName)
        fun bind(responseItem: ResponseItem) {
            this.tvItemName.setOnClickListener {
                this@FavoritesAdapter.listener.onItemClick(responseItem)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(responseItem: ResponseItem)
    }
}
