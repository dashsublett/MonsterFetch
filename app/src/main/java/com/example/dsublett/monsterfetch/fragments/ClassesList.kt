package com.example.dsublett.monsterfetch.fragments

import com.example.dsublett.monsterfetch.services.DndApiService

class ClassesList : ItemList() {
    override fun fetchData() = DndApiService.create().getClasses().enqueue(fetchCallback)
}
