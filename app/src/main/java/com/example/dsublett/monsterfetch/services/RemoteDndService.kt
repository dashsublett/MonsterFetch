package com.example.dsublett.monsterfetch.services

import com.example.dsublett.monsterfetch.models.DNDAPIResponse
import com.example.dsublett.monsterfetch.models.ResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDndService : DndService {
    override fun getMonsters(success: (List<ResponseItem>) -> Unit, failure: (Throwable) -> Unit) {
        DndApiService.create().getMonsters().enqueue(object : Callback<DNDAPIResponse> {
            override fun onResponse(call: Call<DNDAPIResponse>, response: Response<DNDAPIResponse>) {
                success(response.body()?.results ?: emptyList())
            }

            override fun onFailure(call: Call<DNDAPIResponse>, t: Throwable) {
                failure(t)
            }
        })
    }

    override fun getClasses(success: (List<ResponseItem>) -> Unit, failure: (Throwable) -> Unit) {
        DndApiService.create().getClasses().enqueue(object : Callback<DNDAPIResponse> {
            override fun onResponse(call: Call<DNDAPIResponse>, response: Response<DNDAPIResponse>) {
                success(response.body()?.results ?: emptyList())
            }

            override fun onFailure(call: Call<DNDAPIResponse>, t: Throwable) {
                failure(t)
            }
        })
    }

    override fun getSpells(success: (List<ResponseItem>) -> Unit, failure: (Throwable) -> Unit) {
        DndApiService.create().getSpells().enqueue(object : Callback<DNDAPIResponse> {
            override fun onResponse(call: Call<DNDAPIResponse>, response: Response<DNDAPIResponse>) {
                success(response.body()?.results ?: emptyList())
            }

            override fun onFailure(call: Call<DNDAPIResponse>, t: Throwable) {
                failure(t)
            }
        })
    }
}
