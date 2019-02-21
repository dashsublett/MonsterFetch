package com.example.dsublett.monsterfetch.fragments

import com.example.dsublett.monsterfetch.services.ServiceProxy

class MonstersListFragment : ItemListFragment() {
    override fun fetchData() = ServiceProxy.dndService.getMonsters(this::buildUI, this::logFailure)
}
