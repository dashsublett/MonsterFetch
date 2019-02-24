package com.example.dsublett.monsterfetch.activities

import android.os.Bundle
import android.view.View
import com.example.dsublett.monsterfetch.R
import com.example.dsublett.monsterfetch.models.ItemResponse
import com.example.dsublett.monsterfetch.models.MonsterResponse
import com.example.dsublett.monsterfetch.models.ResponseItem
import com.example.dsublett.monsterfetch.services.ServiceProxy
import com.example.dsublett.monsterfetch.utils.UrlParse
import kotlinx.android.synthetic.main.monster_detail.*
import kotlinx.android.synthetic.main.monster_detail.view.*

class MonsterDetail : DetailActivity("monsterFavorites") {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.monster_detail)

        this.collapsingToolbar.visibility = View.INVISIBLE
        this.initSharedPreferences()
        this.itemIndex =
            UrlParse.getIndex(this.intent.getParcelableExtra<ResponseItem>("responseItem").url)
        ServiceProxy.dndService.getMonster(this.itemIndex, this::buildUI, this::logFailure)
    }

    private fun buildUI(details: ItemResponse?) {
        this.prepareUI()

        details as MonsterResponse

        this.collapsingToolbar.title = details.name
//        this.collapsingToolbar.monsterType.text = details.type
//        this.collapsingToolbar.monsterName.text = details.name
//        this.collapsingToolbar.monsterType.text = details.type
//        this.collapsingToolbar.monsterSubtype.text = details.subtype
//        this.collapsingToolbar.monsterAlignment.text = details.alignment
//        this.collapsingToolbar.monsterArmorClass.text = details.armorClass.toString()
//        this.collapsingToolbar.monsterHitPoints.text = details.hitPoints.toString()
//        this.collapsingToolbar.monsterHitDice.text = details.hitDice
//        this.collapsingToolbar.monsterSpeed.text = details.speed
//        this.collapsingToolbar.monsterStrength.text = details.strength.toString()
//        this.collapsingToolbar.monsterDexterity.text = details.dexterity.toString()
//        this.collapsingToolbar.monsterConstitution.text = details.constitution.toString()
//        this.collapsingToolbar.monsterIntelligence.text = details.intelligence.toString()
//        this.collapsingToolbar.monsterWisdom.text = details.wisdom.toString()

        this.collapsingToolbar.visibility = View.VISIBLE
    }
}
