package com.example.dsublett.monsterfetch.utils

import android.content.SharedPreferences

class SPFavorites {
    companion object {
        fun addFavorite(list: String, responseItem: String, prefs: SharedPreferences) {
            if(prefs.getString(list, "").equals("[]")) {
                prefs.edit().putString(list, "[$responseItem]").apply()
            }
            else {
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
    }
}
