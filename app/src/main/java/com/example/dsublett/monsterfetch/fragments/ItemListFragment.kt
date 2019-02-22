package com.example.dsublett.monsterfetch.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dsublett.monsterfetch.R
import com.example.dsublett.monsterfetch.activities.Showable
import com.example.dsublett.monsterfetch.adapters.ItemAdapter
import com.example.dsublett.monsterfetch.models.ResponseItem
import kotlinx.android.synthetic.main.item_list.*
import kotlinx.android.synthetic.main.item_list.view.*

abstract class ItemListFragment : Fragment() {
    protected abstract fun fetchData()
    protected var showable: Showable? = null
    protected fun logFailure(t: Throwable) {
        this.loadingSpinner?.visibility = View.INVISIBLE
        Log.d(this::class.java.canonicalName, "$t")
    }

    protected fun buildUI(itemList: List<ResponseItem>) {
        this.rvItemList?.adapter =
            ItemAdapter(itemList,
                object : ItemAdapter.OnItemClickListener {
                    override fun onItemClick(responseItem: ResponseItem) {
                        this@ItemListFragment.showable?.showDetails(responseItem)
                    }
                }
            )
        this.loadingSpinner?.visibility = View.INVISIBLE
    }

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
