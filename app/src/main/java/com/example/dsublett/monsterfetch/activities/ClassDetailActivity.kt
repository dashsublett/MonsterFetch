package com.example.dsublett.monsterfetch.activities

import android.os.Bundle
import android.view.View
import com.example.dsublett.monsterfetch.R
import com.example.dsublett.monsterfetch.adapters.ItemAdapter
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

        this.classCollapsingToolbar.visibility = View.INVISIBLE
        this.classNestedScrollView.visibility = View.INVISIBLE
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

    private fun buildUI(details: ItemResponse?) {
        this.prepareUI()

        details as ClassResponse

        this.rvProficiencyList.adapter = ItemAdapter(details.proficiencies, null)

        this.classToolbar.title = details.name

        this.classHitDice.text = details.hitDice
        this.classSavingThrows.text = ResponseItem.stringOfNames(details.savingThrows)
        this.classSubClasses.text = ResponseItem.stringOfNames(details.subclasses)

        this.classNestedScrollView.visibility = View.VISIBLE
        this.classCollapsingToolbar.visibility = View.VISIBLE
        this.classLoadingSpinner.visibility = View.INVISIBLE
    }
}
