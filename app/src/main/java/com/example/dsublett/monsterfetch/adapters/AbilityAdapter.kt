package com.example.dsublett.monsterfetch.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.dsublett.monsterfetch.R
import com.example.dsublett.monsterfetch.models.Ability
import kotlinx.android.synthetic.main.list_ability.view.*

class AbilityAdapter(private val abilities: List<Ability>) :
    RecyclerView.Adapter<AbilityAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_ability, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvAbilityName.text = this.abilities[position].name
        holder.tvAbilityDesc.text = this.abilities[position].desc
    }

    override fun getItemCount(): Int = this.abilities.size

    inner class ViewHolder(abilityView: View) : RecyclerView.ViewHolder(abilityView) {
        val tvAbilityName: TextView = abilityView.abilityName
        val tvAbilityDesc: TextView = abilityView.abilityDesc
    }
}
