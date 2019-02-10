package com.example.dsublett.monsterfetch.models

// Data classes required to parse JSON response with Moshi
data class DNDAPIResponse(val count: Int, val results: List<ResponseItem>)

data class ResponseItem(val name: String, val url: String)

/* For MonsterResponse, ClassResponse and SpellResponse, I am only parsing fields that can be
 * represented as a String, Int, List<String> or List<Int>. Complex fields that would require
 * creating more models are being ignored for now. Also fields such as _id, index and url are being
 * ignored, since they are not being used in the app.
 */

data class MonsterResponse(
    val name: String,
    val type: String,
    val subtype: String,
    val alignment: String,
    val armor_class: Int,
    val hit_points: Int,
    val hit_dice: String,
    val speed: String,
    val strength: Int,
    val dexterity: Int,
    val constitution: Int,
    val intelligence: Int,
    val wisdom: Int
)

data class ClassResponse(
    val name: String,
    val hit_dice: String
)

data class SpellResponse(
    val name: String,
    val desc: List<String>,
    val range: String,
    val components: List<String>,
    val ritual: String,
    val duration: String,
    val concentration: String,
    val castingTime: String,
    val level: Int
)
