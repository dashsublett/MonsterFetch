package com.example.dsublett.monsterfetch.activities

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.dsublett.monsterfetch.R
import com.example.dsublett.monsterfetch.models.ResponseItem
import com.example.dsublett.monsterfetch.services.RemoteDndService
import com.example.dsublett.monsterfetch.utils.UrlParse
import kotlinx.android.synthetic.main.spell_detail.*
import kotlinx.android.synthetic.main.spell_detail.view.*
import java.lang.Exception

class SpellDetail : DetailActivity("spellFavorites") {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.spell_detail)

        this.spellDetailView.visibility = View.INVISIBLE
        this.sharedPreferences = this.getSharedPreferences(
            "com.example.dsublett.monsterfetch.sharedPreferences",
            Context.MODE_PRIVATE
        )
        this.itemIndex =
            UrlParse.getIndex(this.intent.getParcelableExtra<ResponseItem>("responseItem").url)

        RemoteDndService().getSpell(
            this.itemIndex,
            {
                this@SpellDetail.detailItem =
                    this@SpellDetail.intent.getParcelableExtra("responseItem")
                this@SpellDetail.responseItemString =
                    this@SpellDetail.responseItemAdapter.toJson(this@SpellDetail.detailItem)

                val sView = this@SpellDetail.spellDetailView
                sView.spellName.text = it?.name
                sView.spellDesc.text = it?.desc.toString()
                sView.spellRange.text = it?.range
                sView.spellComponents.text = it?.components.toString()
                sView.spellRitual.text = it?.ritual
                sView.spellDuration.text = it?.duration
                sView.spellConcentration.text = it?.concentration
                sView.spellCastingTime.text = it?.castingTime
                sView.spellLevel.text = it?.level.toString()

                sView.spellDetailView.visibility = View.VISIBLE
                this@SpellDetail.setTintOnCreate()
            },
            {
                try {
                    throw it
                } catch (e: Exception) {
                    Log.d(this@SpellDetail::class.java.canonicalName, "$e")
                }
            }
        )
    }
}
