package com.example.dsublett.monsterfetch.activities

import android.content.Context
import android.os.Bundle
import android.view.View
import com.example.dsublett.monsterfetch.R
import com.example.dsublett.monsterfetch.models.MonsterResponse
import com.example.dsublett.monsterfetch.models.ResponseItem
import com.example.dsublett.monsterfetch.services.RemoteDndService
import com.example.dsublett.monsterfetch.utils.UrlParse
import kotlinx.android.synthetic.main.monster_detail.*
import kotlinx.android.synthetic.main.monster_detail.view.*

class MonsterDetail : DetailActivity("monsterFavorites") {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.monster_detail)

        this.monsterDetailView.visibility = View.INVISIBLE
        this.sharedPreferences = this.getSharedPreferences(
            "com.example.dsublett.monsterfetch.sharedPreferences",
            Context.MODE_PRIVATE
        )
        this.itemIndex =
            UrlParse.getIndex(this.intent.getParcelableExtra<ResponseItem>("responseItem").url)

        RemoteDndService().getMonster(
            this.itemIndex,
            this::buildUI,
            this::logFailure
        )
    }

    private fun buildUI(details: MonsterResponse?) {
        this@MonsterDetail.detailItem = this@MonsterDetail.intent.getParcelableExtra("responseItem")
        this@MonsterDetail.responseItemString =
            this@MonsterDetail.responseItemAdapter.toJson(this@MonsterDetail.detailItem)

        val mView = this@MonsterDetail.monsterDetailView
        mView.monsterName.text = details?.name
        mView.monsterType.text = details?.type
        mView.monsterSubtype.text = details?.subtype
        mView.monsterAlignment.text = details?.alignment
        mView.monsterArmorClass.text = details?.armorClass.toString()
        mView.monsterHitPoints.text = details?.hitPoints.toString()
        mView.monsterHitDice.text = details?.hitDice
        mView.monsterSpeed.text = details?.speed
        mView.monsterStrength.text = details?.strength.toString()
        mView.monsterDexterity.text = details?.dexterity.toString()
        mView.monsterConstitution.text = details?.constitution.toString()
        mView.monsterIntelligence.text = details?.intelligence.toString()
        mView.monsterWisdom.text = details?.wisdom.toString()

        mView.monsterDetailView.visibility = View.VISIBLE
        this@MonsterDetail.setTintOnCreate()
    }
}
