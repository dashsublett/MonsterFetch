package com.example.dsublett.monsterfetch.fragments

import com.example.dsublett.monsterfetch.services.DndApiService

class SpellsList : ItemList() {
    override fun fetchData() = DndApiService.create().getSpells().enqueue(fetchCallback)
}
