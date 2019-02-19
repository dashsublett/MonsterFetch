package com.example.dsublett.monsterfetch.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.dsublett.monsterfetch.R
import com.example.dsublett.monsterfetch.models.ResponseItem
import kotlinx.android.synthetic.main.list_item.view.*

class ItemAdapter(private val responseItems: List<ResponseItem>,
                  private val listener: OnItemClickListener) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvItemName.text = this.responseItems[position].name
        holder.bind(this.responseItems[position])
    }

    override fun getItemCount(): Int = this.responseItems.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvItemName: TextView = itemView.itemName
        fun bind(responseItem: ResponseItem) {
            this.tvItemName.setOnClickListener {
                this@ItemAdapter.listener.onItemClick(responseItem)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(responseItem: ResponseItem)
    }
}
