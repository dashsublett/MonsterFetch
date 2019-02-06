package com.example.dsublett.monsterfetch.fragments

import com.example.dsublett.monsterfetch.services.DndApiService

class SpellsFragment : DataFragment() {
    override fun fetchData() = DndApiService.create().getSpells().enqueue(fetchCallback)
}
