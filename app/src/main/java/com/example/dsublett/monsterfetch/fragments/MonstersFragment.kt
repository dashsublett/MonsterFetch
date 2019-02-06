package com.example.dsublett.monsterfetch.fragments

import com.example.dsublett.monsterfetch.services.DndApiService

class MonstersFragment : DataFragment() {
    override fun fetchData() = DndApiService.create().getMonsters().enqueue(fetchCallback)
}
