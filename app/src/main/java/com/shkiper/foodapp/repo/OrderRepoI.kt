package com.shkiper.foodapp.repo

import com.shkiper.foodapp.room.entity.Food
import io.reactivex.Flowable
import io.reactivex.Single


interface OrderRepoI {
    fun update(food: Food): Single<Int>

    fun getCartItem(): Flowable<List<Food>>

    fun getAllItem(): Flowable<List<Food>>
}