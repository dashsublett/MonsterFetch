package com.example.dsublett.monsterfetch.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dsublett.monsterfetch.adapters.ItemAdapter
import com.example.dsublett.monsterfetch.R
import com.example.dsublett.monsterfetch.models.DNDAPIResponse
import com.example.dsublett.monsterfetch.services.DNDAPIService
import kotlinx.android.synthetic.main.item_list.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SpellsFragment : Fragment() {
    private val TAG = this::class.java.simpleName
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.item_list, container, false).apply {
            this.rvItemList.layoutManager = LinearLayoutManager(context)
            this.rvItemList.adapter = ItemAdapter(mutableListOf())
            this.loadingSpinner.visibility = View.VISIBLE

            DNDAPIService.create().getSpells().enqueue(object: Callback<DNDAPIResponse> {
                override fun onResponse(call: Call<DNDAPIResponse>,
                                        response: Response<DNDAPIResponse>) {
                    (rvItemList.adapter as ItemAdapter).responseItems =
                            response.body()?.results!!.toMutableList()
                    rvItemList.adapter?.notifyDataSetChanged()
                    loadingSpinner.visibility = View.INVISIBLE
                }
                override fun onFailure(call: Call<DNDAPIResponse>, t: Throwable) {
                    Log.d(TAG, "getSpells() call failed: $t")
                    loadingSpinner.visibility = View.INVISIBLE
                }
            })
        }
    }
}
