package com.example.dsublett.monsterfetch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), ItemFragment.OnListFragmentInteractionListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val itemFragment = ItemFragment()
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()

        transaction.add(R.id.fragment_container, itemFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    override fun onListFragmentInteraction(item: Monster) {}
}
