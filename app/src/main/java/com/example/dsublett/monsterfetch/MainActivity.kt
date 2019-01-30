package com.example.dsublett.monsterfetch

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        /* Set activity_main.xml layout and onClick listeners.
         */
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.fetchBtn.setOnClickListener{
            (this.mlFragment as ItemFragment).onClick(fetchBtn)
        }
        this.clearBtn.setOnClickListener{
            (this.mlFragment as ItemFragment).onClick(clearBtn)
        }
    }
}
