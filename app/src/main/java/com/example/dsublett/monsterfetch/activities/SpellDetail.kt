package com.example.dsublett.monsterfetch.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.dsublett.monsterfetch.R
import com.example.dsublett.monsterfetch.models.SpellResponse
import com.example.dsublett.monsterfetch.services.DndApiService
import com.example.dsublett.monsterfetch.utils.UrlParse
import kotlinx.android.synthetic.main.spell_detail.*
import kotlinx.android.synthetic.main.spell_detail.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SpellDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.spell_detail)

        DndApiService
            .create()
            .getSpell(UrlParse.getIndex(this.intent.getStringExtra("url")))
            .enqueue(
                object : Callback<SpellResponse> {
                    override fun onResponse(call: Call<SpellResponse>,
                                            response: Response<SpellResponse>) {
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
                    }

                    override fun onFailure(call: Call<SpellResponse>, t: Throwable) {
                        throw t
                    }
                }
            )
    }
}
