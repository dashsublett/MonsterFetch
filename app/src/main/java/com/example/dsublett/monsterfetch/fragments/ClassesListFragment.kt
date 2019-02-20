package com.example.dsublett.monsterfetch.fragments

import android.util.Log
import android.view.View
import com.example.dsublett.monsterfetch.adapters.ItemAdapter
import com.example.dsublett.monsterfetch.models.ResponseItem
import com.example.dsublett.monsterfetch.services.RemoteDndService
import kotlinx.android.synthetic.main.item_list.*
import java.lang.Exception

class ClassesListFragment : ItemListFragment() {
    override fun fetchData() = RemoteDndService().getClasses(
        {
            this.rvItemList?.adapter =
                ItemAdapter(it,
                    object : ItemAdapter.OnItemClickListener {
                        override fun onItemClick(responseItem: ResponseItem) {
                            this@ClassesListFragment.showable?.showDetails(responseItem)
                        }
                    }
                )
            this.loadingSpinner?.visibility = View.INVISIBLE
        },
        {
            this.loadingSpinner?.visibility = View.INVISIBLE
            try {
                throw it
            } catch (e: Exception) {
                Log.d(this@ClassesListFragment::class.java.canonicalName, "$e")
            }
        }
    )
}
