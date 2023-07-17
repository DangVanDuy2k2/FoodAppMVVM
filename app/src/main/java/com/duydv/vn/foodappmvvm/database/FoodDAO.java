package com.duydv.vn.foodappmvvm.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.duydv.vn.foodappmvvm.model.Food;

import java.util.List;

@Dao
public interface FoodDAO {
    @Insert
    void insert(Food food);

    @Query("SELECT * FROM food")
    List<Food> getListFood();

    @Query("SELECT * FROM food WHERE id = :id")
    List<Food> checkFoodExists(int id);

    @Delete
    void deleteFood(Food food);

    @Update
    void updateFood(Food food);

    @Query("DELETE FROM food")
    void deleteAll();
}
