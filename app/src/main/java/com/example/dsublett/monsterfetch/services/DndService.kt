package com.example.dsublett.monsterfetch.services

import com.example.dsublett.monsterfetch.models.*
import com.inmotionsoftware.promise.Promise

enum class ItemListType {
    Monsters, Classes, Spells
}

interface DndService {
    fun getList(listType: ItemListType) : Promise<List<ResponseItem>>
    fun getMonster(index: String) : Promise<MonsterResponse?>
    fun getClass(index: String) : Promise<ClassResponse?>
    fun getSpell(index: String) : Promise<SpellResponse?>
}
