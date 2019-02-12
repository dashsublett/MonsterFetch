package com.example.dsublett.monsterfetch.activities

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.dsublett.monsterfetch.R
import com.example.dsublett.monsterfetch.fragments.*
import com.example.dsublett.monsterfetch.models.*
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity(), Showable {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Launch app with monsters fragment and don't add to back stack
        val fragmentTransaction = this.supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.listContainer, MonstersFragment())
        fragmentTransaction.commit()

        this.navbarView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.monstersBtn -> this.replaceRvFragment(MonstersFragment())
                R.id.spellsBtn -> this.replaceRvFragment(SpellsFragment())
                R.id.classesBtn -> this.replaceRvFragment(ClassesFragment())
                R.id.favoritesBtn -> this.replaceRvFragment(FavoritesFragment())
            }
            true
        }
    }

    override fun showDetails(responseItem: ResponseItem) {
        val detailClass = when (
            responseItem.url.removePrefix("http://www.dnd5eapi.co/api/").split("/")[0]
            ) {
            "monsters" -> MonsterDetail::class.java
            "classes" -> ClassDetail::class.java
            "spells" -> SpellDetail::class.java
            else -> throw Exception(
                "That endpoint is either invalid or has not been implemented yet."
            )
        }
        val theIntent = Intent(this, detailClass).apply {
            action = Intent.ACTION_VIEW
            putExtras(Bundle().apply {
                putString("name", responseItem.name)
                putString("url", responseItem.url)
            })
        }
        startActivity(theIntent)
    }

    private fun replaceRvFragment(fragment: Fragment) {
        val fragmentTransaction = this.supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.listContainer, fragment)
        fragmentTransaction.commit()
    }
}

interface Showable {
    fun showDetails(responseItem: ResponseItem)
}
