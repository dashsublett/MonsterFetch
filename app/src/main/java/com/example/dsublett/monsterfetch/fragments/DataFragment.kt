package com.example.dsublett.monsterfetch.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dsublett.monsterfetch.R
import com.example.dsublett.monsterfetch.adapters.ItemAdapter
import com.example.dsublett.monsterfetch.models.DNDAPIResponse
import kotlinx.android.synthetic.main.item_list.*
import kotlinx.android.synthetic.main.item_list.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class DataFragment : Fragment() {
    abstract fun fetchData()
    protected val fetchCallback = object: Callback<DNDAPIResponse> {
        override fun onResponse(call: Call<DNDAPIResponse>, response: Response<DNDAPIResponse>) {
            this@DataFragment.rvItemList?.adapter =
                    ItemAdapter(response.body()?.results ?: emptyList())
            this@DataFragment.loadingSpinner?.visibility = View.INVISIBLE
        }
        override fun onFailure(call: Call<DNDAPIResponse>, t: Throwable) {
            this@DataFragment.loadingSpinner?.visibility = View.INVISIBLE
        }
    }
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.item_list, container, false).apply {
            this.rvItemList.layoutManager = LinearLayoutManager(context)
            this.rvItemList.adapter = ItemAdapter(emptyList())
            this.loadingSpinner.visibility = View.VISIBLE
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.fetchData()
    }
}
