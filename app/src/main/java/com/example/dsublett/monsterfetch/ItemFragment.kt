package com.example.dsublett.monsterfetch

import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
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
        /* Inflate RecyclerView "monster_item_list", setting the layoutManager and adapter for it
         * and return a reference to the View that is the root of the inflated layout.
         */
        val mlView = inflater.inflate(R.layout.monster_item_list, container, false)
        (mlView?.rvMonsterList as RecyclerView).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = MonsterAdapter(mutableListOf())
        }
        mlView.fetchBtn?.setOnClickListener{
            mlView.loadingSpinner?.visibility = View.VISIBLE
            UpdateMonsterList { ml ->
                (mlView.rvMonsterList?.adapter as? MonsterAdapter)?.monsters =
                        ml.toMutableList()
                mlView.rvMonsterList?.adapter?.notifyDataSetChanged()
                mlView.loadingSpinner?.visibility = View.INVISIBLE
            }.execute()
        }
        mlView.clearBtn?.setOnClickListener{
            (mlView.rvMonsterList?.adapter as? MonsterAdapter)?.monsters?.clear()
            mlView.rvMonsterList?.adapter?.notifyDataSetChanged()
        }
        return mlView
    }
}

private class UpdateMonsterList(private val callback: ((List<Monster>) -> Unit))
    : AsyncTask<Unit, Unit, List<Monster>>() {
    /* Performs GET request, parses the response and returns that list or empty list depending on
     * success of the operation.
     */
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
