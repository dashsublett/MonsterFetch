package com.example.dsublett.monsterfetch

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dsublett.monsterfetch.services.DNDAPIResponse
import com.example.dsublett.monsterfetch.services.DNDAPIService
import kotlinx.android.synthetic.main.fragment_nav_bar.view.*
import kotlinx.android.synthetic.main.item_list.*
import kotlinx.android.synthetic.main.item_list.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NavBarFragment : Fragment() {
    private val apiService = DNDAPIService.create()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_nav_bar, container, false).apply {
            // Set m button click listener
            this.monstersButton.setOnClickListener{
                preFetch()
                apiService.getMonsters().enqueue(object: Callback<DNDAPIResponse> {
                    override fun onResponse(call: Call<DNDAPIResponse>,
                                            response: Response<DNDAPIResponse>) {
                        (activity?.rvItemList?.adapter as ItemAdapter).responseItems =
                                response.body()?.results!!.toMutableList()
                        postFetch()
                    }
                    override fun onFailure(call: Call<DNDAPIResponse>, t: Throwable) {
                        Log.d(this::class.java.simpleName, "call failed")
                        loadingSpinner.visibility = View.INVISIBLE
                    }
                })
            }
            // Set c button click listener
            this.classesButton.setOnClickListener{
                preFetch()
                apiService.getClasses().enqueue(object: Callback<DNDAPIResponse> {
                    override fun onResponse(call: Call<DNDAPIResponse>,
                                            response: Response<DNDAPIResponse>) {
                        (activity?.rvItemList?.adapter as ItemAdapter).responseItems =
                                response.body()?.results!!.toMutableList()
                        postFetch()
                    }
                    override fun onFailure(call: Call<DNDAPIResponse>, t: Throwable) {
                        Log.d(this::class.java.simpleName, "call failed")
                        loadingSpinner.visibility = View.INVISIBLE
                    }
                })
            }
            // Set s button click listener
            this.spellsButton.setOnClickListener{
                preFetch()
                apiService.getSpells().enqueue(object: Callback<DNDAPIResponse> {
                    override fun onResponse(call: Call<DNDAPIResponse>,
                                            response: Response<DNDAPIResponse>) {
                        (activity?.rvItemList?.adapter as ItemAdapter).responseItems =
                                response.body()?.results!!.toMutableList()
                        postFetch()
                    }
                    override fun onFailure(call: Call<DNDAPIResponse>, t: Throwable) {
                        Log.d(this::class.java.simpleName, "call failed")
                        loadingSpinner.visibility = View.INVISIBLE
                    }
                })
            }
        }
    }
    // Utility functions to reduce code duplication
    private fun preFetch() {
        (activity?.rvItemList?.adapter as ItemAdapter).responseItems.clear()
        activity?.rvItemList?.adapter?.notifyDataSetChanged()
        activity?.loadingSpinner?.visibility = View.VISIBLE
    }
    private fun postFetch() {
        activity?.rvItemList?.adapter?.notifyDataSetChanged()
        activity?.loadingSpinner?.visibility = View.INVISIBLE
    }
}
