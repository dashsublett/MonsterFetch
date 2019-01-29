package com.example.dsublett.monsterfetch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        /* Set activity_main.xml layout, create instance of ItemFragment, and use a fragment
         * transaction to add it to the Linear Layout with id of "fragment_container".
         */
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val itemFragment = ItemFragment()
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()

        transaction.add(R.id.fragment_container, itemFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
