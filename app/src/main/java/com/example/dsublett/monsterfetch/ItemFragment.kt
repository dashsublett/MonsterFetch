package com.example.dsublett.monsterfetch

import com.example.dsublett.monsterfetch.services.DNDAPIService
import com.example.dsublett.monsterfetch.services.DNDAPIResponse

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_list.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemFragment : Fragment() {
    private val apiService = DNDAPIService.create()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.item_list, container, false).apply {
            // Set layout manager and adapter for recycler view
            this.rvItemList.layoutManager = LinearLayoutManager(context)
            this.rvItemList.adapter = ItemAdapter(mutableListOf())

            // Fetch monster list and animate loading spinner
            this.loadingSpinner.visibility = View.VISIBLE
            apiService.getMonsters().enqueue(object: Callback<DNDAPIResponse> {
                override fun onResponse(call: Call<DNDAPIResponse>,
                                        response: Response<DNDAPIResponse>) {
                    (rvItemList.adapter as ItemAdapter).responseItems =
                            response.body()?.results!!.toMutableList()
                    rvItemList.adapter?.notifyDataSetChanged()
                }
                override fun onFailure(call: Call<DNDAPIResponse>, t: Throwable) {
                    Log.d(this::class.java.simpleName, "call failed")
                }
            })
            this.loadingSpinner.visibility = View.INVISIBLE
        }
    }
}
