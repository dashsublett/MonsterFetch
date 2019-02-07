package com.example.dsublett.monsterfetch.services

import com.example.dsublett.monsterfetch.models.DNDAPIResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface DndApiService {
    @GET("monsters")
    fun getMonsters(): Call<DNDAPIResponse>

    @GET("classes")
    fun getClasses(): Call<DNDAPIResponse>

    @GET("spells")
    fun getSpells(): Call<DNDAPIResponse>

    companion object {
        fun create(): DndApiService =
            Retrofit
                .Builder()
                .baseUrl("http://www.dnd5eapi.co/api/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(DndApiService::class.java)
    }
}
