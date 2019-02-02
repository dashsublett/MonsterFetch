package com.example.dsublett.monsterfetch.services

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface DNDAPIService {
    @GET("monsters")
    fun getMonsters(): Call<DNDAPIResponse>

    @GET("classes")
    fun getClasses(): Call<DNDAPIResponse>

    @GET("spells")
    fun getSpells(): Call<DNDAPIResponse>

    companion object Factory {
        fun create(): DNDAPIService {
            val retrofit = Retrofit.Builder()
                    .baseUrl("http://www.dnd5eapi.co/api/")
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build()

            return retrofit.create(DNDAPIService::class.java)
        }
    }
}
