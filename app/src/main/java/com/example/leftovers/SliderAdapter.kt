package com.example.leftovers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.makeramen.roundedimageview.RoundedImageView

class SliderAdapter internal constructor(
    sliderItems: MutableList<SliderRecipe>,
    viewPager2: ViewPager2):
    RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    private val viewPager2: ViewPager2
    private val sliderItems : List<SliderRecipe>

    init {
        this.sliderItems = sliderItems
        this.viewPager2 = viewPager2

    }


    class SliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val imageView: RoundedImageView = itemView.findViewById(R.id.imageSlider)

        fun image(sliderRecipe: SliderRecipe){
            imageView.setImageResource(sliderRecipe.image)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        return SliderViewHolder(
            LayoutInflater.from(
                parent.context).inflate(
                R.layout.slide_container, parent, false
            )
        )

    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
       holder.image(sliderItems[position])
        if(position == sliderItems.size - 2){
            viewPager2.post(runnable)

        }
    }

    override fun getItemCount(): Int {
        return sliderItems.size
    }

    private val runnable = Runnable {
        sliderItems.addAll(sliderItems)
        notifyDataSetChanged()
    }
}