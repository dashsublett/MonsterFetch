package com.example.dsublett.monsterfetch.fragments

import com.example.dsublett.monsterfetch.services.DndApiService

class MonstersList : ItemList() {
    override fun fetchData() = DndApiService.create().getMonsters().enqueue(fetchCallback)
}
