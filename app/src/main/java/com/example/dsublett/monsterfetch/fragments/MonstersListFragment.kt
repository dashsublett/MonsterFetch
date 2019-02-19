package com.example.dsublett.monsterfetch.fragments

import android.util.Log
import com.example.dsublett.monsterfetch.services.DndApiService
import com.example.dsublett.monsterfetch.services.DndServiceImp
import java.lang.Exception

class MonstersListFragment : ItemListFragment() {
    override fun fetchData() = DndServiceImp.getMonsters(
        { DndApiService.create().getMonsters().enqueue(this.fetchCallback) },
        { t ->
            try {
                throw t
            } catch (e: Exception) {
                Log.d(this@MonstersListFragment::class.java.canonicalName, "$e")
            }
        }
    )
}
