package com.example.demo.data.repository

import com.example.demo.data.model.CarouselData
import com.example.demo.data.model.ItemData
import com.example.demo.data.listOfAllData
import com.example.demo.data.listOfCarousal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Single source of truth data
 * */
interface MainRepository {

    suspend fun getAllItemData() : List<MutableList<ItemData>>

    suspend fun getAllCarousalData() : List<CarouselData>
}