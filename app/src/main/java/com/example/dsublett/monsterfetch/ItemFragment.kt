package com.example.dsublett.monsterfetch

import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.moshi.Moshi
import kotlinx.android.synthetic.main.monster_item_list.view.*
import java.net.URL

class ItemFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.monster_item_list, container, false).apply {
            this.fetchBtn.setOnClickListener{
                this.loadingSpinner.visibility = View.VISIBLE
                UpdateMonsterList { ml ->
                    (this.rvMonsterList.adapter as MonsterAdapter).monsters = ml.toMutableList()
                    this.rvMonsterList.adapter?.notifyDataSetChanged()
                    this.loadingSpinner.visibility = View.INVISIBLE
                }.execute()
            }
            this.clearBtn.setOnClickListener{
                (this.rvMonsterList.adapter as MonsterAdapter).monsters.clear()
                this.rvMonsterList.adapter?.notifyDataSetChanged()
            }
            this.rvMonsterList.layoutManager = LinearLayoutManager(context)
            this.rvMonsterList.adapter = MonsterAdapter(mutableListOf())
        }
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
