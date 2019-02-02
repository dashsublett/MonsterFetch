package com.example.dsublett.monsterfetch.services

// Data classes required to parse JSON response with Moshi
data class MonstersResponse(val count: Int, val results: List<Monster>)
data class Monster(val name: String, val url: String)
