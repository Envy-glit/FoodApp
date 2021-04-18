package com.shkiper.foodapp.ui

import com.shkiper.foodapp.room.entity.Food

data class MainState(
    var loading: Boolean = false,
    var success: Boolean = false,
    var failure: Boolean = false,
    var message: String? = null,
    var list: List<Food>? = listOf()
)