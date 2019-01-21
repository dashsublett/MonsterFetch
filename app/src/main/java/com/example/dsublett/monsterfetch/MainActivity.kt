package com.example.dsublett.monsterfetch

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.squareup.moshi.Moshi
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URL

class MainActivity : AppCompatActivity() {
    var monsters: ArrayList<String> = ArrayList() // Data set for recycler view

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.rv_monster_list.layoutManager = LinearLayoutManager(this)
        this.rv_monster_list.adapter = MonsterAdapter(monsters, this)
    }
    fun updateDataSet(view: View) {
        // Asynchronously retrieve list of monsters, parse into List, add to data set
        UpdateMonsterList(this).execute()
    }
    fun updateView(view: View) {
        // Notify view that data set changed
        this.rv_monster_list.adapter?.notifyDataSetChanged()
    }
}

class UpdateMonsterList(val activity: MainActivity)
    : AsyncTask<String, String, ArrayList<String>>() {
    private val moshi = Moshi.Builder().build()
    private val responseAdapter = moshi.adapter(Response::class.java)
    override fun doInBackground(vararg p0: String?): ArrayList<String> {
        // Return ArrayList populated with monster names
        val retList = ArrayList<String>()
        val jsonStr = URL("http://www.dnd5eapi.co/api/monsters").readText()
        val parsedResponse = responseAdapter.fromJson(jsonStr)
        val monsterList = parsedResponse?.results
        val monsterIt: Iterator<Monster>? = monsterList?.iterator()
        if (monsterIt != null)
            while(monsterIt.hasNext())
                retList.add(monsterIt.next().name)
        return retList
    }
    override fun onPostExecute(result: ArrayList<String>) {
        // Add all monsters from ArrayList to data set
        super.onPostExecute(result)
        activity.monsters.addAll(result)
    }
}

data class Response(val count: Int, val results: List<Monster>)
data class Monster(val name: String, val url: String)
