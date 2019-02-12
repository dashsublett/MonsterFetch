package com.example.dsublett.monsterfetch.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dsublett.monsterfetch.R
import com.example.dsublett.monsterfetch.adapters.ItemAdapter
import com.example.dsublett.monsterfetch.models.ResponseItem
import kotlinx.android.synthetic.main.item_list.*


class FavoritesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.item_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.rvItemList.adapter = ItemAdapter(emptyList(),
            object : ItemAdapter.OnItemClickListener {
                override fun onItemClick(responseItem: ResponseItem) {
                    // Handle click on favorite item
                }
            })
        this.loadingSpinner.visibility = View.INVISIBLE
    }
}
