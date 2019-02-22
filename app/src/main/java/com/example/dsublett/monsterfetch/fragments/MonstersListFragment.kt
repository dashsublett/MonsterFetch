package com.example.dsublett.monsterfetch.fragments

import com.example.dsublett.monsterfetch.services.ItemListType
import com.example.dsublett.monsterfetch.services.ServiceProxy

class MonstersListFragment : ItemListFragment() {
    override fun fetchData() =
        ServiceProxy.dndService.getList(ItemListType.Monsters, this::buildUI, this::logFailure)
}
