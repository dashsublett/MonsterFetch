package com.example.dsublett.monsterfetch

import com.example.dsublett.monsterfetch.services.DNDAPIService

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_list.view.*

class ItemFragment : Fragment() {
    private val apiService = DNDAPIService.create()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.item_list, container, false).apply {
            // Set layout manager and adapter for recycler view
            this.rvItemList.layoutManager = LinearLayoutManager(context)
            this.rvItemList.adapter = ItemAdapter(mutableListOf())
        }
    }
}
