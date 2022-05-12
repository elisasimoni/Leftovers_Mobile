@file:Suppress("MemberVisibilityCanBePrivate", "FunctionName")

package com.example.leftovers

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import org.json.JSONArray


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //read_json()
       val button = findViewById<CardView>(R.id.button)

        button.setOnClickListener{
            changeLogin()
        }

    }
    private fun changeLogin(){
        startActivity(Intent(this,LoginActivity::class.java))
    }

/*
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

    }*/

}

