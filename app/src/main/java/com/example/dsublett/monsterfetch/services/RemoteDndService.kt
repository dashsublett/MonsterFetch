package com.example.dsublett.monsterfetch.services

import com.example.dsublett.monsterfetch.models.*
import com.inmotionsoftware.promise.Promise
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDndService : DndService {
    override fun getList(listType: ItemListType): Promise<List<ResponseItem>> =
        when (listType) {
            ItemListType.Monsters ->
                Promise { resolve, reject ->
                    RetrofitDndApi
                        .create()
                        .getMonsters()
                        .enqueue(ListCallback(resolve, reject))
                }

            ItemListType.Classes ->
                Promise { resolve, reject ->
                    RetrofitDndApi
                        .create()
                        .getClasses()
                        .enqueue(ListCallback(resolve, reject))
                }

            ItemListType.Spells ->
                Promise { resolve, reject ->
                    RetrofitDndApi
                        .create()
                        .getSpells()
                        .enqueue(ListCallback(resolve, reject))
                }
        }

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

    override fun getMonster(index: String): Promise<MonsterResponse?> =
        Promise { resolve, reject ->
            RetrofitDndApi.create().getMonster(index).enqueue(this.detailCallback(resolve, reject))
        }


    override fun getClass(index: String): Promise<ClassResponse?> =
        Promise { resolve, reject ->
            RetrofitDndApi.create().getClass(index).enqueue(this.detailCallback(resolve, reject))
        }

    override fun getSpell(index: String): Promise<SpellResponse?> =
        Promise { resolve, reject ->
            RetrofitDndApi.create().getSpell(index).enqueue(this.detailCallback(resolve, reject))
        }

    private fun <T> detailCallback(resolve: (T?) -> Unit, reject: (Throwable) -> Unit): Callback<T> =
        object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                resolve(response.body())
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                reject(t)
            }
        }
}
