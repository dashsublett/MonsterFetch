package com.example.dsublett.monsterfetch.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.dsublett.monsterfetch.R
import com.example.dsublett.monsterfetch.adapters.AbilityAdapter
import com.example.dsublett.monsterfetch.models.Ability
import com.example.dsublett.monsterfetch.models.ItemResponse
import com.example.dsublett.monsterfetch.models.MonsterResponse
import com.example.dsublett.monsterfetch.models.ResponseItem
import com.example.dsublett.monsterfetch.services.ServiceProxy
import com.example.dsublett.monsterfetch.utils.UrlParse
import com.inmotionsoftware.promise.catch
import com.inmotionsoftware.promise.then
import kotlinx.android.synthetic.main.monster_detail.*

class MonsterDetailActivity : DetailActivity("monsterFavorites") {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.monster_detail)

        this.collapsingToolbar.visibility = View.INVISIBLE
        this.initSharedPreferences()

        this.itemIndex =
            UrlParse.getIndex(this.intent.getParcelableExtra<ResponseItem>("responseItem").url)

        ServiceProxy.dndService.getMonster(this.itemIndex).then {
            this.buildUI(it)
            this.setSupportActionBar(this.toolbar)
            this.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }.catch {
            this.logFailure(it)
        }
    }

    private fun buildUI(details: ItemResponse?) {
        this.prepareUI()
        this.nestedScrollView.visibility = View.INVISIBLE

        details as MonsterResponse

        this.rvAbilityList.adapter = details?.specialAbilities?.let {
            AbilityAdapter(
                it,
                object : AbilityAdapter.OnItemClickListener {
                    override fun onItemClick(ability: Ability) {
                        this@MonsterDetailActivity.showAbility(ability)
                    }
                }
            )
        }

        this.toolbar.title = details.name
        this.toolbar.subtitle = details.type

        this.monsterSize.text = details.size
        this.monsterAlignment.text = details.alignment
        this.monsterArmorClass.text = details.armorClass.toString()
        this.monsterHitPoints.text = details.hitPoints.toString()
        this.monsterHitDice.text = details.hitDice
        this.monsterSpeed.text = details.speed
        this.monsterStrength.text = details.strength.toString()
        this.monsterDexterity.text = details.dexterity.toString()

        this.nestedScrollView.visibility = View.VISIBLE
        this.collapsingToolbar.visibility = View.VISIBLE
    }

    fun showAbility(ability: Ability) {
        this.startActivity(Intent(this, AbilityActivity::class.java).apply {
            action = Intent.ACTION_VIEW
            putExtras(Bundle().apply {
                putString("name", ability.name)
                putString("desc", ability.desc)
            })
        })
    }
}
