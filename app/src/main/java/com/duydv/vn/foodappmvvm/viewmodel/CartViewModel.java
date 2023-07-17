package com.duydv.vn.foodappmvvm.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.duydv.vn.foodappmvvm.R;
import com.duydv.vn.foodappmvvm.adapter.CartAdapter;
import com.duydv.vn.foodappmvvm.database.FoodDatabase;
import com.duydv.vn.foodappmvvm.databinding.DialogOrderBinding;
import com.duydv.vn.foodappmvvm.listener.ICalculateSumPrice;
import com.duydv.vn.foodappmvvm.listener.IOnClickOrder;
import com.duydv.vn.foodappmvvm.model.Food;
import com.duydv.vn.foodappmvvm.utils.Util;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

public class CartViewModel extends BaseObservable {
    private final Context mContext;
    public ObservableList<Food> mListFoodCart = new ObservableArrayList<>();

    public static String strSumPrice;

    private DialogOrderViewModel mDialogOrderViewModel;

    public static CartAdapter cartAdapter;

    public CartViewModel(Context mContext) {
        this.mContext = mContext;
        getListFoodCart();
    }

    public void getListFoodCart(){
        if(mListFoodCart != null){
            mListFoodCart.clear();
        }else{
            mListFoodCart = new ObservableArrayList<>();
        }

        List<Food> list = FoodDatabase.getInstance(mContext).foodDAO().getListFood();
        mListFoodCart.addAll(list);
    }

    @BindingAdapter(value = {"load_list_food_cart","calculate_sum_price"})
    public static void loadListFoodCart(RecyclerView recyclerView,ObservableList<Food> list, TextView textView){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);


        cartAdapter = new CartAdapter(list, new ICalculateSumPrice() {
            @Override
            public void calculateSumPrice(String sumPrice) {
                textView.setText(sumPrice);
                strSumPrice = sumPrice;
            }
        });
        strSumPrice = getStringSumPrice(recyclerView.getContext());
        textView.setText(strSumPrice);
        recyclerView.setAdapter(cartAdapter);
    }

    public static String getStringSumPrice(Context context){
        List<Food> list = FoodDatabase.getInstance(context).foodDAO().getListFood();
        if(list == null || list.isEmpty()){
            return 0 + context.getString(R.string.VND);
        }
        int sumPrice = 0;
        for(Food food : list){
            sumPrice = sumPrice + food.getSumPrice();
        }
        return sumPrice + context.getString(R.string.VND);
    }

    public void onClickOrder(){
        if(mContext == null || mListFoodCart == null || mListFoodCart.isEmpty()){
            return;
        }
        DialogOrderBinding dialogOrderBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.dialog_order,null,false);

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(mContext);
        bottomSheetDialog.setContentView(dialogOrderBinding.getRoot());

        mDialogOrderViewModel = new DialogOrderViewModel(mContext, mListFoodCart, strSumPrice, bottomSheetDialog, new IOnClickOrder() {
            @Override
            public void onClickOrder() {
                bottomSheetDialog.dismiss();
                Util.hideSoftKeyboard((Activity) mContext);
                Toast.makeText(mContext, mContext.getString(R.string.message_order_success), Toast.LENGTH_LONG).show();

                FoodDatabase.getInstance(mContext).foodDAO().deleteAll();
                clearCart();
            }
        });
        dialogOrderBinding.setDialogOrderViewModel(mDialogOrderViewModel);

        bottomSheetDialog.show();
    }
    
    public void clearCart(){
        if(mListFoodCart != null){
            mListFoodCart.clear();
        }
        cartAdapter.notifyDataSetChanged();
    }
}
