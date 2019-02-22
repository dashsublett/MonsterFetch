package com.example.dsublett.monsterfetch.services

import com.example.dsublett.monsterfetch.models.*


enum class ItemListType {
    Monsters, Classes, Spells
}

interface DndService {
    fun getList(
        listType: ItemListType,
        success: (List<ResponseItem>) -> Unit,
        failure: (Throwable) -> Unit
    )
    fun getMonster(index: String, success: (MonsterResponse?) -> Unit, failure: (Throwable) -> Unit)
    fun getClass(index: String, success: (ClassResponse?) -> Unit, failure: (Throwable) -> Unit)
    fun getSpell(index: String, success: (SpellResponse?) -> Unit, failure: (Throwable) -> Unit)
}
