package com.example.dsublett.monsterfetch.fragments

import android.util.Log
import com.example.dsublett.monsterfetch.services.DndApiService
import com.example.dsublett.monsterfetch.services.DndServiceImp
import java.lang.Exception

class SpellsListFragment : ItemListFragment() {
    override fun fetchData() = DndServiceImp.getSpells(
        { DndApiService.create().getSpells().enqueue(this.fetchCallback) },
        { t ->
            try {
                throw t
            } catch (e: Exception) {
                Log.d(this@SpellsListFragment::class.java.canonicalName, "$e")
            }
        }
    )
}
