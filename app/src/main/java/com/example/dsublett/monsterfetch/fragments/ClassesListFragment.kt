package com.example.dsublett.monsterfetch.fragments

import com.example.dsublett.monsterfetch.services.ItemListType
import com.example.dsublett.monsterfetch.services.ServiceProxy
import com.inmotionsoftware.promise.catch
import com.inmotionsoftware.promise.then

class ClassesListFragment : ItemListFragment() {
    override fun fetchData() =
        ServiceProxy.dndService.getList(ItemListType.Classes).then {
            this.buildUI(it)
        }.catch {
            this.logFailure(it)
        }
}
