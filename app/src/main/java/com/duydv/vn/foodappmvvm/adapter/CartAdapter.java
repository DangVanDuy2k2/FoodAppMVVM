package com.duydv.vn.foodappmvvm.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.duydv.vn.foodappmvvm.R;
import com.duydv.vn.foodappmvvm.database.FoodDatabase;
import com.duydv.vn.foodappmvvm.databinding.ItemCartBinding;
import com.duydv.vn.foodappmvvm.listener.ICalculateSumPrice;
import com.duydv.vn.foodappmvvm.listener.IOnClickCartItem;
import com.duydv.vn.foodappmvvm.model.Food;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> implements IOnClickCartItem{
    private final List<Food> mListFoodCart;
    private final ICalculateSumPrice mICalculateSumPrice;

    public CartAdapter(List<Food> mListFoodCart, ICalculateSumPrice mICalculateSumPrice) {
        this.mListFoodCart = mListFoodCart;
        this.mICalculateSumPrice = mICalculateSumPrice;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCartBinding itemCartBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_cart,parent,false);
        return new CartViewHolder(itemCartBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Food food = mListFoodCart.get(position);
        if(food == null){
            return;
        }
        food.setmIOnClickCartItem(this);
        food.setPosition(position);
        holder.itemCartBinding.setFood(food);
    }

    @Override
    public int getItemCount() {
        if(mListFoodCart != null){
            return mListFoodCart.size();
        }
        return 0;
    }

    @Override
    public void updateFood(Context context, Food food, int position) {
        FoodDatabase.getInstance(context).foodDAO().updateFood(food);
        notifyItemChanged(position);
        calculateSumPrice(context);
    }

    @Override
    public void deleteFood(Context context, Food food, int position) {
        showConfirmDialogDeleteFood(context,food,position);
    }

    private void showConfirmDialogDeleteFood(Context context, Food food, int position) {
        new AlertDialog.Builder(context)
                .setTitle(context.getString(R.string.title_dialod_delete_food))
                .setMessage(context.getString(R.string.message_dialog_delete_food))
                .setPositiveButton(context.getString(R.string.delete), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FoodDatabase.getInstance(context).foodDAO().deleteFood(food);

                        mListFoodCart.remove(position);
                        notifyItemRemoved(position);
                        calculateSumPrice(context);
                    }
                })
                .setNegativeButton(context.getString(R.string.dialog_cancel),null)
                .show();
    }

    private void calculateSumPrice(Context context) {
        List<Food> listFood = FoodDatabase.getInstance(context).foodDAO().getListFood();
        if (listFood == null || listFood.isEmpty()) {
            String strZero = 0 + context.getString(R.string.VND);
            mICalculateSumPrice.calculateSumPrice(strZero);
            return;
        }

        int SumPrice = 0;
        for (Food food : listFood) {
            SumPrice = SumPrice + food.getSumPrice();
        }

        String strSumPrice = SumPrice + context.getString(R.string.VND);
        mICalculateSumPrice.calculateSumPrice(strSumPrice);
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder{
        private final ItemCartBinding itemCartBinding;
        public CartViewHolder(@NonNull ItemCartBinding itemCartBinding) {
            super(itemCartBinding.getRoot());

            this.itemCartBinding = itemCartBinding;
        }
    }
}
