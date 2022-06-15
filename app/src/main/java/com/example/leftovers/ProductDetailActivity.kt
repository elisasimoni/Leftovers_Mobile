package com.example.leftovers

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.appbar.MaterialToolbar
import com.squareup.picasso.Picasso
import java.text.DecimalFormat



class ProductDetailActivity : AppCompatActivity() {
    val BASE_URL = "https://world.openfoodfacts.org/api/v0/product/"
    var product:Product? = null
    var df: DecimalFormat = DecimalFormat("###.##")
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_detail_activity)
        val code = intent.getStringExtra("barcode")
        val userPid = intent.getStringExtra("userPid")
        val topAppBar = findViewById<MaterialToolbar>(R.id.topAppBar)

        topAppBar.setNavigationOnClickListener {
            val intent = Intent(this@ProductDetailActivity, HomePageActivity::class.java)
            intent.putExtra("userPid", userPid)
            startActivity(intent)
        }
        topAppBar.setOnMenuItemClickListener { item ->
            when (item?.itemId) {
                R.id.favoriteHeartButtonTopBar -> {
                    val intent = Intent(this, FavoriteActivity::class.java)
                    intent.putExtra("userPid", userPid)
                    startActivity(intent)
                }

                R.id.profileButtonTopBar -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    intent.putExtra("userPid", userPid)
                    startActivity(intent)
                }


            }
            true
        }

        if (code != null) {
             apiCall(code)

            Log.i("Product Detail Activity", code)
        }else{
            Log.d("code", "code is null")
        }


    }

    private fun apiCall(code : String){
        val url = "$BASE_URL$code.json"
        val queue = Volley.newRequestQueue(this)


        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                Log.d("Product Detail Activity", "API CALL SUCCESS")
                product = Product(
                    response.getString("code"),
                    if(response.getJSONObject("product").has("product_name")){
                        response.getJSONObject("product").getString("product_name")
                        }else{
                        "NA"
                         },
                    if (response.getJSONObject("product").has("quantity")) {
                        response.getJSONObject("product").getString("quantity")
                    } else {
                           "//"
                    },
                    if(response.getJSONObject("product").has("brands")){
                        response.getJSONObject("product").getString("brands")
                    }else{
                        "Unknown"
                    },
                    if(response.getJSONObject("product").has("image_url")){
                        response.getJSONObject("product").getString("image_url")
                    }else{
                        "Unknown"
                    },
                    if(response.getJSONObject("product").getJSONObject("nutriments").has("carbohydrates")){
                        df.format(response.getJSONObject("product").getJSONObject("nutriments").getDouble("carbohydrates")).toString()
                    }else{
                        "//"
                    },
                    if(response.getJSONObject("product").getJSONObject("nutriments").has("carbohydrates_100g")){
                        df.format(response.getJSONObject("product").getJSONObject("nutriments").getDouble("carbohydrates_100g")).toString()
                    }else{
                        "//"
                    },
                    if(response.getJSONObject("product").getJSONObject("nutriments").has("carbohydrates_unit")){
                        response.getJSONObject("product").getJSONObject("nutriments").getString("carbohydrates_unit")
                    }else{
                        ""
                    },
                    if(response.getJSONObject("product").getJSONObject("nutriments").has("proteins")){
                        df.format(response.getJSONObject("product").getJSONObject("nutriments").getDouble("proteins")).toString()
                    }else{
                        "//"
                    },
                    if(response.getJSONObject("product").getJSONObject("nutriments").has("proteins_100g")) {
                        df.format(response.getJSONObject("product").getJSONObject("nutriments") .getDouble("proteins_100g")).toString()
                    }else{
                        "//"
                    },
                    if(response.getJSONObject("product").getJSONObject("nutriments").has("proteins_unit")){
                        response.getJSONObject("product").getJSONObject("nutriments").getString("proteins_unit")
                    }else{
                        ""
                    },
                    if(response.getJSONObject("product").getJSONObject("nutriments").has("fat")){
                        df.format(response.getJSONObject("product").getJSONObject("nutriments").getDouble("fat")).toString()
                    }else{
                        "//"
                    },
                    if(response.getJSONObject("product").getJSONObject("nutriments").has("fat_100g")){
                        df.format(response.getJSONObject("product").getJSONObject("nutriments").getDouble("fat_100g")).toString()
                    }else{
                        "//"
                    },
                    if(response.getJSONObject("product").getJSONObject("nutriments").has("fat_unit")){
                        response.getJSONObject("product").getJSONObject("nutriments").getString("fat_unit")
                    }else{
                        ""
                    },
                    if(response.getJSONObject("product").getJSONObject("nutriments").has("saturated-fat")){
                        df.format(response.getJSONObject("product").getJSONObject("nutriments").getDouble("saturated-fat")).toString()
                    }else{
                        "//"
                    },
                    if(response.getJSONObject("product").getJSONObject("nutriments").has("saturated-fat_100g")){
                        df.format(response.getJSONObject("product").getJSONObject("nutriments").getDouble("saturated-fat_100g")).toString()
                    }else{
                        "//"
                    },
                    if(response.getJSONObject("product").getJSONObject("nutriments").has("saturated-fat_unit")){
                        response.getJSONObject("product").getJSONObject("nutriments").getString("saturated-fat_unit")
                    }else{
                        ""
                    },
                    if(response.getJSONObject("product").getJSONObject("nutriments").has("fiber")){
                        df.format(response.getJSONObject("product").getJSONObject("nutriments").getDouble("fiber")).toString()
                    }else{
                        "//"
                    },
                    if(response.getJSONObject("product").getJSONObject("nutriments").has("fiber_100g")){
                        df.format(response.getJSONObject("product").getJSONObject("nutriments").getDouble("fiber_100g")).toString()
                    }else{
                        "//"
                    },
                    if(response.getJSONObject("product").getJSONObject("nutriments").has("fiber_unit")){
                        response.getJSONObject("product").getJSONObject("nutriments").getString("fiber_unit")
                    }else{
                        ""
                    },
                    if(response.getJSONObject("product").getJSONObject("nutriments").has("salt")){
                        df.format(response.getJSONObject("product").getJSONObject("nutriments").getDouble("salt")).toString()
                    }else{
                        "//"
                    },
                    if(response.getJSONObject("product").getJSONObject("nutriments").has("salt_100g")){
                        df.format(response.getJSONObject("product").getJSONObject("nutriments").getDouble("salt_100g")).toString()

                    }else{
                        "//"
                    },
                    if(response.getJSONObject("product").getJSONObject("nutriments").has("salt_unit")){
                        response.getJSONObject("product").getJSONObject("nutriments").getString("salt_unit")
                    }else{
                        ""
                    },
                    if(response.getJSONObject("product").getJSONObject("nutriments").has("energy-kcal")){
                        df.format(response.getJSONObject("product").getJSONObject("nutriments").getDouble("energy-kcal")).toString()
                    }else{
                        "//"
                    },
                    if(response.getJSONObject("product").getJSONObject("nutriments").has("energy-kcal_100g")){
                        df.format(response.getJSONObject("product").getJSONObject("nutriments").getDouble("energy-kcal_100g")).toString()
                    }else{
                        "//"
                    },
                    if(response.getJSONObject("product").getJSONObject("nutriments").has("energy-kcal_unit")){
                        response.getJSONObject("product").getJSONObject("nutriments").getString("energy-kcal_unit")
                    }else{
                        ""
                    },
                    if(response.getJSONObject("product").getJSONObject("nutriments").has("sodium")){

                        df.format( response.getJSONObject("product").getJSONObject("nutriments").getDouble("sodium")).toString()
                    }else{

                        "//"
                    },
                    if(response.getJSONObject("product").getJSONObject("nutriments").has("sodium_100g")){
                        df.format(response.getJSONObject("product").getJSONObject("nutriments").getDouble("sodium_100g")).toString()
                    }else{
                        "//"
                    },
                    if(response.getJSONObject("product").getJSONObject("nutriments").has("sodium_unit")){
                        response.getJSONObject("product").getJSONObject("nutriments").getString("sodium_unit")
                    }else{
                        ""
                    },
                    if(response.getJSONObject("product").getJSONObject("nutriments").has("sugars")){

                        df.format(response.getJSONObject("product").getJSONObject("nutriments").getDouble("sugars")).toString()
                    }else{

                        "//"
                    },
                    if(response.getJSONObject("product").getJSONObject("nutriments").has("sugars_100g")){
                        df.format(response.getJSONObject("product").getJSONObject("nutriments").getDouble("sugars_100g")).toString()
                    }else{
                        "//"
                    },
                    if(response.getJSONObject("product").getJSONObject("nutriments").has("sugars_unit")){

                        response.getJSONObject("product").getJSONObject("nutriments").getString("sugars_unit")
                    }else{
                        ""
                    },
                    if(response.getJSONObject("product").has("ingredients_text_en")){
                        response.getJSONObject("product").getString("ingredients_text_en")
                    }else{
                        ""
                    }
                )
                if(product?.name !="NA"){
                val image_url = findViewById<ImageView>(R.id.scanProductImage)
                Picasso.get().load(product!!.image).into(image_url);
                val name = findViewById<android.widget.TextView>(R.id.scanProductName)
                name.text = product?.name
                val quantity = findViewById<android.widget.TextView>(R.id.scanProductQta)
                quantity.text = "Q.ta: " + product?.qta
                val brands = findViewById<android.widget.TextView>(R.id.scanProductBrands)
                brands.text = "Brand: " +product?.brands


                val carbohydrates = findViewById<android.widget.TextView>(R.id.scanProductCarb)
                carbohydrates.text = product?.carbohydrates.toString() + product?.carbohydrates_unit
                val carbohydrates_100g = findViewById<android.widget.TextView>(R.id.scanProductCarb100)
                carbohydrates_100g.text = product?.carbohydrates100.toString() + product?.carbohydrates_unit
                val proteins = findViewById<android.widget.TextView>(R.id.scanProductProtein)
                proteins.text = product?.proteins.toString() + product?.proteins_unit
                val proteins_100g = findViewById<android.widget.TextView>(R.id.scanProductProtein100)
                proteins_100g.text = product?.proteins100.toString() + product?.proteins_unit
                    val sugar = findViewById<android.widget.TextView>(R.id.scanProductSugar)
                    sugar.text = product?.sugars.toString() + product?.sugars_unit
                    val sugar_100g = findViewById<android.widget.TextView>(R.id.scanProductSugar100)
                    sugar_100g.text = product?.sugars100.toString() + product?.sugars_unit


                val fat = findViewById<android.widget.TextView>(R.id.scanProductFat)

                fat.text = product?.fats.toString() + product?.fats_unit
                val fat_100g = findViewById<android.widget.TextView>(R.id.scanProductFat100)
                fat_100g.text = product?.fats100.toString() + product?.fats_unit
                val saturated_fat = findViewById<android.widget.TextView>(R.id.scanProductSaturatedFat)
                saturated_fat.text = product?.saturatedFats.toString() + product?.saturatedFats_unit
                val saturated_fat_100g = findViewById<android.widget.TextView>(R.id.scanProductSaturatedFat100)
                saturated_fat_100g.text = product?.saturatedFats100.toString() + product?.saturatedFats_unit
                val fiber = findViewById<android.widget.TextView>(R.id.scanProductFiber)
                fiber.text = product?.fiber.toString() + product?.fiber_unit
                val fiber_100g = findViewById<android.widget.TextView>(R.id.scanProductFiber100)
                fiber_100g.text = product?.fiber100.toString() + product?.fiber_unit
                val salt = findViewById<android.widget.TextView>(R.id.scanProductSalt)
                salt.text = product?.salt.toString() + product?.salt_unit
                val salt_100g = findViewById<android.widget.TextView>(R.id.scanProductSalt100)
                salt_100g.text = product?.salt100.toString() + product?.salt_unit
                val energy_kcal = findViewById<android.widget.TextView>(R.id.scanProductCalories)
                energy_kcal.text = product?.calories.toString() + product?.calories_unit
                val energy_kcal_100g = findViewById<android.widget.TextView>(R.id.scanProductCalories100)
                energy_kcal_100g.text = product?.calories100.toString() + product?.calories_unit

                val ingredients_text_en = findViewById<android.widget.TextView>(R.id.scanProductIngredients)
                ingredients_text_en.text = product?.ingredients}else{
                    Log.i("Product", "Product not found")
                }

            }, { error ->

                Toast.makeText(this, "Error: cant't find this product " + error.message, Toast.LENGTH_LONG).show()
                Log.d("Product Detail Activity", "API CALL FAILED")
            })

        queue.add(jsonObjectRequest)
    }


}





