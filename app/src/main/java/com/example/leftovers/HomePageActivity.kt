package com.example.leftovers

//import android.widget.Button
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2


class HomePageActivity : AppCompatActivity() {

    private lateinit var viewPager2: ViewPager2
    private var sliderHandler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)
        handleUsername("Welcome, Gianni")

        viewPager2 = findViewById(R.id.viewPage)

        val sliderItems: MutableList<SliderRecipe> = ArrayList()
        sliderItems.add(SliderRecipe(R.drawable.smothie))
        sliderItems.add(SliderRecipe(R.drawable.banana_bread))
        sliderItems.add(SliderRecipe(R.drawable.teriyaki))
        sliderItems.add(SliderRecipe(R.drawable.tacos))

        viewPager2.adapter = SliderAdapter(sliderItems, viewPager2)
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.offscreenPageLimit = 3
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(30))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - kotlin.math.abs(
                position
            )
            page.scaleY = 0.85f + r * 0.25f
        }

        viewPager2.setPageTransformer(compositePageTransformer)
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                sliderHandler.removeCallbacks(sliderRunnable)
                sliderHandler.postDelayed(sliderRunnable, 3000)
            }

        })


        //val plusRecipeBtn = findViewById<Button>(R.id.plusButton)

        //plusRecipeBtn.setOnClickListener(){
        //  goToRecipe()
        //}

/*
        bottomAppBar.setNavigationOnClickListener {
            // Handle navigation icon press
        }


        bottomAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.search -> {
                    // Handle search icon press
                    true
                }
                R.id.more -> {
                    // Handle more item (inside overflow menu) press
                    true
                }
                else -> false
            }
        }*/
    }

    private val sliderRunnable = Runnable {
        viewPager2.currentItem = viewPager2.currentItem + 1
    }

    private fun handleUsername(username: String) {
        var text = findViewById<TextView>(R.id.usernameWelcome)
        text.text = username

    }


    override fun onPause() {
        super.onPause()
        sliderHandler.postDelayed(sliderRunnable, 3000)
    }

    override fun onResume() {
        super.onResume()
        sliderHandler.postDelayed(sliderRunnable, 3000)
    }

    private fun goToRecipe() {
        val intent = Intent(this, RecipeCreatorActivity::class.java)
        startActivity(intent)

    }
}