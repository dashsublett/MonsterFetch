package com.example.dsublett.monsterfetch.fragments

import com.example.dsublett.monsterfetch.services.DndApiService

class ClassesFragment : DataFragment() {
    override fun fetchData() = DndApiService.create().getClasses().enqueue(fetchCallback)
}
