package com.example.dsublett.monsterfetch.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
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

class MonsterDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.monster_detail)

        this.monsterDetailView.visibility = View.INVISIBLE

        DndApiService
            .create()
            .getMonster(UrlParse
                .getIndex(this.intent.getParcelableExtra<ResponseItem>("responseItem").url))
            .enqueue(
                object : Callback<MonsterResponse> {
                    override fun onResponse(call: Call<MonsterResponse>,
                                            response: Response<MonsterResponse>) {
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
                    }

                    override fun onFailure(call: Call<MonsterResponse>, t: Throwable) {
                        throw t
                    }
                }
            )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        this.menuInflater.inflate(R.menu.action_bar, menu)
        return super.onCreateOptionsMenu(menu)
    }
}
