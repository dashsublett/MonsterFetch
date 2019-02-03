package com.example.dsublett.monsterfetch.adapters

import com.example.dsublett.monsterfetch.services.ResponseItem

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.dsublett.monsterfetch.R
import kotlinx.android.synthetic.main.list_item.view.*

class ItemAdapter(var responseItems: MutableList<ResponseItem>)
    : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create the view holder and inflate the list_item layout for each monster
        return ViewHolder(LayoutInflater
                .from(parent.context)
                .inflate(R.layout.list_item, parent, false))
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Bind monster name to text view
        holder.tvMonsterName.text = responseItems[position].name
    }
    override fun getItemCount(): Int {
        // Get size of List<ResponseItem> (required to implement RecyclerView.Adapter)
        return responseItems.size
    }
    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        // Set the reference to the text view that holds the monster name
        val tvMonsterName: TextView = mView.itemName
    }
}
