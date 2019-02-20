package com.example.dsublett.monsterfetch.activities

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.dsublett.monsterfetch.R
import com.example.dsublett.monsterfetch.models.ResponseItem
import com.example.dsublett.monsterfetch.services.RemoteDndService
import com.example.dsublett.monsterfetch.utils.UrlParse
import kotlinx.android.synthetic.main.monster_detail.*
import kotlinx.android.synthetic.main.monster_detail.view.*
import java.lang.Exception

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
            {
                this@MonsterDetail.detailItem =
                    this@MonsterDetail.intent.getParcelableExtra("responseItem")
                this@MonsterDetail.responseItemString =
                    this@MonsterDetail.responseItemAdapter.toJson(this@MonsterDetail.detailItem)

                val mView = this@MonsterDetail.monsterDetailView
                mView.monsterName.text = it?.name
                mView.monsterType.text = it?.type
                mView.monsterSubtype.text = it?.subtype
                mView.monsterAlignment.text = it?.alignment
                mView.monsterArmorClass.text = it?.armorClass.toString()
                mView.monsterHitPoints.text = it?.hitPoints.toString()
                mView.monsterHitDice.text = it?.hitDice.toString()
                mView.monsterSpeed.text = it?.speed
                mView.monsterStrength.text = it?.strength.toString()
                mView.monsterDexterity.text = it?.dexterity.toString()
                mView.monsterConstitution.text = it?.constitution.toString()
                mView.monsterIntelligence.text = it?.intelligence.toString()
                mView.monsterWisdom.text = it?.wisdom.toString()

                mView.monsterDetailView.visibility = View.VISIBLE
                this@MonsterDetail.setTintOnCreate()

            },
            {
                Log.d(this@MonsterDetail::class.java.canonicalName, "$it")
            }
        )
    }
}
