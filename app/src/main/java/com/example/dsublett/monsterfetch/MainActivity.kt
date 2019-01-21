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
    private val monsters = mutableListOf<Monster>() // Data set for recycler view

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.rvMonsterList.layoutManager = LinearLayoutManager(this)
        this.rvMonsterList.adapter = MonsterAdapter(monsters, this)
    }
    fun updateDataSet(view: View) {
        // Asynchronously retrieve list of monsters, parse into List, add to data set
        UpdateMonsterList(this.monsters).execute()
    }
    fun clearDataSet(view: View) {
        (monsters as? ArrayList)?.clear()
        this.rvMonsterList.adapter?.notifyDataSetChanged()
    }
    fun updateView(view: View) {
        // Notify view that data set changed
        this.rvMonsterList.adapter?.notifyDataSetChanged()
    }
}

private class UpdateMonsterList(val monsterList: List<Monster>)
    : AsyncTask<Unit, Unit, List<Monster>>() {
    private val moshi = Moshi.Builder().build()
    private val responseAdapter = moshi.adapter(Response::class.java)

    override fun doInBackground(vararg p0: Unit?): List<Monster> {
        // Return List populated with monster names
        val retList = mutableListOf<Monster>()
        val jsonStr = URL("http://www.dnd5eapi.co/api/monsters").readText()
        val parsedResponse = responseAdapter.fromJson(jsonStr)
        val monsterList = parsedResponse?.results
        val monsterIt: Iterator<Monster>? = monsterList?.iterator()
        if (monsterIt != null) {
            while (monsterIt.hasNext()) {
                retList.add(monsterIt.next())
            }
        }
        return retList
    }
    override fun onPostExecute(result: List<Monster>) {
        // Add all monsters from ArrayList to data set
        super.onPostExecute(result)
        (monsterList as? ArrayList)?.addAll(result)
    }
}
