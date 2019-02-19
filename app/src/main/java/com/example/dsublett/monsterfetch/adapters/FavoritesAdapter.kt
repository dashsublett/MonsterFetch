package com.example.dsublett.monsterfetch.adapters

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.dsublett.monsterfetch.R
import com.example.dsublett.monsterfetch.models.FavoriteItem
import com.example.dsublett.monsterfetch.models.FavoriteType
import com.example.dsublett.monsterfetch.models.ResponseItem

class FavoritesAdapter(private val favoritesList: List<FavoriteItem>,
                       private val listener: OnItemClickListener) :
    RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_item, parent, false))
            .apply {
                if (viewType == FavoriteType.Header.ordinal) {
                    tvItemName.gravity = Gravity.START
                    tvItemName.setBackgroundColor(ContextCompat
                        .getColor(parent.context, R.color.primary_dark_material_light))
                }
            }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val favoriteItem = this.favoritesList[position]
        holder.tvItemName.text = favoriteItem.label
        if (favoriteItem.type == FavoriteType.Favorite) {
            holder.bind(this.favoritesList[position].data)
        }
    }

    override fun getItemCount(): Int = this.favoritesList.size

    override fun getItemViewType(position: Int): Int = this.favoritesList[position].type.ordinal

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvItemName: TextView = itemView.findViewById(R.id.itemName)
        fun bind(responseItem: ResponseItem?) {
            this.tvItemName.setOnClickListener {
                responseItem?.let {
                    this@FavoritesAdapter.listener.onItemClick(it)
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(responseItem: ResponseItem)
    }
}
