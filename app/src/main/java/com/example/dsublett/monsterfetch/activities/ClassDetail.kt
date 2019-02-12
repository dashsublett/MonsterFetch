package com.example.dsublett.monsterfetch.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.dsublett.monsterfetch.R
import com.example.dsublett.monsterfetch.models.ClassResponse
import com.example.dsublett.monsterfetch.services.DndApiService
import com.example.dsublett.monsterfetch.utils.UrlParse
import kotlinx.android.synthetic.main.class_detail.*
import kotlinx.android.synthetic.main.class_detail.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClassDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.class_detail)

        DndApiService
            .create()
            .getClass(UrlParse.getIndex(this.intent.getStringExtra("url")))
            .enqueue(
                object : Callback<ClassResponse> {
                    override fun onResponse(call: Call<ClassResponse>,
                                            response: Response<ClassResponse>) {
                        val cView = this@ClassDetail.classDetailView
                        cView.className.text = response.body()?.name
                        cView.classHitDice.text = response.body()?.hit_die
                    }

                    override fun onFailure(call: Call<ClassResponse>, t: Throwable) {
                        throw t
                    }
                }
            )
    }
}
