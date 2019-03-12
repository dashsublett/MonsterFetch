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

class SpellDetailActivity : DetailActivity("spellFavorites") {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.spell_detail)

        this.spellNestedScrollView.visibility = View.INVISIBLE
        this.spellCollapsingToolbar.visibility = View.INVISIBLE
        this.spellLoadingSpinner.visibility = View.VISIBLE
        this.initSharedPreferences()

        this.itemIndex =
            UrlParse.getIndex(this.intent.getParcelableExtra<ResponseItem>("responseItem").url)

        ServiceProxy.dndService.getSpell(this.itemIndex).then {
            this.setSupportActionBar(this.spellToolbar)
            this.supportActionBar?.setDisplayHomeAsUpEnabled(true)
            this.buildUI(it)
        }.catch {
            this.logFailure(it)
        }
    }

    private fun buildUI(details: ItemResponse?) {
        this.prepareUI()

        details as SpellResponse

        this.spellToolbar.title = details.name

        this.spellDescription.text = details.descAsString()
        this.spellRange.text = details.range
        this.spellComponents.text = details.components.toString()
        this.spellRitual.text = details.ritual
        this.spellDuration.text = details.duration
        this.spellConcentration.text = details.concentration
        this.spellCastingTime.text = details.castingTime
        this.spellLevel.text = details.level.toString()

        this.spellNestedScrollView.visibility = View.VISIBLE
        this.spellCollapsingToolbar.visibility = View.VISIBLE
        this.spellLoadingSpinner.visibility = View.INVISIBLE
    }
}
