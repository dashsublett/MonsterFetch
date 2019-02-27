package com.example.dsublett.monsterfetch.fragments

import com.example.dsublett.monsterfetch.services.ItemListType
import com.example.dsublett.monsterfetch.services.ServiceProxy
import com.inmotionsoftware.promise.catch
import com.inmotionsoftware.promise.then

class SpellsListFragment : ItemListFragment() {
    override fun fetchData() =
        ServiceProxy.dndService.getList(ItemListType.Spells).then {
            this.buildUI(it)
        }.catch {
            this.logFailure(it)
        }
}
