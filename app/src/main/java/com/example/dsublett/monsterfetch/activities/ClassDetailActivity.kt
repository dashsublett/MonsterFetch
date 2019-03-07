package com.example.dsublett.monsterfetch.activities

import android.os.Bundle
import android.view.View
import com.example.dsublett.monsterfetch.R
import com.example.dsublett.monsterfetch.models.ClassResponse
import com.example.dsublett.monsterfetch.models.ItemResponse
import com.example.dsublett.monsterfetch.models.ResponseItem
import com.example.dsublett.monsterfetch.services.ServiceProxy
import com.example.dsublett.monsterfetch.utils.UrlParse
import com.inmotionsoftware.promise.catch
import com.inmotionsoftware.promise.then
import kotlinx.android.synthetic.main.class_detail.*
import kotlinx.android.synthetic.main.class_detail.view.*

class ClassDetailActivity : DetailActivity("classFavorites") {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.class_detail)

        this.classDetailView.visibility = View.INVISIBLE
        this.itemIndex =
            UrlParse.getIndex(this.intent.getParcelableExtra<ResponseItem>("responseItem").url)
        this.initSharedPreferences()
        ServiceProxy.dndService.getClass(this.itemIndex).then {
            this.buildUI(it)
        }.catch {
            this.logFailure(it)
        }
    }

    private fun buildUI(details: ItemResponse?) {
        this.prepareUI()

        details as ClassResponse

        this.classDetailView.className.text = details.name
        this.classDetailView.classHitDice.text = details.hitDice

        this.classDetailView.classDetailView.visibility = View.VISIBLE
    }
}
