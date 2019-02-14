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
        if (favoritesList.monsterFavorites.isEmpty() and
            favoritesList.classFavorites.isEmpty() and
            favoritesList.spellFavorites.isEmpty() ) {
            holder.tvItemName.text = "You don't have any favorites"
        } else {
            holder.tvItemName.text = this.favoritesList.monsterFavorites[position].name
            holder.bind(favoritesList.monsterFavorites[position], listener)
        }
    }

    override fun getItemCount(): Int {
        return if (favoritesList.monsterFavorites.isEmpty() and
            favoritesList.classFavorites.isEmpty() and
            favoritesList.spellFavorites.isEmpty()) {
            1
        } else {
            this.favoritesList.monsterFavorites.size
        }

    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
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
