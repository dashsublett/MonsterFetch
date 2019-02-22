package com.example.dsublett.monsterfetch.models

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

// Data classes required to parse JSON response with Moshi
data class DNDAPIResponse(val count: Int, val results: List<ResponseItem>)

data class ResponseItem(val name: String, val url: String) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString(), parcel.readString())

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


/* For MonsterResponse, ClassResponse and SpellResponse, I am only parsing fields that can be
 * represented as a String, Int, List<String> or List<Int>. Complex fields that would require
 * creating more models are being ignored for now. Also fields such as _id, index and url are being
 * ignored, since they are not being used in the app.
 */

interface ItemResponse

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
) : ItemResponse

data class ClassResponse(
    val name: String,
    @field:Json(name = "hit_die") val hitDice: String
) : ItemResponse

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
) : ItemResponse
