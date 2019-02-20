package com.example.dsublett.monsterfetch.activities

import android.content.Context
import android.os.Bundle
import android.view.View
import com.example.dsublett.monsterfetch.R
import com.example.dsublett.monsterfetch.models.ResponseItem
import com.example.dsublett.monsterfetch.models.SpellResponse
import com.example.dsublett.monsterfetch.services.RemoteDndService
import com.example.dsublett.monsterfetch.utils.UrlParse
import kotlinx.android.synthetic.main.spell_detail.*
import kotlinx.android.synthetic.main.spell_detail.view.*

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

        RemoteDndService().getSpell(this.itemIndex, this::buildUI, this::logFailure)
    }

    private fun buildUI(details: SpellResponse?) {
        this.detailItem = this.intent.getParcelableExtra("responseItem")
        this.responseItemString =
            this.responseItemAdapter.toJson(this.detailItem)

        val sView = this.spellDetailView
        sView.spellName.text = details?.name
        sView.spellDesc.text = details?.desc.toString()
        sView.spellRange.text = details?.range
        sView.spellComponents.text = details?.components.toString()
        sView.spellRitual.text = details?.ritual
        sView.spellDuration.text = details?.duration
        sView.spellConcentration.text = details?.concentration
        sView.spellCastingTime.text = details?.castingTime
        sView.spellLevel.text = details?.level.toString()

        sView.spellDetailView.visibility = View.VISIBLE
        this.setTintOnCreate()
    }
}
