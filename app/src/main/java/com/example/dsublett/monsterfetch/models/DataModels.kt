package com.example.dsublett.monsterfetch.models

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

data class DNDAPIResponse(val count: Int, val results: List<ResponseItem>)

interface ItemResponse

// Primary data classes
data class MonsterResponse(
    @field:Json(name = "_id") val id: String,
    val index: Int,
    val name: String,
    val size: String,
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
    val wisdom: Int,
    val charisma: Int,
    val athletics: Int,
    val intimidation: Int,
    val acrobatics: Int,
    val medicine: Int,
    val nature: Int,
    val religion: Int,
    @field:Json(name = "dexterity_save") val dexteritySave: Int,
    @field:Json(name = "constitution_save") val constitutionSave: Int,
    @field:Json(name = "intelligence_save") val intelligenceSave: Int,
    @field:Json(name = "wisdom_save") val wisdomSave: Int,
    @field:Json(name = "charisma_save") val charismaSave: Int,
    @field:Json(name = "strength_save") val strengthSave: Int,
    val deception: Int,
    val insight: Int,
    val arcana: Int,
    val history: Int,
    val investigation: Int,
    val perception: Int,
    val performance: Int,
    val persuasion: Int,
    val stealth: Int,
    val survival: Int,
    @field:Json(name = "damage_vulnerabilities") val damageVulnerabilities: String,
    @field:Json(name = "damage_resistances") val damageResistances: String,
    @field:Json(name = "damage_immunities") val damageImmunities: String,
    @field:Json(name = "condition_immunities") val conditionImmunities: String,
    val senses: String,
    val languages: String,
    @field:Json(name = "challenge_rating") val challengeRating: Float,
    @field:Json(name = "special_abilities") val specialAbilities: List<Ability>?,
    val actions: List<Ability>?,
    val reactions: List<Ability>?,
    @field:Json(name = "legendary_actions") val legendaryActions: List<Ability>?,
    val url: String
) : ItemResponse

data class ClassResponse(
    @field:Json(name = "_id") val id: String,
    val index: Int,
    val name: String,
    @field:Json(name = "hit_die") val hitDice: String,
    @field:Json(name = "proficiency_choices") val proficiencyChoices: List<ProficiencyGroup>,
    val proficiencies: List<ResponseItem>,
    @field:Json(name = "saving_throws") val savingThrows: List<ResponseItem>,
    @field:Json(name = "starting_equipment") val startingEquipment: ClassAndUrl,
    @field:Json(name = "class_levels") val classLevels: ClassAndUrl,
    val subclasses: List<ResponseItem>,
    val spellcasting: ClassAndUrl,
    val url: String
) : ItemResponse

data class SpellResponse(
    @field:Json(name = "_id") val id: String,
    val index: Int,
    val name: String,
    val desc: List<String>,
    @field:Json(name = "higher_level") val higherLevel: List<String>,
    val page: String,
    val range: String,
    val components: List<String>,
    val material: String,
    val ritual: String,
    val duration: String,
    val concentration: String,
    @field:Json(name = "casting_time") val castingTime: String,
    val level: Int,
    val school: ResponseItem,
    val classes: List<ResponseItem>,
    val subclasses: List<ResponseItem>,
    val url: String
) : ItemResponse {
    fun descAsString(): String {
        var retStr = ""
        for (desc in this.desc) {
            retStr += "$desc "
        }
        return retStr.replace("â€™", "'").replace("â€�", "").replace("â€œ", "")
    }
}


// Supporting data classes
data class Ability(
    @field:Json(name = "damage_bonus") val damageBonus: Int,
    @field:Json(name = "damage_dice") val damageDice: String,
    @field:Json(name = "attack_bonus") val attackBonus: Int,
    val desc: String,
    val name: String
)

data class ProficiencyGroup(val from: List<ResponseItem>, val type: String, val choose: String)

data class ClassAndUrl(val url: String, @field:Json(name = "class") val dndClass: String)

data class ResponseItem(val name: String, val url: String) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString(), parcel.readString())

    override fun describeContents() = 0

    override fun writeToParcel(parcel: Parcel?, p1: Int) {
        parcel?.let {
            it.writeString(this.name)
            it.writeString(this.url)
        }
    }

    companion object CREATOR : Parcelable.Creator<ResponseItem> {
        override fun createFromParcel(parcel: Parcel): ResponseItem = ResponseItem(parcel)

        override fun newArray(size: Int): Array<ResponseItem?> = arrayOfNulls(size)

        fun stringOfNames(inList: List<ResponseItem>): String {
            var retStr = ""
            var i = 0
            while (i < inList.size) {
                retStr += "${inList[i].name}, "
                ++i
            }

            return retStr.substring(0, retStr.length - 2)
        }
    }
}
