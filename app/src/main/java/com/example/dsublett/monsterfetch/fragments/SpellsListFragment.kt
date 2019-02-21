package com.example.dsublett.monsterfetch.fragments

import com.example.dsublett.monsterfetch.services.ServiceProxy

class SpellsListFragment : ItemListFragment() {
    override fun fetchData() = ServiceProxy.dndService.getSpells(this::buildUI, this::logFailure)
}
