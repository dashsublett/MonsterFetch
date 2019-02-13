package com.example.dsublett.monsterfetch.fragments

import com.example.dsublett.monsterfetch.services.DndApiService

class SpellsListFragment : ItemListFragment() {
    override fun fetchData() = DndApiService.create().getSpells().enqueue(fetchCallback)
}
