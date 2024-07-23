package com.example.demo.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.data.model.CarouselData
import com.example.demo.databinding.CarouselItemBinding

/**
 * horizontal images carousel adapter
 * */
class ImageCarouselAdapter(private val imageList: List<CarouselData>) :
    RecyclerView.Adapter<ImageCarouselAdapter.ImageViewHolder>() {

    class ImageViewHolder(val binding: CarouselItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding =
            CarouselItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.binding.imageView.setImageResource(imageList[position].image)
    }

    override fun getItemCount(): Int = imageList.size
}