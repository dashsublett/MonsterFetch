package com.example.dsublett.monsterfetch.services

import com.example.dsublett.monsterfetch.models.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDndService : DndService {
    class ListCallback(
        val success: (List<ResponseItem>) -> Unit,
        val failure: (Throwable) -> Unit
    ) : Callback<DNDAPIResponse> {
        override fun onResponse(call: Call<DNDAPIResponse>, response: Response<DNDAPIResponse>) {
            success(response.body()?.results ?: emptyList())
        }

        override fun onFailure(call: Call<DNDAPIResponse>, t: Throwable) {
            failure(t)
        }
    }

    override fun getList(
        listType: ItemListType,
        success: (List<ResponseItem>) -> Unit,
        failure: (Throwable) -> Unit
    ) =
        when (listType) {
            ItemListType.Monsters ->
                RetrofitDndApi.create().getMonsters().enqueue(ListCallback(success, failure))
            ItemListType.Classes ->
                RetrofitDndApi.create().getClasses().enqueue(ListCallback(success, failure))
            ItemListType.Spells ->
                RetrofitDndApi.create().getSpells().enqueue(ListCallback(success, failure))
        }

    override fun getMonster(index: String, success: (MonsterResponse?) -> Unit, failure: (Throwable) -> Unit) {
        RetrofitDndApi.create().getMonster(index).enqueue(object : Callback<MonsterResponse> {
            override fun onResponse(call: Call<MonsterResponse>, response: Response<MonsterResponse>) {
                success(response.body())
            }

            override fun onFailure(call: Call<MonsterResponse>, t: Throwable) {
                failure(t)
            }
        })
    }

    override fun getClass(index: String, success: (ClassResponse?) -> Unit, failure: (Throwable) -> Unit) {
        RetrofitDndApi.create().getClass(index).enqueue(object : Callback<ClassResponse> {
            override fun onResponse(call: Call<ClassResponse>, response: Response<ClassResponse>) {
                success(response.body())
            }

            override fun onFailure(call: Call<ClassResponse>, t: Throwable) {
                failure(t)
            }
        })
    }

    override fun getSpell(index: String, success: (SpellResponse?) -> Unit, failure: (Throwable) -> Unit) {
        RetrofitDndApi.create().getSpell(index).enqueue(object : Callback<SpellResponse> {
            override fun onResponse(call: Call<SpellResponse>, response: Response<SpellResponse>) {
                success(response.body())
            }

            override fun onFailure(call: Call<SpellResponse>, t: Throwable) {
                failure(t)
            }
        })
    }
}
