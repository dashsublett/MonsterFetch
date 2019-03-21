package com.example.dsublett.monsterfetch.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.dsublett.monsterfetch.R
import com.example.dsublett.monsterfetch.models.Ability
import kotlinx.android.synthetic.main.list_ability.view.*

class AbilityAdapter(
    private val abilities: List<Ability>,
    private val listener: AbilityAdapter.OnItemClickListener
) :
    RecyclerView.Adapter<AbilityAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_ability, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        this.abilities[position].let {
            holder.apply {
                tvAbilityName.text = it.name
                tvAbilityDesc.text = it.desc
            }.run {
                bind(it)
            }
        }

    override fun getItemCount(): Int = this.abilities.size

    inner class ViewHolder(private val abilityView: View) : RecyclerView.ViewHolder(abilityView) {
        val tvAbilityName: TextView = this.abilityView.abilityName
        val tvAbilityDesc: TextView = this.abilityView.abilityDesc
        fun bind(ability: Ability?) =
            this.abilityView.setOnClickListener {
                ability?.let {
                    this@AbilityAdapter.listener.onItemClick(it)
                }
            }
    }

    interface OnItemClickListener {
        fun onItemClick(ability: Ability)
    }
}
