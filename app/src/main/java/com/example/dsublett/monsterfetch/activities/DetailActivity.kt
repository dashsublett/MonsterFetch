package com.example.dsublett.monsterfetch.activities

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.dsublett.monsterfetch.R
import com.example.dsublett.monsterfetch.models.ResponseItem
import com.example.dsublett.monsterfetch.utils.SPFavorites
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

abstract class DetailActivity(private val spListName: String) : AppCompatActivity() {
    protected lateinit var itemIndex: String
    private lateinit var detailItem: ResponseItem
    private lateinit var responseItemString: String
    private lateinit var sharedPreferences: SharedPreferences
    private var addFavoriteBtn: MenuItem? = null

    private val responseItemAdapter: JsonAdapter<ResponseItem> = Moshi
        .Builder()
        .build()
        .adapter(ResponseItem::class.java)

    protected fun logFailure(t: Throwable) {
        Log.e("logFailure", "$t")
    }

    protected fun initSharedPreferences() {
        this.sharedPreferences = this.getSharedPreferences(SPFavorites.KEY, Context.MODE_PRIVATE)
    }

    protected fun prepareUI() {
        this.detailItem = this.intent.getParcelableExtra("responseItem")
        this.responseItemString = this.responseItemAdapter.toJson(this.detailItem)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        this.menuInflater.inflate(R.menu.action_bar, menu)
        this.addFavoriteBtn = menu?.findItem(R.id.addFavoriteBtn)
        this.setFavBtnTint()

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.addFavoriteBtn) {
            SPFavorites.addIfNotFavorited(
                this.spListName,
                this.responseItemString,
                this.sharedPreferences
            )
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                item.icon.setTint(getColor(R.color.accent_material_dark))
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setFavBtnTint() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (SPFavorites.isFavorited(
                    this.spListName,
                    this.responseItemString,
                    this.sharedPreferences
                )) {
                this.addFavoriteBtn?.icon?.setTint(getColor(R.color.accent_material_dark))
            } else {
                this.addFavoriteBtn?.icon?.setTintList(null)
            }
        }
    }
}
