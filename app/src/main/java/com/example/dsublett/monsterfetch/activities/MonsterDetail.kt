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
import kotlinx.android.synthetic.main.monster_detail.view.*

class MonsterDetail : DetailActivity("monsterFavorites") {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.monster_detail)

        this.monsterDetailView.visibility = View.INVISIBLE
        this.initSharedPreferences()
        this.itemIndex =
            UrlParse.getIndex(this.intent.getParcelableExtra<ResponseItem>("responseItem").url)
        ServiceProxy.dndService.getMonster(this.itemIndex).then {
            this.buildUI(it)
        }.catch {
            this.logFailure(it)
        }
    }

    private fun buildUI(details: ItemResponse?) {
        this.prepareUI()

        details as MonsterResponse

        monsterDetailView.monsterName.text = details.name
        monsterDetailView.monsterType.text = details.type
        monsterDetailView.monsterSubtype.text = details.subtype
        monsterDetailView.monsterAlignment.text = details.alignment
        monsterDetailView.monsterArmorClass.text = details.armorClass.toString()
        monsterDetailView.monsterHitPoints.text = details.hitPoints.toString()
        monsterDetailView.monsterHitDice.text = details.hitDice
        monsterDetailView.monsterSpeed.text = details.speed
        monsterDetailView.monsterStrength.text = details.strength.toString()
        monsterDetailView.monsterDexterity.text = details.dexterity.toString()
        monsterDetailView.monsterConstitution.text = details.constitution.toString()
        monsterDetailView.monsterIntelligence.text = details.intelligence.toString()
        monsterDetailView.monsterWisdom.text = details.wisdom.toString()

        monsterDetailView.monsterDetailView.visibility = View.VISIBLE
    }
}
