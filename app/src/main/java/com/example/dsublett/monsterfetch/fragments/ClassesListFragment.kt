package com.example.dsublett.monsterfetch.fragments

import com.example.dsublett.monsterfetch.services.ItemListType
import com.example.dsublett.monsterfetch.services.ServiceProxy

class ClassesListFragment : ItemListFragment() {
    override fun fetchData() =
        ServiceProxy.dndService.getList(ItemListType.Classes, this::buildUI, this::logFailure)
}
