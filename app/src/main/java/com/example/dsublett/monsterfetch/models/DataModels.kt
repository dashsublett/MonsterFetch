package com.example.dsublett.monsterfetch.models

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

// Data classes required to parse JSON response with Moshi
data class DNDAPIResponse(val count: Int, val results: List<ResponseItem>)

data class ResponseItem(val name: String, val url: String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(parcel: Parcel?, p1: Int) {
        parcel?.writeString(this.name)
        parcel?.writeString(this.url)
    }

    companion object CREATOR : Parcelable.Creator<ResponseItem> {
        override fun createFromParcel(parcel: Parcel): ResponseItem {
            return ResponseItem(parcel)
        }

        override fun newArray(size: Int): Array<ResponseItem?> {
            return arrayOfNulls(size)
        }
    }
}

data class FavoritesList(
    private val monsterFavorites: List<ResponseItem>,
    private val classFavorites: List<ResponseItem>,
    private val spellFavorites: List<ResponseItem>
) : Map<String, List<ResponseItem>> {
    val startOfSpells: Int
        get() = monsterFavorites.size + classFavorites.size
    override val entries: Set<Map.Entry<String, List<ResponseItem>>>
        get() = throw NotImplementedError()
    override val keys: Set<String>
        get() = setOf("monsterFavorites", "classFavorites", "spellFavorites")
    override val size: Int
        get() = this.monsterFavorites.size + this.classFavorites.size + this.spellFavorites.size
    override val values: Collection<List<ResponseItem>>
        get() = listOf(this.monsterFavorites + this.classFavorites + this.spellFavorites)

    override fun containsKey(key: String): Boolean =
        (key == "monsterFavorites" || key == "classFavorites" || key == "spellFavorites")

    override fun containsValue(value: List<ResponseItem>): Boolean = throw NotImplementedError()

    override fun get(key: String): List<ResponseItem> {
        return when (key) {
            "monsterFavorites" -> this.monsterFavorites
            "classFavorites" -> this.classFavorites
            "spellFavorites" -> this.spellFavorites
            else -> emptyList()
        }
    }

    override fun isEmpty(): Boolean = this.monsterFavorites.isEmpty() and
        this.classFavorites.isEmpty() and this.spellFavorites.isEmpty()

}


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
    @field:Json(name = "armor_class") val armorClass: Int,
    @field:Json(name = "hit_points") val hitPoints: Int,
    @field:Json(name = "hit_dice") val hitDice: String,
    val speed: String,
    val strength: Int,
    val dexterity: Int,
    val constitution: Int,
    val intelligence: Int,
    val wisdom: Int
)

data class ClassResponse(
    val name: String,
    @field:Json(name = "hit_die") val hitDice: String
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
