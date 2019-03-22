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

    private fun buildUI(details: ItemResponse?) =
        this.run {
            prepareUI()
        }.apply {
            details as SpellResponse

            spellToolbar.title = details.name

            spellDescription.text = details.descAsString()
            spellRange.text = getString(R.string.range_label, details.range)
            spellComponents.text = getString(
                R.string.components_label,
                details.components.toString()
            )
            spellMaterial.text = getString(R.string.material, details.material)
            spellRitual.text = getString(R.string.ritual_label, details.ritual)
            spellDuration.text = getString(R.string.duration_label, details.duration)
            spellConcentration.text = getString(R.string.concentration_label, details.concentration)
            spellCastingTime.text = getString(R.string.casting_time_label, details.castingTime)
            spellLevel.text = getString(R.string.level_label, details.level.toString())

            spellNestedScrollView.visibility = View.VISIBLE
            spellCollapsingToolbar.visibility = View.VISIBLE
            spellLoadingSpinner.visibility = View.INVISIBLE
        }
}
