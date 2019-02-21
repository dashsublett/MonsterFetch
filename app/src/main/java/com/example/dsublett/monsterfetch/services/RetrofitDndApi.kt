package com.example.dsublett.monsterfetch.services

import com.example.dsublett.monsterfetch.models.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitDndApi {
    @GET("monsters")
    fun getMonsters(): Call<DNDAPIResponse>

    @GET("classes")
    fun getClasses(): Call<DNDAPIResponse>

    @GET("spells")
    fun getSpells(): Call<DNDAPIResponse>

    @GET("monsters/{index}")
    fun getMonster(@Path("index")index: String): Call<MonsterResponse>

    @GET("spells/{index}")
    fun getSpell(@Path("index")index: String): Call<SpellResponse>

    @GET("classes/{index}")
    fun getClass(@Path("index")index: String): Call<ClassResponse>

    companion object {
        fun create(): RetrofitDndApi =
            Retrofit
                .Builder()
                .baseUrl("http://www.dnd5eapi.co/api/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(RetrofitDndApi::class.java)
    }
}
