package com.example.demo.data.repository

import com.example.demo.data.model.CarouselData
import com.example.demo.data.model.ItemData
import com.example.demo.data.listOfAllData
import com.example.demo.data.listOfCarousal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Implementation of MainRepository
 * */
class MainRepositoryImpl : MainRepository {

    override suspend fun getAllItemData() : List<MutableList<ItemData>> = withContext(Dispatchers.IO) {
        listOfAllData
    }

    override suspend fun getAllCarousalData() : List<CarouselData> = withContext(Dispatchers.IO) {
        listOfCarousal
    }
}