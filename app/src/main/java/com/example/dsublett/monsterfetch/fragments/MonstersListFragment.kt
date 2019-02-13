package com.example.dsublett.monsterfetch.fragments

import com.example.dsublett.monsterfetch.services.DndApiService

class MonstersListFragment : ItemListFragment() {
    override fun fetchData() = DndApiService.create().getMonsters().enqueue(this.fetchCallback)
}
