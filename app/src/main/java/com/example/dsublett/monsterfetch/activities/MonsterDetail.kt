package com.example.dsublett.monsterfetch.activities

import android.content.Context
import android.os.Bundle
import android.view.View
import com.example.dsublett.monsterfetch.R
import com.example.dsublett.monsterfetch.models.MonsterResponse
import com.example.dsublett.monsterfetch.models.ResponseItem
import com.example.dsublett.monsterfetch.services.DndApiService
import com.example.dsublett.monsterfetch.utils.UrlParse
import kotlinx.android.synthetic.main.monster_detail.*
import kotlinx.android.synthetic.main.monster_detail.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MonsterDetail : DetailActivity("monsterFavorites") {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.monster_detail)

        this.monsterDetailView.visibility = View.INVISIBLE
        this.sharedPreferences =
            this.getSharedPreferences("com.example.dsublett.monsterfetch.sharedPreferences", Context.MODE_PRIVATE)
        this.itemIndex =
            UrlParse.getIndex(this.intent.getParcelableExtra<ResponseItem>("responseItem").url)

        DndApiService
            .create()
            .getMonster(this.itemIndex)
            .enqueue(
                object : Callback<MonsterResponse> {
                    override fun onResponse(call: Call<MonsterResponse>,
                                            response: Response<MonsterResponse>) {
                        this@MonsterDetail.detailItem =
                            this@MonsterDetail.intent.getParcelableExtra("responseItem")
                        this@MonsterDetail.responseItemString =
                            this@MonsterDetail.responseItemAdapter.toJson(this@MonsterDetail.detailItem)

                        val mView = this@MonsterDetail.monsterDetailView
                        mView.monsterName.text = response.body()?.name
                        mView.monsterType.text = response.body()?.type
                        mView.monsterSubtype.text = response.body()?.subtype
                        mView.monsterAlignment.text = response.body()?.alignment
                        mView.monsterArmorClass.text = response.body()?.armorClass.toString()
                        mView.monsterHitPoints.text = response.body()?.hitPoints.toString()
                        mView.monsterHitDice.text = response.body()?.hitDice.toString()
                        mView.monsterSpeed.text = response.body()?.speed
                        mView.monsterStrength.text = response.body()?.strength.toString()
                        mView.monsterDexterity.text = response.body()?.dexterity.toString()
                        mView.monsterConstitution.text = response.body()?.constitution.toString()
                        mView.monsterIntelligence.text = response.body()?.intelligence.toString()
                        mView.monsterWisdom.text = response.body()?.wisdom.toString()

                        mView.monsterDetailView.visibility = View.VISIBLE
                        this@MonsterDetail.setTintOnCreate()
                    }

                    override fun onFailure(call: Call<MonsterResponse>, t: Throwable) {
                        throw t
                    }
                }
            )
    }
}
