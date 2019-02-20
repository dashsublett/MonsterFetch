package com.example.dsublett.monsterfetch.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dsublett.monsterfetch.R
import com.example.dsublett.monsterfetch.activities.Showable
import kotlinx.android.synthetic.main.item_list.*
import kotlinx.android.synthetic.main.item_list.view.*

abstract class ItemListFragment : Fragment() {
    protected abstract fun fetchData()
    protected var showable: Showable? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.showable = (this.activity as? Showable)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater
            .inflate(R.layout.item_list, container, false)
            .apply { this.loadingSpinner.visibility = View.VISIBLE }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.fetchData()
    }

    override fun onPause() {
        super.onPause()
        this.loadingSpinner?.visibility = View.INVISIBLE
    }
}
