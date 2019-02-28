package com.example.dsublett.monsterfetch.activities

import android.os.Bundle
import android.view.View
import com.example.dsublett.monsterfetch.R
import com.example.dsublett.monsterfetch.models.ItemResponse
import com.example.dsublett.monsterfetch.models.MonsterResponse
import com.example.dsublett.monsterfetch.models.ResponseItem
import com.example.dsublett.monsterfetch.services.ServiceProxy
import com.example.dsublett.monsterfetch.utils.UrlParse
import com.inmotionsoftware.promise.catch
import com.inmotionsoftware.promise.then
import kotlinx.android.synthetic.main.monster_detail.*

class MonsterDetail : DetailActivity("monsterFavorites") {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.monster_detail)

        this.collapsingToolbar.visibility = View.INVISIBLE
        this.initSharedPreferences()
        this.itemIndex =
            UrlParse.getIndex(this.intent.getParcelableExtra<ResponseItem>("responseItem").url)
        ServiceProxy.dndService.getMonster(this.itemIndex).then {
            this.buildUI(it)
            setSupportActionBar(this.toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }.catch {
            this.logFailure(it)
        }
    }

    private fun buildUI(details: ItemResponse?) {
        this.prepareUI()

        details as MonsterResponse

        this.toolbar.title = details.name
        this.toolbar.subtitle = details.type

        this.monsterSize.text = details.size

//        this.monsterType.text = details.type
//        this.monsterName.text = details.name
//        this.monsterType.text = details.type
//        this.monsterSubtype.text = details.subtype
        this.monsterAlignment.text = details.alignment
        this.monsterArmorClass.text = details.armorClass.toString()
        this.monsterHitPoints.text = details.hitPoints.toString()
        this.monsterHitDice.text = details.hitDice
        this.monsterSpeed.text = details.speed
        this.monsterStrength.text = details.strength.toString()
        this.monsterDexterity.text = details.dexterity.toString()
//        this.monsterConstitution.text = details.constitution.toString()
//        this.monsterIntelligence.text = details.intelligence.toString()
//        this.monsterWisdom.text = details.wisdom.toString()

        this.collapsingToolbar.visibility = View.VISIBLE
    }
}
