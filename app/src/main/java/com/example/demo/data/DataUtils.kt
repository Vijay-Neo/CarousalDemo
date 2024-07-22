package com.example.demo.data

import com.example.demo.R
import com.example.demo.data.model.CarouselData

import com.example.demo.data.model.ItemData


val fruitList = listOf(
    "Apple",
    "Banana",
    "Cherry",
    "Orange",
    "Water melon",
    "Grapes",
    "Mango",
    "Pineapple",
    "Strawberry"
)
val animalList =
    listOf("Lion", "Tiger", "Elephant", "Giraffe", "Hippo", "Leopard", "Bear", "Wolf", "Fox")
val birdList =
    listOf("Pigeon", "Parrot", "Penguin", "Ostrich", "Eagle", "Crow", "Peacock")
val vehicleList = listOf("Auto", "Bike", "Car", "Truck", "Bus", "Train", "Boat")
val foodList = listOf("Pizza", "Burger", "Pasta", "Steak", "Sushi", "Tacos", "Salad", "Soup")

val listOfAllData = listOf(
    MutableList(fruitList.size) { index ->
        ItemData(
            title = fruitList[index],
            description = fruitList[index] + " is a fruit",
            image = R.drawable.screenshot1
        )
    },
    MutableList(animalList.size) { index ->
        ItemData(
            title = animalList[index],
            description = animalList[index] + " is an animal",
            image = R.drawable.screenshot2
        )
    },
    MutableList(birdList.size) { index ->
        ItemData(
            title = birdList[index],
            description = birdList[index] + " is a bird",
            image = R.drawable.screenshot3
        )
    },
    MutableList(vehicleList.size) { index ->
        ItemData(
            title = vehicleList[index],
            description = vehicleList[index] + " is a vehicle",
            image = R.drawable.screenshot4
        )
    },
    MutableList(foodList.size) { index ->
        ItemData(
            title = foodList[index],
            description = foodList[index] + " is a food",
            image = R.drawable.screenshot5
        )
    }
)


val listOfCarousal = listOf(
    CarouselData(R.drawable.screenshot1),
    CarouselData(R.drawable.screenshot2),
    CarouselData(R.drawable.screenshot3),
    CarouselData(R.drawable.screenshot4),
    CarouselData(R.drawable.screenshot5)
)