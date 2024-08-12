package com.example.dunifoodsimple.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface FoodDao {

    @Insert
    fun insertFood(food: Food)

    @Insert
    fun insertAllFood(data: ArrayList<Food>)

    @Update
    fun updateFood(food: Food)

    @Delete
    fun deleteFood(food: Food)

    @Query("DELETE FROM TABLE_FOOD")
    fun deleteAllFoods()

    @Query("SELECT * FROM TABLE_FOOD")
    fun getAllFoods(): List<Food>

    @Query("SELECT * FROM TABLE_FOOD WHERE txtSubject LIKE '%' || :searching || '%' ")
    fun searchFoods(searching: String): List<Food>

}