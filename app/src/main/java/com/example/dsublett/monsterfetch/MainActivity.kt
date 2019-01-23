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
        // Asynchronously retrieve list of monsters, parse into List, add to data set
        UpdateMonsterList { ml ->
            val parsedResponse = Moshi
                    .Builder()
                    .build()
                    .adapter(Response::class.java)
                    .fromJson(URL("http://www.dnd5eapi.co/api/monsters").readText())
            val monsterList = parsedResponse?.results
            val monsterIt: Iterator<Monster>? = monsterList?.iterator()
            if (monsterIt != null) {
                while (monsterIt.hasNext()) {
                    (ml as MutableList).add(monsterIt.next())
                }
            }
        }.execute()
        this.rvMonsterList.adapter?.notifyDataSetChanged()
        this.progressBar.visibility = View.INVISIBLE
    }
    fun clearDataSet(view: View) {
        MonsterAdapter.monsters.clear()
        this.rvMonsterList.adapter?.notifyDataSetChanged()
    }
}

private class UpdateMonsterList(private val callback: ((List<Monster>) -> Unit))
    : AsyncTask<Unit, Unit, Unit>() {
    override fun doInBackground(vararg p0: Unit) {
        callback(MonsterAdapter.monsters)
    }
}
