package com.example.dsublett.monsterfetch.activities

import android.content.Context
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

        with(getSharedPreferences(
            "com.example.dsublett.monsterfetch.sharedPreferences",
            Context.MODE_PRIVATE
        )) {
            if (getString("monsterFavorites", "").isBlank()) {
                edit().putString("monsterFavorites", "[]").apply()
            }
            if (getString("classFavorites", "").isBlank()) {
                edit().putString("classFavorites", "[]").apply()
            }
            if (getString("spellFavorites", "").isBlank()) {
                edit().putString("spellFavorites", "[]").apply()
            }
        }

        this.actionBar?.setDisplayHomeAsUpEnabled(true)

        this.replaceRvFragment(MonstersListFragment())

        this.navbarView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.monstersBtn -> this.replaceRvFragment(MonstersListFragment())
                R.id.spellsBtn -> this.replaceRvFragment(SpellsListFragment())
                R.id.classesBtn -> this.replaceRvFragment(ClassesListFragment())
                R.id.favoritesBtn -> this.replaceRvFragment(FavoritesListFragment())
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
            putExtras(Bundle().apply { putParcelable("responseItem", responseItem) })
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
