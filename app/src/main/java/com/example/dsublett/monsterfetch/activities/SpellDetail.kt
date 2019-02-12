package com.example.dsublett.monsterfetch.activities

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.AttributeSet
import android.view.View
import com.example.dsublett.monsterfetch.R
import com.example.dsublett.monsterfetch.models.SpellResponse
import com.example.dsublett.monsterfetch.models.ResponseItem
import com.example.dsublett.monsterfetch.services.DndApiService
import kotlinx.android.synthetic.main.spell_detail.*
import kotlinx.android.synthetic.main.spell_detail.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SpellDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.spell_detail)
    }

    override fun onCreateView(name: String?, context: Context?, attrs: AttributeSet?): View? {
        val tokenizedUrl = ResponseItem(
            intent.getStringExtra("name"),
            intent.getStringExtra("url")
        ).url.split("/")
        DndApiService.create().getSpell(tokenizedUrl[tokenizedUrl.size - 1].toInt()).enqueue(
            object : Callback<SpellResponse> {
                override fun onResponse(call: Call<SpellResponse>, response: Response<SpellResponse>) {
                    val sView = this@SpellDetail.spellDetail
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
                    // Handle failure
                }
            }
        )
        return super.onCreateView(name, context, attrs)
    }
}
