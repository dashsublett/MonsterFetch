package com.example.dsublett.monsterfetch.activities

import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.view.MenuItem
import com.example.dsublett.monsterfetch.R
import kotlinx.android.synthetic.main.activity_ability.*

class AbilityActivity : AppCompatActivity() {
    private lateinit var abilityName: String
    private lateinit var abilityDesc: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ability)
        setSupportActionBar(this.abilityToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        this.abilityName = this.intent.getStringExtra("name")
        this.abilityDesc = this.intent.getStringExtra("desc")

        this.title = this.abilityName

        this.tvAbilityDesc.text = this.abilityDesc
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
