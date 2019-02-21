package com.example.dsublett.monsterfetch.fragments

import android.util.Log
import android.view.View
import com.example.dsublett.monsterfetch.adapters.ItemAdapter
import com.example.dsublett.monsterfetch.models.ResponseItem
import com.example.dsublett.monsterfetch.services.AppProxy
import kotlinx.android.synthetic.main.item_list.*

class MonstersListFragment : ItemListFragment() {
    override fun fetchData() = AppProxy.dndService.getMonsters(
        {
            this.rvItemList?.adapter =
                ItemAdapter(it,
                    object : ItemAdapter.OnItemClickListener {
                        override fun onItemClick(responseItem: ResponseItem) {
                            this@MonstersListFragment.showable?.showDetails(responseItem)
                        }
                    }
                )
            this.loadingSpinner?.visibility = View.INVISIBLE
        },
        {
            this.loadingSpinner?.visibility = View.INVISIBLE
            Log.d(this@MonstersListFragment::class.java.canonicalName, "$it")
        }
    )
}
