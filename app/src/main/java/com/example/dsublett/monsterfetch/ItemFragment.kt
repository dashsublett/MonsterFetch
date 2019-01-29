package com.example.dsublett.monsterfetch

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.squareup.moshi.Moshi
import kotlinx.android.synthetic.main.monster_item_list.*
import java.net.URL

class ItemFragment : Fragment(), View.OnClickListener {
    private var listener: OnListFragmentInteractionListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.monster_item_list, container, false)
        (view as RecyclerView).layoutManager = LinearLayoutManager(context)
        view.adapter = MonsterAdapter(listener)

        val fetchBtn = activity?.findViewById<Button>(R.id.fetchBtn)
        fetchBtn?.setOnClickListener(this)

        return view
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnListFragmentInteractionListener
    }
    override fun onDetach() {
        super.onDetach()
        listener = null
    }
    override fun onClick(view: View) {
        when(view.id) {
            R.id.fetchBtn ->
//                this.progressBar.visibility = View.INVISIBLE
                UpdateMonsterList{ ml ->
                    (this.rvMonsterList.adapter as? MonsterAdapter)?.monsters = ml.toMutableList()
                    this.rvMonsterList.adapter?.notifyDataSetChanged()
//                this.progressBar.visibility = View.INVISIBLE
                }.execute()
            R.id.clearBtn -> {
                (this.rvMonsterList.adapter as? MonsterAdapter)?.monsters?.clear()
                this.rvMonsterList.adapter?.notifyDataSetChanged()
            }
        }
    }
    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(item: Monster)
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
