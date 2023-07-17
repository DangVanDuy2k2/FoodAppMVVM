package com.duydv.vn.foodappmvvm.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.duydv.vn.foodappmvvm.R;
import com.duydv.vn.foodappmvvm.databinding.ItemFoodPopularBinding;
import com.duydv.vn.foodappmvvm.model.Food;

import java.util.List;

public class FoodPopularAdapter extends RecyclerView.Adapter<FoodPopularAdapter.FoodPopularViewHolder> {
    private final List<Food> mListFoodPopular;

    public FoodPopularAdapter(List<Food> mListFoodPopular) {
        this.mListFoodPopular = mListFoodPopular;
    }

    @NonNull
    @Override
    public FoodPopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFoodPopularBinding itemFoodPopularBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_food_popular,parent,false);
        return new FoodPopularViewHolder(itemFoodPopularBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodPopularViewHolder holder, int position) {
        Food food = mListFoodPopular.get(position);
        if(food == null){
            return;
        }
        holder.itemFoodPopularBinding.setFood(food);
    }

    @Override
    public int getItemCount() {
        if(mListFoodPopular != null){
            return mListFoodPopular.size();
        }
        return 0;
    }

    public static class FoodPopularViewHolder extends RecyclerView.ViewHolder{
        private final ItemFoodPopularBinding itemFoodPopularBinding;
        public FoodPopularViewHolder(@NonNull ItemFoodPopularBinding itemFoodPopularBinding) {
            super(itemFoodPopularBinding.getRoot());
            this.itemFoodPopularBinding = itemFoodPopularBinding;
        }
    }
}
