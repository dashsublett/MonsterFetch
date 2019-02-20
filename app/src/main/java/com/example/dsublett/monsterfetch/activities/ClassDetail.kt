package com.example.dsublett.monsterfetch.activities

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.dsublett.monsterfetch.R
import com.example.dsublett.monsterfetch.models.ResponseItem
import com.example.dsublett.monsterfetch.services.RemoteDndService
import com.example.dsublett.monsterfetch.utils.UrlParse
import kotlinx.android.synthetic.main.class_detail.*
import kotlinx.android.synthetic.main.class_detail.view.*
import java.lang.Exception

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

        RemoteDndService().getClass(
            this.itemIndex,
            {
                this@ClassDetail.detailItem =
                    this@ClassDetail.intent.getParcelableExtra("responseItem")
                this@ClassDetail.responseItemString =
                    this@ClassDetail.responseItemAdapter.toJson(this@ClassDetail.detailItem)

                val cView = this@ClassDetail.classDetailView
                cView.className.text = it?.name
                cView.classHitDice.text = it?.hitDice

                cView.classDetailView.visibility = View.VISIBLE
                this@ClassDetail.setTintOnCreate()
            },
            {
                try {
                    throw it
                } catch (e: Exception) {
                    Log.d(this@ClassDetail::class.java.canonicalName, "$e")
                }
            }
        )
    }
}
