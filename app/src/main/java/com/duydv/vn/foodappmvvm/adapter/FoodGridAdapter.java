package com.duydv.vn.foodappmvvm.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.duydv.vn.foodappmvvm.R;
import com.duydv.vn.foodappmvvm.databinding.ItemFoodGridBinding;
import com.duydv.vn.foodappmvvm.model.Food;

import java.util.List;

public class FoodGridAdapter extends RecyclerView.Adapter<FoodGridAdapter.FoodGridViewHolder> {

    private final List<Food> mListFood;

    public FoodGridAdapter(List<Food> mListFoods) {
        this.mListFood = mListFoods;
    }

    @NonNull
    @Override
    public FoodGridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFoodGridBinding itemFoodGridBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_food_grid,parent,false);
        return new FoodGridViewHolder(itemFoodGridBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodGridViewHolder holder, int position) {
        Food food = mListFood.get(position);
        if (food == null) {
            return;
        }
        holder.mItemFoodGridBinding.setFood(food);
    }

    @Override
    public int getItemCount() {
        if(mListFood != null){
            return mListFood.size();
        }
        return 0;
    }

    public static class FoodGridViewHolder extends RecyclerView.ViewHolder {

        private final ItemFoodGridBinding mItemFoodGridBinding;

        public FoodGridViewHolder(ItemFoodGridBinding itemFoodGridBinding) {
            super(itemFoodGridBinding.getRoot());
            this.mItemFoodGridBinding = itemFoodGridBinding;
        }
    }
}
