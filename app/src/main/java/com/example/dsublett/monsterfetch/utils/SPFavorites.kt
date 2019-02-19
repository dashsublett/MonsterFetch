package com.example.dsublett.monsterfetch.utils

import android.content.SharedPreferences
import com.example.dsublett.monsterfetch.models.FavoritesList
import com.squareup.moshi.Moshi

object SPFavorites {
    private fun addFavorite(list: String, responseItem: String, prefs: SharedPreferences) = when {
        prefs.getString(list, "") == "[]" -> prefs
            .edit()
            .putString(list, "[$responseItem]")
            .apply()
        else -> {
            val currentVal = prefs.getString(list, "")
            prefs
                .edit()
                .putString(
                    list,
                    "${currentVal.slice(0..(currentVal.length - 2))},$responseItem]")
                .apply()
        }
    }

    fun isFavorited(list: String, responseItem: String, prefs: SharedPreferences): Boolean =
        prefs.getString(list, "").contains(responseItem)

    fun addIfNotFavorited(list: String, responseItem: String, prefs: SharedPreferences) {
        if (!(isFavorited(list, responseItem, prefs))) {
            addFavorite(list, responseItem, prefs)
        }
    }

    fun getFavorites(prefs: SharedPreferences?): FavoritesList {
        val stringList = """
                {
                    "monsterFavorites":${prefs?.getString("monsterFavorites", "")},
                    "classFavorites":${prefs?.getString("classFavorites", "")},
                    "spellFavorites":${prefs?.getString("spellFavorites", "")}
                }"""

        val favoritesListAdapter = Moshi
            .Builder()
            .build()
            .adapter(FavoritesList::class.java)

        return favoritesListAdapter.fromJson(stringList)
            ?: FavoritesList(emptyList(), emptyList(), emptyList())
    }
}
