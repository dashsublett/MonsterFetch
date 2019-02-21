package com.example.dsublett.monsterfetch.activities

import android.content.Context
import android.os.Bundle
import android.view.View
import com.example.dsublett.monsterfetch.R
import com.example.dsublett.monsterfetch.models.ClassResponse
import com.example.dsublett.monsterfetch.models.ResponseItem
import com.example.dsublett.monsterfetch.services.AppProxy
import com.example.dsublett.monsterfetch.utils.UrlParse
import kotlinx.android.synthetic.main.class_detail.*
import kotlinx.android.synthetic.main.class_detail.view.*

class ClassDetail : DetailActivity("classFavorites") {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.class_detail)

        this.classDetailView.visibility = View.INVISIBLE
        this.sharedPreferences = this.getSharedPreferences(
            "com.example.dsublett.monsterfetch.sharedPreferences",
            Context.MODE_PRIVATE
        )
        this.itemIndex =
            UrlParse.getIndex(this.intent.getParcelableExtra<ResponseItem>("responseItem").url)

        AppProxy.dndService.getClass(this.itemIndex, this::buildUI, this::logFailure)
    }

    private fun buildUI(details: ClassResponse?) {
        this.detailItem = this.intent.getParcelableExtra("responseItem")
        this.responseItemString =
            this.responseItemAdapter.toJson(this.detailItem)

        val cView = this.classDetailView
        cView.className.text = details?.name
        cView.classHitDice.text = details?.hitDice

        cView.classDetailView.visibility = View.VISIBLE
        this.setTintOnCreate()
    }
}
