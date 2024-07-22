package com.example.demo.viewmodel

/**
 * interface for the view model
 * */
interface MainViewModel {

    // to get all vertical list data
    fun getAllItemData()

    // to get carousal data
    fun getCarousalData()

    // to filter the list based on search text
    fun filterListData(searchText: String)

    // to set the list position
    fun setSelection(pos: Int)

    // to get top 3 characters from the list (statistic info)
    fun getStatistics(): List<MutableMap.MutableEntry<Char, Int>>
}
