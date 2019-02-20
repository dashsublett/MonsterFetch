package com.example.dsublett.monsterfetch.activities

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
    protected lateinit var detailItem: ResponseItem
    protected lateinit var sharedPreferences: SharedPreferences
    protected lateinit var responseItemString: String
    protected lateinit var itemIndex: String
    private var addButton: MenuItem? = null
    protected val responseItemAdapter: JsonAdapter<ResponseItem> = Moshi
        .Builder()
        .build()
        .adapter(ResponseItem::class.java)
    protected fun logFailure(t: Throwable) {
        Log.d("logFailure", "$t")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        this.menuInflater.inflate(R.menu.action_bar, menu)
        this.addButton = menu?.findItem(R.id.addFavoriteBtn)

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

    protected fun setTintOnCreate() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if(SPFavorites.isFavorited(
                this.spListName,
                this.responseItemString,
                this.sharedPreferences
            )) {
                this.addButton?.icon?.setTint(getColor(R.color.accent_material_dark))
            }
            else {
                this.addButton?.icon?.setTintList(null)
            }
        }
    }
}
