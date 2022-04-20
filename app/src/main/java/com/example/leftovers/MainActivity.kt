@file:Suppress("MemberVisibilityCanBePrivate", "FunctionName")

package com.example.leftovers

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun read_json() {

        val arr = arrayListOf<String>()
        val jsonList = findViewById<ListView>(R.id.json_list)

        try {
            val inputStream = assets.open("Food.json")
            val json = inputStream.bufferedReader().use { it.readText() }

            val jsonarr = JSONArray(json)

            for (i in 0 until jsonarr.length()) {
                val jsonobj = jsonarr.getJSONObject(i)
                arr.add(jsonobj.getString("name"))
            }
            val adpt = ArrayAdapter(this, android.R.layout.simple_list_item_1, arr)


            jsonList.adapter = adpt
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}

