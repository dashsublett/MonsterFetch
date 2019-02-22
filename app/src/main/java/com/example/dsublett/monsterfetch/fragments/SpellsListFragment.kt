package com.example.dsublett.monsterfetch.fragments

import com.example.dsublett.monsterfetch.services.ItemListType
import com.example.dsublett.monsterfetch.services.ServiceProxy

class SpellsListFragment : ItemListFragment() {
    override fun fetchData() =
        ServiceProxy.dndService.getList(ItemListType.Spells, this::buildUI, this::logFailure)
}
