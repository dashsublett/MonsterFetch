package com.example.dsublett.monsterfetch.activities

import android.content.Intent
import android.os.Bundle
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

        this.monsterCollapsingToolbar.visibility = View.INVISIBLE
        this.monsterLoadingSpinner.visibility = View.VISIBLE
        this.initSharedPreferences()

        this.itemIndex =
            UrlParse.getIndex(this.intent.getParcelableExtra<ResponseItem>("responseItem").url)

        ServiceProxy.dndService.getMonster(this.itemIndex).then {
            this.setSupportActionBar(this.monsterToolbar)
            this.supportActionBar?.setDisplayHomeAsUpEnabled(true)
            this.buildUI(it)
        }.catch {
            this.logFailure(it)
        }
    }

    private fun buildUI(details: ItemResponse?) =
        this.run {
            prepareUI()
        }.apply {
            details as MonsterResponse
            rvAbilityList.adapter = details.specialAbilities?.let {
                AbilityAdapter(
                    it,
                    object : AbilityAdapter.OnItemClickListener {
                        override fun onItemClick(ability: Ability) {
                            this@MonsterDetailActivity.showAbility(ability)
                        }
                    }
                )
            }
            monsterToolbar.title = details.name

            monsterSize.text = details.size
            monsterAlignment.text = details.alignment
            monsterArmorClass.text = details.armorClass.toString()
            monsterHitPoints.text = details.hitPoints.toString()
            monsterHitDice.text = details.hitDice
            monsterSpeed.text = details.speed
            monsterStrength.text = details.strength.toString()
            monsterDexterity.text = details.dexterity.toString()

            monsterNestedScrollView.visibility = View.VISIBLE
            monsterCollapsingToolbar.visibility = View.VISIBLE
            monsterLoadingSpinner.visibility = View.INVISIBLE
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
