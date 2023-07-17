package com.duydv.vn.foodappmvvm.viewmodel;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;

import com.duydv.vn.foodappmvvm.R;
import com.duydv.vn.foodappmvvm.database.FoodDatabase;
import com.duydv.vn.foodappmvvm.listener.IOnClickAddToCart;
import com.duydv.vn.foodappmvvm.model.Food;
import com.duydv.vn.foodappmvvm.utils.GlideUtil;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class DialogCartViewModel extends BaseObservable {
    private final Activity mActivity;

    public Food mFood;
    private final BottomSheetDialog mBottomSheetDialog;
    public ObservableField<String> strPrice = new ObservableField<>();

    private final IOnClickAddToCart mIOnClickAddToCart;


    public DialogCartViewModel(Activity mActivity, Food mFood, BottomSheetDialog mBottomSheetDialog, IOnClickAddToCart mIOnClickAddToCart) {
        this.mActivity = mActivity;
        this.mFood = mFood;
        this.mBottomSheetDialog = mBottomSheetDialog;
        this.mIOnClickAddToCart = mIOnClickAddToCart;
        initData();
    }

    private void initData() {
        int realPrice = mFood.getRealPrice();
        String strRealPrice = realPrice + mActivity.getString(R.string.VND);
        strPrice.set(strRealPrice);

        mFood.setCount(1);
        mFood.setSumPrice(realPrice);
    }


    public void onClickMinusFood(TextView textView){
        int count = Integer.parseInt(textView.getText().toString().trim());
        if(count <= 1){
            return;
        }
        int newCount = Integer.parseInt(textView.getText().toString().trim()) - 1;
        textView.setText(String.valueOf(newCount));

        int sumPrice1 = mFood.getRealPrice() * newCount;
        String strSumPrice1 = sumPrice1 + textView.getContext().getString(R.string.VND);
        strPrice.set(strSumPrice1);

        mFood.setCount(newCount);
        mFood.setSumPrice(sumPrice1);
    }

    public void onClickPhusFood(TextView textView){
        int newCount = Integer.parseInt(textView.getText().toString().trim()) + 1;
        textView.setText(String.valueOf(newCount));

        int sumPrice2 = mFood.getRealPrice() * newCount;
        String strSumPrice2 = sumPrice2 + textView.getContext().getString(R.string.VND);
        strPrice.set(strSumPrice2);

        mFood.setCount(newCount);
        mFood.setSumPrice(sumPrice2);
    }

    public void onClickAddToCart(){
        FoodDatabase.getInstance(mActivity).foodDAO().insert(mFood);
        Toast.makeText(mActivity, "Insert Succesfuly", Toast.LENGTH_SHORT).show();
        mIOnClickAddToCart.setStrButton();
    }

    public void onClickCancle(){
        if(mBottomSheetDialog != null){
            mBottomSheetDialog.dismiss();
        }
    }

    @BindingAdapter({"load_image_food"})
    public static void loadImage(ImageView imageView, String url){
        GlideUtil.loadUrl(url,imageView);
    }
}
