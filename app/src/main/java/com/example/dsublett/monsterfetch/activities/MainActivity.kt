package com.example.dsublett.monsterfetch.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.dsublett.monsterfetch.R
import com.example.dsublett.monsterfetch.fragments.MonstersFragment
import com.example.dsublett.monsterfetch.fragments.NavBarFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.navBarContainer, NavBarFragment())
        fragmentTransaction.replace(R.id.listContainer, MonstersFragment())
        fragmentTransaction.commit()
    }
}
