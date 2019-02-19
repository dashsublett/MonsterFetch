package com.example.dsublett.monsterfetch.fragments

import android.util.Log
import com.example.dsublett.monsterfetch.services.DndApiService
import com.example.dsublett.monsterfetch.services.DndServiceImp
import java.lang.Exception

class ClassesListFragment : ItemListFragment() {
    override fun fetchData() = DndServiceImp.getClasses(
        { DndApiService.create().getClasses().enqueue(this.fetchCallback) },
        { t ->
            try {
                throw t
            } catch (e: Exception) {
                Log.d(this@ClassesListFragment::class.java.canonicalName, "$e")
            }
        }
    )
}
