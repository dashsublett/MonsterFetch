package com.example.dsublett.monsterfetch.fragments

import com.example.dsublett.monsterfetch.services.DndApiService

class ClassesListFragment : ItemListFragment() {
    override fun fetchData() = DndApiService.create().getClasses().enqueue(this.fetchCallback)
}
