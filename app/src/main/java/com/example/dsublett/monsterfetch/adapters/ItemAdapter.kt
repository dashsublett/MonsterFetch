package com.example.dsublett.monsterfetch.adapters

import com.example.dsublett.monsterfetch.models.ResponseItem

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.dsublett.monsterfetch.R
import kotlinx.android.synthetic.main.list_item.view.*

class ItemAdapter(private val responseItems: List<ResponseItem>) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (responseItems.isEmpty()) {
            holder.tvItemName.text = "You don't have any favorites"
        } else {
            holder.tvItemName.text = this.responseItems[position].name
        }
    }

    override fun getItemCount(): Int {
        return if (this.responseItems.isEmpty()) {
            1
        } else {
            this.responseItems.size
        }
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvItemName: TextView = itemView.itemName
    }
}
