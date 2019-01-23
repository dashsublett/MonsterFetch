package com.example.dsublett.monsterfetch

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.squareup.moshi.Moshi
import java.net.URL
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.rvMonsterList.layoutManager = LinearLayoutManager(this)
        this.rvMonsterList.adapter = MonsterAdapter(this)
    }
    fun fetchMonsters(view: View) {
        this.progressBar.visibility = View.VISIBLE
        UpdateMonsterList{ ml ->
            (this.rvMonsterList.adapter as? MonsterAdapter)?.monsters = ml.toMutableList()
            this.progressBar.visibility = View.INVISIBLE
        }.execute()
    }
    fun clearDataSet(view: View) {
        (this.rvMonsterList.adapter as? MonsterAdapter)?.monsters = mutableListOf()
    }
}
private class UpdateMonsterList(private val callback: ((List<Monster>) -> Unit))
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
