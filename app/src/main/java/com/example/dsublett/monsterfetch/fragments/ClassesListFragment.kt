package com.example.dsublett.monsterfetch.fragments

import com.example.dsublett.monsterfetch.services.ServiceProxy

class ClassesListFragment : ItemListFragment() {
    override fun fetchData() = ServiceProxy.dndService.getClasses(this::buildUI, this::logFailure)
}
