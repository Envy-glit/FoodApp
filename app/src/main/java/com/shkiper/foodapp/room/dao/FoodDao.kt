package com.shkiper.foodapp.room.dao

import androidx.room.*
import com.shkiper.foodapp.room.entity.Food
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(foods: List<Food>)

    @Update
    fun updateFood(food: Food): Single<Int>

    @Query("SELECT * FROM FOOD")
    fun getAllList(): Flowable<List<Food>>

    @Query("SELECT * FROM FOOD WHERE quantity > 0")
    fun getCartList(): Flowable<List<Food>>

    @Delete
    fun deleteFood(food: Food)

}