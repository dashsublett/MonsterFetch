package com.example.dsublett.monsterfetch.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dsublett.monsterfetch.adapters.ItemAdapter
import com.example.dsublett.monsterfetch.R
import kotlinx.android.synthetic.main.item_list.view.*

class ItemFragment : Fragment() {
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
