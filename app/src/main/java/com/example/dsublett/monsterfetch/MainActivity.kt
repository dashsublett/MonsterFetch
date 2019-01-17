package com.example.dsublett.monsterfetch

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.net.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val responseTextView = findViewById<TextView>(R.id.responseTextView)
        fetchBtn.setOnClickListener {
            AsyncTaskExample(this).execute()
        }
    }

}

class AsyncTaskExample(private var activity: MainActivity?) : AsyncTask<String, String, String>() {
    override fun doInBackground(vararg p0: String?): String {
        return URL("http://www.dnd5eapi.co/api/monsters").readText()
    }
    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        activity?.responseTextView?.text = result
    }
}
