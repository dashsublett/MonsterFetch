package com.example.dsublett.monsterfetch.activities

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem
import com.example.dsublett.monsterfetch.R
import kotlinx.android.synthetic.main.activity_ability.*

class AbilityActivity : AppCompatActivity() {
    private lateinit var abilityName: String
    private lateinit var abilityDesc: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_ability)
        this.setSupportActionBar(this.abilityToolbar)
        this.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        this.abilityName = this.intent.getStringExtra("name")
        this.abilityDesc = this.intent.getStringExtra("desc")

        this.title = this.abilityName

        this.tvAbilityDesc.text = this.abilityDesc
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                NavUtils.navigateUpTo(this, (NavUtils.getParentActivityIntent(this)
                    ?: Intent()).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
