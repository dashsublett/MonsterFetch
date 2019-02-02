package com.example.dsublett.monsterfetch

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.mainActivityLayout, ItemFragment())
        fragmentTransaction.add(R.id.mainActivityLayout, NavBarFragment())
        fragmentTransaction.commit()
    }
}
