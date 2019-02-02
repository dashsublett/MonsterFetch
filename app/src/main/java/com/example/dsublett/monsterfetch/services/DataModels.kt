package com.example.dsublett.monsterfetch.services

// Data classes required to parse JSON response with Moshi
data class DNDAPIResponse(val count: Int, val results: List<ResponseItem>)
data class ResponseItem(val name: String, val url: String)
