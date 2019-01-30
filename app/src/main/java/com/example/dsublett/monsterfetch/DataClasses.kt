package com.example.dsublett.monsterfetch

// Data classes required to parse JSON response with Moshi
data class Response(val count: Int, val results: List<Monster>)
data class Monster(val name: String, val url: String)
