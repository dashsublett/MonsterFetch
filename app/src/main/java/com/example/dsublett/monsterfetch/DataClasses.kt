package com.example.dsublett.monsterfetch

data class Response(val count: Int, val results: List<Monster>)
data class Monster(val name: String, val url: String)
