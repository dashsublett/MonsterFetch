package com.example.dsublett.monsterfetch.activities

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.AttributeSet
import android.view.View
import com.example.dsublett.monsterfetch.R
import com.example.dsublett.monsterfetch.models.MonsterResponse
import com.example.dsublett.monsterfetch.models.ResponseItem
import com.example.dsublett.monsterfetch.services.DndApiService
import kotlinx.android.synthetic.main.monster_detail.*
import kotlinx.android.synthetic.main.monster_detail.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MonsterDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.monster_detail)
        // Could wrap api call and setting view in a function and call here
    }

    override fun onCreateView(name: String?, context: Context?, attrs: AttributeSet?): View? {
        val tokenizedUrl = ResponseItem(
            intent.getStringExtra("name"),
            intent.getStringExtra("url")
        ).url.split("/")
        DndApiService.create().getMonster(tokenizedUrl[tokenizedUrl.size - 1].toInt()).enqueue(
            object : Callback<MonsterResponse> {
                override fun onResponse(call: Call<MonsterResponse>, response: Response<MonsterResponse>) {
                    val mView = this@MonsterDetail.monsterDetail
                    mView.monsterName.text = response.body()?.name
                    mView.monsterType.text = response.body()?.type
                    mView.monsterSubtype.text = response.body()?.subtype
                    mView.monsterAlignment.text = response.body()?.alignment
                    mView.monsterArmorClass.text = response.body()?.armor_class.toString()
                    mView.monsterHitPoints.text = response.body()?.hit_points.toString()
                    mView.monsterHitDice.text = response.body()?.hit_dice.toString()
                    mView.monsterSpeed.text = response.body()?.speed
                    mView.monsterStrength.text = response.body()?.strength.toString()
                    mView.monsterDexterity.text = response.body()?.dexterity.toString()
                    mView.monsterConstitution.text = response.body()?.constitution.toString()
                    mView.monsterIntelligence.text = response.body()?.intelligence.toString()
                    mView.monsterWisdom.text = response.body()?.wisdom.toString()
                }

                override fun onFailure(call: Call<MonsterResponse>, t: Throwable) {
                    // Handle failure
                }
            }
        )
        return super.onCreateView(name, context, attrs)
    }
}
