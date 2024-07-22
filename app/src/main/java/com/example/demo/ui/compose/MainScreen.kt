package com.example.demo.ui.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.demo.viewmodel.MainViewModelImpl
import kotlinx.coroutines.coroutineScope

/**
 *This is a main view of the activity
 * */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(viewModel: MainViewModelImpl) {
    val searchText = rememberSaveable { mutableStateOf("") }
    val carousalList by viewModel.carousalList.collectAsState()
    val pagerState = rememberPagerState(pageCount = { carousalList?.size ?: 0 })
    var showCarousel by rememberSaveable { mutableStateOf(true) }
    val scrollState = rememberLazyListState()

    val filteredItems by viewModel.filteredListData.collectAsState()

    // used to fetch data initially for carousal list
    LaunchedEffect(Unit) {
        coroutineScope {
            viewModel.getCarousalData()
        }
    }

    // used to hide the carousel when scrolled past
    LaunchedEffect(scrollState.firstVisibleItemIndex) {
        showCarousel = scrollState.firstVisibleItemIndex == 0
    }

    // used to reset data & textfield
    LaunchedEffect(pagerState.currentPage) {
        viewModel.setSelection(pagerState.currentPage)
        searchText.value = ""
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Image Carousel (conditionally shown)
        AnimatedVisibility(visible = showCarousel) {
            Column(modifier = Modifier.wrapContentHeight()) {

                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                ) { page ->
                    // Display image for the current page
                    carousalList[page].image.let {
                        Image(
                            painter = painterResource(id = it), // Replace with your image loading logic
                            contentDescription = "Image ${page + 1}",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .padding(horizontal = 10.dp)
                                .border(
                                    shape =
                                    RoundedCornerShape(15.dp),
                                    border = BorderStroke(.5.dp, Color.Gray)
                                )
                                .fillMaxSize()
                                .clip(
                                    RoundedCornerShape(15.dp)
                                )
                        )
                    }
                }

                // used to draw circle indicator
                Row(
                    Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(pagerState.pageCount) { iteration ->
                        val color =
                            if (pagerState.currentPage == iteration) Color.LightGray else Color.DarkGray
                        Box(
                            modifier = Modifier
                                .padding(2.dp)
                                .clip(CircleShape)
                                .background(color)
                                .size(10.dp)
                        )
                    }
                }

            }
        }

        // Search Bar (sticky)
        TextField(
            value = searchText.value,
            onValueChange = {
                viewModel.filterListData(it)
                searchText.value = it
            },
            textStyle = TextStyle(
                color = Color.White,
            ),
            placeholder = { Text("Search", color = Color.Gray) },
            singleLine = true,
            shape = RoundedCornerShape(8.dp),
            leadingIcon = {
                Icon(Icons.Filled.Search, "", tint = Color.Gray)
            },
            colors = TextFieldDefaults.colors().copy(
                focusedContainerColor = Color.DarkGray,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedContainerColor = Color.DarkGray,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )

        filteredItems.let {
            // List
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                state = scrollState // Attach scroll state
            ) {
                items(it) { item ->
                    VerticalListModel(item)
                }
            }
        }
    }
}