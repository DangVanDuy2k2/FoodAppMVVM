package com.duydv.vn.foodappmvvm.listener;

import android.content.Context;

import com.duydv.vn.foodappmvvm.model.Food;

public interface IOnClickCartItem {
    void updateFood(Context context, Food food, int position);
    void deleteFood(Context context, Food food, int position);
}
