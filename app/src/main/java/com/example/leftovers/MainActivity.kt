@file:Suppress("MemberVisibilityCanBePrivate", "FunctionName")

package com.example.leftovers

import android.content.Context
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
            changePage(this,LoginActivity::class.java)
        }

    }
    private fun changeLogin(){
        startActivity(Intent(this,LoginActivity::class.java))
    }

    private fun changePage(context : Context, activity : Class<*>){
        val intent = Intent(context, activity)
        startActivity(intent)
    }



}

