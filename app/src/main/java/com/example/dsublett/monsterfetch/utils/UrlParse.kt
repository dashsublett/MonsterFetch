package com.example.dsublett.monsterfetch.utils

class UrlParse {
    companion object {
        fun getEndpoint(url: String): String {
            return url.removePrefix("http://www.dnd5eapi.co/api/").split("/")[0]
        }

        fun getIndex(url: String): String {
            return url.removePrefix("http://www.dnd5eapi.co/api/").split("/")[1]
        }
    }
}
