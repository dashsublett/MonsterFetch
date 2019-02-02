package com.example.dsublett.monsterfetch

import com.example.dsublett.monsterfetch.services.DNDAPIService
import com.example.dsublett.monsterfetch.services.MonstersResponse

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.monster_item_list.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemFragment : Fragment() {
    private val apiService = DNDAPIService.create()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.monster_item_list, container, false).apply {
            // Fetch monster list and animate loading spinner
            this.loadingSpinner.visibility = View.VISIBLE
            apiService.getMonsters().enqueue(object: Callback<MonstersResponse> {
                override fun onResponse(call: Call<MonstersResponse>,
                                        response: Response<MonstersResponse>) {
                    (rvMonsterList.adapter as MonsterAdapter).monsters =
                            response.body()?.results!!.toMutableList()
                    rvMonsterList.adapter?.notifyDataSetChanged()
                }
                override fun onFailure(call: Call<MonstersResponse>, t: Throwable) {
                    Log.d(this::class.java.simpleName, "call failed")
                }
            })
            this.loadingSpinner.visibility = View.INVISIBLE

            // Set clear button click listener
            this.clearBtn.setOnClickListener{
                (this.rvMonsterList.adapter as MonsterAdapter).monsters.clear()
                this.rvMonsterList.adapter?.notifyDataSetChanged()
            }
            // Set layout manager and adapter for recycler view
            this.rvMonsterList.layoutManager = LinearLayoutManager(context)
            this.rvMonsterList.adapter = MonsterAdapter(mutableListOf())
        }
    }
}
