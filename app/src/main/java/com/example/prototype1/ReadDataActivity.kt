package com.example.prototype1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import androidx.recyclerview.widget.RecyclerView
class ReadDataActivity : AppCompatActivity() {
    private lateinit var mAdapter: ReadDataAdapter
    override fun onCreate(savedInstanceState: Bundle?) {

        var recView = findViewById<RecyclerView>(R.id.recView)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_data)
        fetchData()
        mAdapter = ReadDataAdapter(this)
        recView.adapter = mAdapter

    }
    private fun fetchData() {
        val url = "https://api.thingspeak.com/channels/1673699/feeds.json?api_key=WVTJZI8SKVX6GJTH&results=2"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            {
                val newsJsonArray = it.getJSONArray("channel")
                val newsArray = ArrayList<ReadD>()
                for(i in 0 until newsJsonArray.length()) {
                    val newsJsonObject = newsJsonArray.getJSONObject(i)
                    val news = ReadD(
                        newsJsonObject.getString("id"),
                        newsJsonObject.getString("name"),
                        newsJsonObject.getString("latitude"),
                        newsJsonObject.getString("longitude"),
                        newsJsonObject.getInt("field1"),
                        newsJsonObject.getInt("field2"),
                        newsJsonObject.getInt("field3"),
                        newsJsonObject.getInt("field4"),
                        newsJsonObject.getInt("field5"),
                        newsJsonObject.getInt("field6"),
                        newsJsonObject.getInt("field7")
                    )
                    newsArray.add(news)
                }

                mAdapter.updateNews(newsArray)
            },
            {

            }
        )
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

}