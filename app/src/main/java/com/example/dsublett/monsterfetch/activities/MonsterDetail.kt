package com.example.dsublett.monsterfetch.activities

import android.content.Context
import android.os.Bundle
import android.view.View
import com.example.dsublett.monsterfetch.R
import com.example.dsublett.monsterfetch.models.MonsterResponse
import com.example.dsublett.monsterfetch.models.ResponseItem
import com.example.dsublett.monsterfetch.services.ServiceProxy
import com.example.dsublett.monsterfetch.utils.SPFavorites
import com.example.dsublett.monsterfetch.utils.UrlParse
import kotlinx.android.synthetic.main.monster_detail.*
import kotlinx.android.synthetic.main.monster_detail.view.*

class MonsterDetail : DetailActivity("monsterFavorites") {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.monster_detail)

        this.monsterDetailView.visibility = View.INVISIBLE
        this.sharedPreferences = this.getSharedPreferences(SPFavorites.KEY, Context.MODE_PRIVATE)
        this.itemIndex =
            UrlParse.getIndex(this.intent.getParcelableExtra<ResponseItem>("responseItem").url)

        ServiceProxy.dndService.getMonster(this.itemIndex, this::buildUI, this::logFailure)
    }

    private fun buildUI(details: MonsterResponse?) {
        this.prepareUI()

        monsterDetailView.monsterName.text = details?.name
        monsterDetailView.monsterType.text = details?.type
        monsterDetailView.monsterSubtype.text = details?.subtype
        monsterDetailView.monsterAlignment.text = details?.alignment
        monsterDetailView.monsterArmorClass.text = details?.armorClass.toString()
        monsterDetailView.monsterHitPoints.text = details?.hitPoints.toString()
        monsterDetailView.monsterHitDice.text = details?.hitDice
        monsterDetailView.monsterSpeed.text = details?.speed
        monsterDetailView.monsterStrength.text = details?.strength.toString()
        monsterDetailView.monsterDexterity.text = details?.dexterity.toString()
        monsterDetailView.monsterConstitution.text = details?.constitution.toString()
        monsterDetailView.monsterIntelligence.text = details?.intelligence.toString()
        monsterDetailView.monsterWisdom.text = details?.wisdom.toString()

        monsterDetailView.monsterDetailView.visibility = View.VISIBLE
    }
}
