package com.example.demo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demo.data.model.CarouselData
import com.example.demo.data.model.ItemData
import com.example.demo.data.repository.MainRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.collections.set

/**
 * ViewModel for the main screen
 * */
class MainViewModelImpl(
    private val mainRepository: MainRepositoryImpl = MainRepositoryImpl()
) : ViewModel(), MainViewModel {

    private val _carousalList = MutableStateFlow<List<CarouselData>>(emptyList())
    val carousalList: StateFlow<List<CarouselData>> = _carousalList.asStateFlow()

    private var listPosition = 0
    private var list = listOf<MutableList<ItemData>>()
    private val _filteredListData = MutableStateFlow<List<ItemData>>(emptyList())
    val filteredListData: StateFlow<List<ItemData>> = _filteredListData.asStateFlow()

    init {
        getAllItemData()
    }

    // to get all vertical list data
    override fun getAllItemData() {
        viewModelScope.launch {
            list = mainRepository.getAllItemData()
            setSelection(0)
        }
    }

    // to get carousal data
    override fun getCarousalData() {
        viewModelScope.launch {
            val list = mainRepository.getAllCarousalData()
            _carousalList.value = list
        }
    }


    // to filter the list based on search text
    override fun filterListData(searchText: String) {
        val filteredList = list[listPosition].filter {
            it.title.contains(searchText, ignoreCase = true)
        }
        _filteredListData.value = filteredList
    }

    // to set the list position
    override fun setSelection(pos: Int) {
        listPosition = pos
        if (list.isNotEmpty()) _filteredListData.value = list[listPosition]
    }

    // to get top 3 characters from the list (statistic info)
    override fun getStatistics(): List<MutableMap.MutableEntry<Char, Int>> {
        val charCount = mutableMapOf<Char, Int>()
        filteredListData.value.forEach { item ->
            item.title.forEach { char ->
                if (charCount.containsKey(char.lowercaseChar())) {
                    charCount[char.lowercaseChar()] = charCount[char.lowercaseChar()]!! + 1
                } else {
                    charCount[char.lowercaseChar()] = 1
                }
            }
        }
        return charCount.entries.sortedByDescending { it.value }.take(3)
    }
}
