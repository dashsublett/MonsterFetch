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

class SpellDetailActivity : DetailActivity("spellFavorites") {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.spell_detail)

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

        this.spellDetailView.spellName.text = details.name
        this.spellDetailView.spellDesc.text = details.desc.toString()
        this.spellDetailView.spellRange.text = details.range
        this.spellDetailView.spellComponents.text = details.components.toString()
        this.spellDetailView.spellRitual.text = details.ritual
        this.spellDetailView.spellDuration.text = details.duration
        this.spellDetailView.spellConcentration.text = details.concentration
        this.spellDetailView.spellCastingTime.text = details.castingTime
        this.spellDetailView.spellLevel.text = details.level.toString()

        this.spellDetailView.spellDetailView.visibility = View.VISIBLE
    }
}
