package com.example.dsublett.monsterfetch.services

import android.os.AsyncTask
import com.example.dsublett.monsterfetch.Monster
import com.example.dsublett.monsterfetch.Response
import com.squareup.moshi.Moshi
import java.net.URL

class UpdateMonsterList(private val callback: ((List<Monster>) -> Unit))
    : AsyncTask<Unit, Unit, List<Monster>>() {
    override fun doInBackground(vararg p0: Unit): List<Monster>? {
        val monsterList = Moshi
                .Builder()
                .build()
                .adapter(Response::class.java)
                .fromJson(URL("http://www.dnd5eapi.co/api/monsters").readText())
                ?.results
        return monsterList ?: emptyList()
    }
    override fun onPostExecute(result: List<Monster>) {
        super.onPostExecute(result)
        this.callback(result)
    }
}
