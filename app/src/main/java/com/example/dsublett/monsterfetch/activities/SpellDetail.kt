package com.example.dsublett.monsterfetch.activities

import android.content.Context
import android.os.Bundle
import android.view.View
import com.example.dsublett.monsterfetch.R
import com.example.dsublett.monsterfetch.models.ResponseItem
import com.example.dsublett.monsterfetch.models.SpellResponse
import com.example.dsublett.monsterfetch.services.DndApiService
import com.example.dsublett.monsterfetch.utils.UrlParse
import kotlinx.android.synthetic.main.spell_detail.*
import kotlinx.android.synthetic.main.spell_detail.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SpellDetail : DetailActivity("spellFavorites") {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.spell_detail)

        this.spellDetailView.visibility = View.INVISIBLE
        this.sharedPreferences =
            this.getSharedPreferences("com.example.dsublett.monsterfetch.sharedPreferences", Context.MODE_PRIVATE)
        this.itemIndex =
            UrlParse.getIndex(this.intent.getParcelableExtra<ResponseItem>("responseItem").url)

        DndApiService
            .create()
            .getSpell(this.itemIndex)
            .enqueue(
                object : Callback<SpellResponse> {
                    override fun onResponse(call: Call<SpellResponse>,
                                            response: Response<SpellResponse>) {
                        this@SpellDetail.detailItem =
                            this@SpellDetail.intent.getParcelableExtra("responseItem")
                        this@SpellDetail.responseItemString =
                            this@SpellDetail.responseItemAdapter.toJson(this@SpellDetail.detailItem)

                        val sView = this@SpellDetail.spellDetailView
                        sView.spellName.text = response.body()?.name
                        sView.spellDesc.text = response.body()?.desc.toString()
                        sView.spellRange.text = response.body()?.range
                        sView.spellComponents.text = response.body()?.components.toString()
                        sView.spellRitual.text = response.body()?.ritual
                        sView.spellDuration.text = response.body()?.duration
                        sView.spellConcentration.text = response.body()?.concentration
                        sView.spellCastingTime.text = response.body()?.castingTime
                        sView.spellLevel.text = response.body()?.level.toString()

                        sView.spellDetailView.visibility = View.VISIBLE
                        this@SpellDetail.setTintOnCreate()
                    }

                    override fun onFailure(call: Call<SpellResponse>, t: Throwable) {
                        throw t
                    }
                }
            )
    }
}
