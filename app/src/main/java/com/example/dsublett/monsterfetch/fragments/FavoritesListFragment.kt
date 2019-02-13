package com.example.dsublett.monsterfetch.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dsublett.monsterfetch.R
import com.example.dsublett.monsterfetch.adapters.ItemAdapter
import com.example.dsublett.monsterfetch.models.ResponseItem
import com.example.dsublett.monsterfetch.utils.UrlParse
import kotlinx.android.synthetic.main.item_list.*

class FavoritesListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.item_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        this.rvItemList.adapter = ItemAdapter(emptyList(),
        this.rvItemList.adapter = ItemAdapter(listOf(
            ResponseItem("Aboleth", "http://www.dnd5eapi.co/api/monsters/1"),
            ResponseItem("Barbarian", "http://www.dnd5eapi.co/api/classes/1"),
            ResponseItem("Acid Arrow", "http://www.dnd5eapi.co/api/spells/1")
        ),
            object : ItemAdapter.OnItemClickListener {
                override fun onItemClick(responseItem: ResponseItem) {
                    val sharedPreferences = activity?.getSharedPreferences("com.example.dsublett.monsterfetch.sharedPreferences", Context.MODE_PRIVATE)

                    when (UrlParse.getEndpoint(responseItem.url)) {
                        "monsters" -> Log.d("FavoritesListFragment", sharedPreferences?.getString("monsterFavorites", ""))
                        "classes" -> Log.d("FavoritesListFragment", sharedPreferences?.getString("classFavorites", ""))
                        "spells" -> Log.d("FavoritesListFragment", sharedPreferences?.getString("spellFavorites", ""))
                    }
                }
            })
        this.loadingSpinner.visibility = View.INVISIBLE
    }
}
