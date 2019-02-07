package com.example.dsublett.monsterfetch.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.dsublett.monsterfetch.R
import com.example.dsublett.monsterfetch.fragments.ClassesFragment
import com.example.dsublett.monsterfetch.fragments.MonstersFragment
import com.example.dsublett.monsterfetch.fragments.SpellsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Launch app with monsters fragment and don't add to back stack
        val fragmentTransaction = this.supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.listContainer, MonstersFragment())
        fragmentTransaction.commit()

        this.navbarView.setOnNavigationItemSelectedListener{ item ->
            when(item.itemId) {
                R.id.monstersBtn -> {
                    this.replaceRvFragment(MonstersFragment())
                }
                R.id.spellsBtn -> {
                    this.replaceRvFragment(SpellsFragment())
                }
                R.id.classesBtn -> {
                    this.replaceRvFragment(ClassesFragment())
                }
            }
            true
        }
    }
    private fun replaceRvFragment(fragment: Fragment) {
        val fragmentTransaction = this.supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.listContainer, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}
