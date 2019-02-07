package com.example.dsublett.monsterfetch.fragments

import com.example.dsublett.monsterfetch.services.DNDAPIService

class SpellsFragment : DataFragment() {
    override fun fetchData() {
        DNDAPIService.create().getSpells().enqueue(fetchCallback)
    }
}
