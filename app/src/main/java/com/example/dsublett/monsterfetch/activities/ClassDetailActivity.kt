package com.example.dsublett.monsterfetch.activities

import android.os.Bundle
import android.view.View
import com.example.dsublett.monsterfetch.R
import com.example.dsublett.monsterfetch.adapters.ProficiencyAdapter
import com.example.dsublett.monsterfetch.models.ClassResponse
import com.example.dsublett.monsterfetch.models.ItemResponse
import com.example.dsublett.monsterfetch.models.ResponseItem
import com.example.dsublett.monsterfetch.services.ServiceProxy
import com.example.dsublett.monsterfetch.utils.UrlParse
import com.inmotionsoftware.promise.catch
import com.inmotionsoftware.promise.then
import kotlinx.android.synthetic.main.class_detail.*

class ClassDetailActivity : DetailActivity("classFavorites") {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.class_detail)

        this.classNestedScrollView.visibility = View.INVISIBLE
        this.classCollapsingToolbar.visibility = View.INVISIBLE
        this.classLoadingSpinner.visibility = View.VISIBLE
        this.initSharedPreferences()

        this.itemIndex =
            UrlParse.getIndex(this.intent.getParcelableExtra<ResponseItem>("responseItem").url)

        ServiceProxy.dndService.getClass(this.itemIndex).then {
            this.setSupportActionBar(this.classToolbar)
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

            details as ClassResponse

            rvProficiencyList.adapter = ProficiencyAdapter(details.proficiencies)

            classToolbar.title = details.name

            classHitDice.text = getString(R.string.hit_dice_label, details.hitDice)
            classSavingThrows.text = getString(
                R.string.saving_throws,
                ResponseItem.stringOfNames(details.savingThrows)
            )
            classSubClasses.text = getString(
                R.string.subclasses,
                ResponseItem.stringOfNames(details.subclasses)
            )

            classNestedScrollView.visibility = View.VISIBLE
            classCollapsingToolbar.visibility = View.VISIBLE
            classLoadingSpinner.visibility = View.INVISIBLE
        }
}
