package com.duydv.vn.foodappmvvm.viewmodel;

import android.content.Context;
import android.widget.Toast;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.duydv.vn.foodappmvvm.R;
import com.duydv.vn.foodappmvvm.listener.IOnClickOrder;
import com.duydv.vn.foodappmvvm.model.Food;
import com.duydv.vn.foodappmvvm.model.Order;
import com.duydv.vn.foodappmvvm.utils.StringUtil;
import com.duydv.vn.foodappmvvm.utils.Util;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DialogOrderViewModel {
    private final Context mContext;
    public ObservableList<Food> mListFoodCart = new ObservableArrayList<>();
    public String strSumPrice;

    public ObservableField<String> strName = new ObservableField<>();
    public ObservableField<String> strPhone = new ObservableField<>();
    public ObservableField<String> strAddress = new ObservableField<>();

    public IOnClickOrder mIOnClickOrder;


    private final BottomSheetDialog mBottomSheetDialog;

    public DialogOrderViewModel(Context mContext, ObservableList<Food> mListFoodCart, String strSumPrice, BottomSheetDialog mBottomSheetDialog, IOnClickOrder mIOnClickOrder) {
        this.mContext = mContext;
        this.mListFoodCart = mListFoodCart;
        this.strSumPrice = strSumPrice;
        this.mBottomSheetDialog = mBottomSheetDialog;
        this.mIOnClickOrder = mIOnClickOrder;
    }

    public String getStringListFoodsOrder() {
        if (mListFoodCart == null || mListFoodCart.isEmpty()) {
            return "";
        }
        String result = "";
        for (Food food : mListFoodCart) {
            if (StringUtil.isEmpty(result)) {
                result = "- " + food.getName() + " (" + food.getRealPrice() + mContext.getString(R.string.VND) + ") "
                        + "- " + mContext.getString(R.string.quantity) + " " + food.getCount();
            } else {
                result = result + "\n" + "- " + food.getName() + " (" + food.getRealPrice() + mContext.getString(R.string.VND) + ") "
                        + "- " + mContext.getString(R.string.quantity) + " " + food.getCount();
            }
        }
        return result;
    }

    public void onCliclCancel(){
        if(mBottomSheetDialog != null){
            mBottomSheetDialog.dismiss();
        }
    }


    public void onClickOrder(){
        String name = strName.get();
        String phone = strPhone.get();
        String address = strAddress.get();
        
        if(StringUtil.isEmpty(name) || StringUtil.isEmpty(phone) || StringUtil.isEmpty(address)){
            Toast.makeText(mContext, mContext.getString(R.string.toast_order), Toast.LENGTH_LONG).show();
        }else{
            long id = System.currentTimeMillis();
            Order order = new Order(id,name,phone,address,strSumPrice,getStringListFoodsOrder(),1);

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("booking");
            myRef.child(Util.getDeviceId(mContext))
                    .child(String.valueOf(id))
                    .setValue(order);
            mIOnClickOrder.onClickOrder();
        }
    }
}
