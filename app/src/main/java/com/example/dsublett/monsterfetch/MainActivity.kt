package com.example.dsublett.monsterfetch

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.dsublett.monsterfetch.services.DNDAPIResponse
import com.example.dsublett.monsterfetch.services.DNDAPIService
import kotlinx.android.synthetic.main.item_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.navBarContainer, NavBarFragment())
        fragmentTransaction.replace(R.id.listContainer, ItemFragment())
        fragmentTransaction.commit()

        DNDAPIService.create().getMonsters().enqueue(object: Callback<DNDAPIResponse> {
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
    }
}
