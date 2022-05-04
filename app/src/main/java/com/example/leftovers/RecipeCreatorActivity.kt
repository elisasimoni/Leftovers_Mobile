package com.example.leftovers

import android.R
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.test.platform.app.InstrumentationRegistry
import org.json.JSONException
import org.json.JSONObject
import java.io.InputStream


class RecipeCreatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.leftovers.R.layout.activity_recipe)
        val ddm = findViewById<android.widget.Spinner>(com.example.leftovers.R.id.dropdown_menu)

        val json = loadJsonObjectFromAsset("Food.json")
        val refList: MutableList<String> = ArrayList()
        try {
            val refArray = json!!.getJSONArray("food")
            for (i in 0 until refArray.length()) {
                val ref = refArray.getJSONObject(i).getString("name")
                refList.add(ref)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        val arrayAdapter = ArrayAdapter(
            this,
            R.layout.simple_spinner_dropdown_item,
            refList
        )
        ddm.adapter = arrayAdapter


    }


    fun loadJsonObjectFromAsset(assetName: String): JSONObject? {
        try {
            val json = loadStringFromAsset(assetName)
            if (json != null) return JSONObject(json)
        } catch (e: Exception) {
            Log.e("JsonUtils", e.toString())
        }
        return null
    }

    @Throws(Exception::class)
    private fun loadStringFromAsset(assetName: String): String? {
        val `is`: InputStream =  InstrumentationRegistry.getInstrumentation().context.assets.open(assetName)
        val size = `is`.available()
        val buffer = ByteArray(size)
        `is`.read(buffer)
        `is`.close()
        return String(buffer, Charsets.UTF_8)
    }
}