package com.example.dsublett.monsterfetch.fragments

import com.example.dsublett.monsterfetch.services.DNDAPIService

class ClassesFragment : DataFragment() {
    override fun fetchData() {
        DNDAPIService.create().getClasses().enqueue(fetchCallback)
    }
}
