package com.example.dsublett.monsterfetch.activities

import android.content.Context
import android.os.Bundle
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

class ClassDetail : DetailActivity("classFavorites") {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.class_detail)

        this.classDetailView.visibility = View.INVISIBLE
        this.sharedPreferences =
            this.getSharedPreferences("com.example.dsublett.monsterfetch.sharedPreferences", Context.MODE_PRIVATE)
        this.itemIndex =
            UrlParse.getIndex(this.intent.getParcelableExtra<ResponseItem>("responseItem").url)

        DndApiService
            .create()
            .getClass(this.itemIndex)
            .enqueue(
                object : Callback<ClassResponse> {
                    override fun onResponse(call: Call<ClassResponse>,
                                            response: Response<ClassResponse>) {
                        this@ClassDetail.detailItem =
                            this@ClassDetail.intent.getParcelableExtra("responseItem")
                        this@ClassDetail.responseItemString =
                            this@ClassDetail.responseItemAdapter.toJson(this@ClassDetail.detailItem)

                        val cView = this@ClassDetail.classDetailView
                        cView.className.text = response.body()?.name
                        cView.classHitDice.text = response.body()?.hitDice

                        cView.classDetailView.visibility = View.VISIBLE
                        this@ClassDetail.setTintOnCreate()
                    }

                    override fun onFailure(call: Call<ClassResponse>, t: Throwable) {
                        throw t
                    }
                }
            )
    }
}
