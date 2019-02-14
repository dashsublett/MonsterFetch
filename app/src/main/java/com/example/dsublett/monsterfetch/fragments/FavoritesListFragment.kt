package com.example.dsublett.monsterfetch.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dsublett.monsterfetch.R
import com.example.dsublett.monsterfetch.adapters.FavoritesAdapter
import com.example.dsublett.monsterfetch.models.ResponseItem
import com.example.dsublett.monsterfetch.utils.SPFavorites
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
        val sharedPreferences = activity?.getSharedPreferences("com.example.dsublett.monsterfetch.sharedPreferences", Context.MODE_PRIVATE)

        this.rvItemList.adapter = FavoritesAdapter(SPFavorites.getFavorites(sharedPreferences),
            object : FavoritesAdapter.OnItemClickListener {
                override fun onItemClick(responseItem: ResponseItem) {
                    when (UrlParse.getEndpoint(responseItem.url)) {
                        // TODO: Launch detail activity on click
                    }
                }
            })
        this.loadingSpinner.visibility = View.INVISIBLE
    }
}
