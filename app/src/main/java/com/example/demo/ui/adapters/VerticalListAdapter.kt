package com.example.demo.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.data.model.ItemData
import com.example.demo.databinding.ListItemBinding

/**
 * vertical list adapter for the dataItems
 */
class VerticalListAdapter : RecyclerView.Adapter<VerticalListAdapter.ListViewHolder>() {

    private var dataList: List<ItemData> = emptyList()
    class ListViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.binding.txtTitle.text = dataList[position].title
        holder.binding.txtDesc.text = dataList[position].description
        holder.binding.imgItem.setImageResource(dataList[position].image)

    }

    override fun getItemCount(): Int = dataList.size

    fun updateData(newData: List<ItemData>) {
        dataList = newData
        notifyDataSetChanged()
    }
}