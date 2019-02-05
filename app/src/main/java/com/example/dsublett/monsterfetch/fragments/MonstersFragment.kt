package com.example.dsublett.monsterfetch.fragments

import com.example.dsublett.monsterfetch.services.DNDAPIService

class MonstersFragment : DataFragment() {
    override fun fetchData() {
        DNDAPIService.create().getMonsters().enqueue(fetchCallback)
    }
}
