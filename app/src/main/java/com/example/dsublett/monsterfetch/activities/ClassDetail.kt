package com.example.dsublett.monsterfetch.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.View
import com.example.dsublett.monsterfetch.R
import com.example.dsublett.monsterfetch.models.ClassResponse
import com.example.dsublett.monsterfetch.models.ResponseItem
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

        this.classDetailView.visibility = View.VISIBLE

        DndApiService
            .create()
            .getClass(UrlParse
                .getIndex(this.intent.getParcelableExtra<ResponseItem>("responseItem").url))
            .enqueue(
                object : Callback<ClassResponse> {
                    override fun onResponse(call: Call<ClassResponse>,
                                            response: Response<ClassResponse>) {
                        val cView = this@ClassDetail.classDetailView
                        cView.className.text = response.body()?.name
                        cView.classHitDice.text = response.body()?.hitDice
                        cView.classDetailView.visibility = View.VISIBLE
                    }

                    override fun onFailure(call: Call<ClassResponse>, t: Throwable) {
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
