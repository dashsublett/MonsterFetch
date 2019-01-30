package com.example.dsublett.monsterfetch

import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import com.squareup.moshi.Moshi
import kotlinx.android.synthetic.main.monster_item_list.view.*
import java.net.URL

class ItemFragment : Fragment(), View.OnClickListener {
    private lateinit var monsterListRv: RecyclerView
    private var progressBarRef: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        /* Initialize button references and set their onClick listeners.
         */
        super.onCreate(savedInstanceState)
        val fetchBtn = activity?.findViewById<Button>(R.id.fetchBtn)
        val clearBtn = activity?.findViewById<Button>(R.id.clearBtn)

        fetchBtn?.setOnClickListener(this)
        clearBtn?.setOnClickListener(this)
    }
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        /* Inflate RecyclerView "monster_item_list", setting the layoutManager and adapter for it,
         * set the monsterListRv reference after the containing view has been created, and return a
         * reference to the RecyclerView.
         */
        val view =
                (inflater.inflate(R.layout.monster_item_list, container, false)
                        as RecyclerView).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = MonsterAdapter(mutableListOf())
        }
        this.monsterListRv = view.rvMonsterList

        return view
    }
    override fun onClick(view: View) {
        /* Handle click event based on id of view (button). For fetch button, pass a callback to the
         * constructor of UpdateMonsterList to retrieve a List<Monster> For clear button, .clear()
         * is just being called on the adapter's List<Monster>.
         * In either case, notifyDataSetChanged() is called on the adapter after modifying the list
         * to let it know it should update its UI.
         */
        when(view.id) {
            R.id.fetchBtn -> {
                this.progressBarRef = activity?.findViewById(R.id.loadingSpinner)
                this.progressBarRef?.visibility = View.VISIBLE
                UpdateMonsterList { ml ->
                    (this.monsterListRv.adapter as? MonsterAdapter)?.monsters = ml.toMutableList()
                    this.monsterListRv.adapter?.notifyDataSetChanged()
                    this.progressBarRef?.visibility = View.INVISIBLE
                }.execute()
            }
            R.id.clearBtn -> {
                (this.monsterListRv.adapter as? MonsterAdapter)?.monsters?.clear()
                this.monsterListRv.adapter?.notifyDataSetChanged()
            }
        }
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
