package com.example.dsublett.monsterfetch

import com.example.dsublett.monsterfetch.services.UpdateMonsterList

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.monster_item_list.view.*

class ItemFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.monster_item_list, container, false).apply {
            // Fetch monster list
            this.loadingSpinner.visibility = View.VISIBLE
            UpdateMonsterList { ml ->
                (this.rvMonsterList.adapter as MonsterAdapter).monsters = ml.toMutableList()
                this.rvMonsterList.adapter?.notifyDataSetChanged()
                this.loadingSpinner.visibility = View.INVISIBLE
            }.execute()
            // Set clear button click listener
            this.clearBtn.setOnClickListener{
                (this.rvMonsterList.adapter as MonsterAdapter).monsters.clear()
                this.rvMonsterList.adapter?.notifyDataSetChanged()
            }
            // Set layout manager and adapter for recycler view
            this.rvMonsterList.layoutManager = LinearLayoutManager(context)
            this.rvMonsterList.adapter = MonsterAdapter(mutableListOf())
        }
    }
}
