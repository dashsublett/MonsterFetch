package com.example.dsublett.monsterfetch.utils

import android.content.SharedPreferences
import com.example.dsublett.monsterfetch.models.FavoritesList
import com.squareup.moshi.Moshi

object SPFavorites {
    const val KEY = "com.example.dsublett.monsterfetch.sharedPreferences"
    fun addIfNotFavorited(list: String, responseItem: String, prefs: SharedPreferences) {
        when {
            isFavorited(list, responseItem, prefs) -> return // Remove favorite
            else -> this.addFavorite(list, responseItem, prefs)
        }
    }

    fun isFavorited(list: String, responseItem: String, prefs: SharedPreferences): Boolean =
        prefs.getString(list, "").contains(responseItem)

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

    private fun addFavorite(list: String, responseItem: String, prefs: SharedPreferences) =
        when {
            prefs.getString(list, "") == "[]" -> {
                this.updateFavorites(list, prefs, "[$responseItem]")
            }
            else -> {
                val currentVal = prefs.getString(list, "")
                this.updateFavorites(
                    list, prefs, "${currentVal.slice(0..(currentVal.length - 2))},$responseItem]"
                )
            }
        }

    private fun updateFavorites(list: String, prefs: SharedPreferences, newString: String) {
        prefs.edit().putString(list, newString).apply()
    }
}
