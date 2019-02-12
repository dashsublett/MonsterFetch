package com.example.dsublett.monsterfetch.activities

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.AttributeSet
import android.view.View
import com.example.dsublett.monsterfetch.R
import com.example.dsublett.monsterfetch.models.ClassResponse
import com.example.dsublett.monsterfetch.models.ResponseItem
import com.example.dsublett.monsterfetch.services.DndApiService
import kotlinx.android.synthetic.main.class_detail.*
import kotlinx.android.synthetic.main.class_detail.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClassDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.class_detail)
    }

    override fun onCreateView(name: String?, context: Context?, attrs: AttributeSet?): View? {
        val tokenizedUrl = ResponseItem(
            intent.getStringExtra("name"),
            intent.getStringExtra("url")
        ).url.split("/")
        DndApiService.create().getClass(tokenizedUrl[tokenizedUrl.size - 1].toInt()).enqueue(
            object : Callback<ClassResponse> {
                override fun onResponse(call: Call<ClassResponse>, response: Response<ClassResponse>) {
                    val cView = this@ClassDetail.classDetail
                    cView.className.text = response.body()?.name
                    cView.classHitDice.text = response.body()?.hit_die
                }

                override fun onFailure(call: Call<ClassResponse>, t: Throwable) {
                    // Handle failure
                }
            }
        )
        return super.onCreateView(name, context, attrs)
    }
}