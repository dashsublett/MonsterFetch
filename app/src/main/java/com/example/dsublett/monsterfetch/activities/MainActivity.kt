package com.example.dsublett.monsterfetch.activities

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.dsublett.monsterfetch.R
import com.example.dsublett.monsterfetch.fragments.*
import com.example.dsublett.monsterfetch.models.*
import com.example.dsublett.monsterfetch.utils.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_list.*
import java.lang.Exception

class MainActivity : AppCompatActivity(), Showable {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.actionBar?.setDisplayHomeAsUpEnabled(true)

        val fragmentTransaction = this.supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.listContainer, MonstersList())
        fragmentTransaction.commit()

        this.navbarView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.monstersBtn -> this.replaceRvFragment(MonstersList())
                R.id.spellsBtn -> this.replaceRvFragment(SpellsList())
                R.id.classesBtn -> this.replaceRvFragment(ClassesList())
                R.id.favoritesBtn -> this.replaceRvFragment(FavoritesList())
            }
            true
        }
    }

    override fun showDetails(responseItem: ResponseItem) {
        this.loadingSpinner.visibility = View.VISIBLE
        val detailClass = when (UrlParse.getEndpoint(responseItem.url)) {
            "monsters" -> MonsterDetail::class.java
            "classes" -> ClassDetail::class.java
            "spells" -> SpellDetail::class.java
            else -> throw Exception("That endpoint is invalid or has not been implemented yet.")
        }
        val theIntent = Intent(this, detailClass).apply {
            action = Intent.ACTION_VIEW
            putExtras(Bundle().apply { putString("url", responseItem.url) })
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
