package com.shkiper.foodapp.ui.adapter

import com.shkiper.foodapp.room.entity.Food

interface OnClickListener {
    fun update(food: Food, position: Int)
}
