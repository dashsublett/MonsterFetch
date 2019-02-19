package com.example.dsublett.monsterfetch.services

import com.example.dsublett.monsterfetch.models.ResponseItem
import java.lang.Exception

object DndServiceImp : DndService {
    override fun getMonsters(success: (List<ResponseItem>) -> Unit, failure: (Throwable) -> Unit) {
        success(emptyList())
        failure(Exception("getMonsters exception"))
    }

    override fun getClasses(success: (List<ResponseItem>) -> Unit, failure: (Throwable) -> Unit) {
        success(emptyList())
        failure(Exception("getClasses exception"))
    }

    override fun getSpells(success: (List<ResponseItem>) -> Unit, failure: (Throwable) -> Unit) {
        success(emptyList())
        failure(Exception("getSpells exception"))
    }
}
