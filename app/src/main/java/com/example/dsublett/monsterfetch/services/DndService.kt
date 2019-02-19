package com.example.dsublett.monsterfetch.services

import com.example.dsublett.monsterfetch.models.*

interface DndService {
    fun getMonsters(success: (List<ResponseItem>) -> Unit, failure: (Throwable) -> Unit)
    fun getClasses(success: (List<ResponseItem>) -> Unit, failure: (Throwable) -> Unit)
    fun getSpells(success: (List<ResponseItem>) -> Unit, failure: (Throwable) -> Unit)
}
