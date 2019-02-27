package com.example.dsublett.monsterfetch.activities

import android.os.Bundle
import android.view.View
import com.example.dsublett.monsterfetch.R
import com.example.dsublett.monsterfetch.models.ItemResponse
import com.example.dsublett.monsterfetch.models.ResponseItem
import com.example.dsublett.monsterfetch.models.SpellResponse
import com.example.dsublett.monsterfetch.services.ServiceProxy
import com.example.dsublett.monsterfetch.utils.UrlParse
import com.inmotionsoftware.promise.catch
import com.inmotionsoftware.promise.then
import kotlinx.android.synthetic.main.spell_detail.*
import kotlinx.android.synthetic.main.spell_detail.view.*

class SpellDetail : DetailActivity("spellFavorites") {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.spell_detail)

        this.spellDetailView.visibility = View.INVISIBLE
        this.itemIndex =
            UrlParse.getIndex(this.intent.getParcelableExtra<ResponseItem>("responseItem").url)
        this.initSharedPreferences()
        ServiceProxy.dndService.getSpell(this.itemIndex).then {
            this.buildUI(it)
        }.catch {
            this.logFailure(it)
        }
    }

    private fun buildUI(details: ItemResponse?) {
        this.prepareUI()

        details as SpellResponse

        spellDetailView.spellName.text = details.name
        spellDetailView.spellDesc.text = details.desc.toString()
        spellDetailView.spellRange.text = details.range
        spellDetailView.spellComponents.text = details.components.toString()
        spellDetailView.spellRitual.text = details.ritual
        spellDetailView.spellDuration.text = details.duration
        spellDetailView.spellConcentration.text = details.concentration
        spellDetailView.spellCastingTime.text = details.castingTime
        spellDetailView.spellLevel.text = details.level.toString()

        spellDetailView.spellDetailView.visibility = View.VISIBLE
    }
}
