package com.example.dsublett.monsterfetch.activities

import android.os.Bundle
import android.view.View
import com.example.dsublett.monsterfetch.R
import com.example.dsublett.monsterfetch.models.ResponseItem
import com.example.dsublett.monsterfetch.models.SpellResponse
import com.example.dsublett.monsterfetch.services.ServiceProxy
import com.example.dsublett.monsterfetch.utils.UrlParse
import kotlinx.android.synthetic.main.spell_detail.*
import kotlinx.android.synthetic.main.spell_detail.view.*

class SpellDetail : DetailActivity("spellFavorites") {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.spell_detail)

        this.spellDetailView.visibility = View.INVISIBLE
        this.initSharedPreferences()
        this.itemIndex =
            UrlParse.getIndex(this.intent.getParcelableExtra<ResponseItem>("responseItem").url)

        ServiceProxy.dndService.getSpell(this.itemIndex, this::buildUI, this::logFailure)
    }

    private fun buildUI(details: SpellResponse?) {
        this.prepareUI()

        spellDetailView.spellName.text = details?.name
        spellDetailView.spellDesc.text = details?.desc.toString()
        spellDetailView.spellRange.text = details?.range
        spellDetailView.spellComponents.text = details?.components.toString()
        spellDetailView.spellRitual.text = details?.ritual
        spellDetailView.spellDuration.text = details?.duration
        spellDetailView.spellConcentration.text = details?.concentration
        spellDetailView.spellCastingTime.text = details?.castingTime
        spellDetailView.spellLevel.text = details?.level.toString()

        spellDetailView.spellDetailView.visibility = View.VISIBLE
    }
}
