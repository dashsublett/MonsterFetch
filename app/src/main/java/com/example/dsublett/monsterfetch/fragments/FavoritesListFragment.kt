package com.example.dsublett.monsterfetch.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dsublett.monsterfetch.R
import com.example.dsublett.monsterfetch.activities.Showable
import com.example.dsublett.monsterfetch.adapters.FavoritesAdapter
import com.example.dsublett.monsterfetch.models.ResponseItem
import com.example.dsublett.monsterfetch.utils.SPFavorites
import kotlinx.android.synthetic.main.item_list.*

class FavoritesListFragment : Fragment() {
    private var showable: Showable? = null
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
                    this@FavoritesListFragment.showable?.showDetails(responseItem)
                }
            })
        this.loadingSpinner.visibility = View.INVISIBLE
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.showable = (this.activity as? Showable)
    }
}
