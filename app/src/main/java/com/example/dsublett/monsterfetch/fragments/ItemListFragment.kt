package com.example.dsublett.monsterfetch.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dsublett.monsterfetch.R
import com.example.dsublett.monsterfetch.activities.Showable
import com.example.dsublett.monsterfetch.adapters.ItemAdapter
import com.example.dsublett.monsterfetch.models.DNDAPIResponse
import com.example.dsublett.monsterfetch.models.ResponseItem
import kotlinx.android.synthetic.main.item_list.*
import kotlinx.android.synthetic.main.item_list.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class ItemListFragment : Fragment() {
    protected abstract fun fetchData()
    private var showable: Showable? = null
    protected val fetchCallback = object : Callback<DNDAPIResponse> {
        override fun onResponse(call: Call<DNDAPIResponse>, response: Response<DNDAPIResponse>) {
            this@ItemListFragment.rvItemList?.adapter =
                ItemAdapter(response.body()?.results ?: emptyList(),
                    object : ItemAdapter.OnItemClickListener {
                        override fun onItemClick(responseItem: ResponseItem) {
                            this@ItemListFragment.showable?.showDetails(responseItem)
                        }
                    }
                )
            this@ItemListFragment.loadingSpinner?.visibility = View.INVISIBLE
        }

        override fun onFailure(call: Call<DNDAPIResponse>, t: Throwable) {
            this@ItemListFragment.loadingSpinner?.visibility = View.INVISIBLE
        }
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
}
