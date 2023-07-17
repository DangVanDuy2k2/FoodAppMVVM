package com.duydv.vn.foodappmvvm.viewmodel;

import static com.duydv.vn.foodappmvvm.R.drawable.bg_gray_shape_corner_6;

import android.app.Activity;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.widget.TextViewCompat;
import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

import com.duydv.vn.foodappmvvm.R;
import com.duydv.vn.foodappmvvm.database.FoodDatabase;
import com.duydv.vn.foodappmvvm.databinding.DialogAddToCartBinding;
import com.duydv.vn.foodappmvvm.listener.IOnClickAddToCart;
import com.duydv.vn.foodappmvvm.model.Food;
import com.duydv.vn.foodappmvvm.utils.GlideUtil;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

public class FoodDetailViewModel extends BaseObservable {
    public Food mFood;
    private final Activity mActivity;
    private DialogCartViewModel mDialogCartViewModel;

    public ObservableBoolean isFoodInCart = new ObservableBoolean();

    public ObservableField<String> strButton = new ObservableField<>();

    public FoodDetailViewModel(Food mFood, Activity mActivity) {
        this.mFood = mFood;
        this.mActivity = mActivity;
        initData();
    }

    private void initData() {
        if(isFoodExists(mFood.getId())){
            isFoodInCart.set(true);
            strButton.set(mActivity.getString(R.string.added_to_cart));
        }else{
            isFoodInCart.set(false);
            strButton.set(mActivity.getString(R.string.add_to_cart));
        }
    }

    @BindingAdapter({"load_image_food"})
    public static void loadImageFood(ImageView imageView,String url){
        GlideUtil.loadUrl(url, imageView);
    }

    public void onClickBack(){
        mActivity.onBackPressed();
    }

    public void deleteAll(){
        FoodDatabase.getInstance(mActivity).foodDAO().deleteAll();
    }

    public void onClickAddToCart(){
        if(isFoodExists(mFood.getId())){
            return;
        }
        DialogAddToCartBinding dialogAddToCartBinding = DataBindingUtil.inflate(LayoutInflater.from(mActivity),
                R.layout.dialog_add_to_cart,null,false);

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(mActivity);
        bottomSheetDialog.setContentView(dialogAddToCartBinding.getRoot());

        mDialogCartViewModel = new DialogCartViewModel(mActivity, mFood, bottomSheetDialog, new IOnClickAddToCart() {
            @Override
            public void setStrButton() {
                bottomSheetDialog.dismiss();
                strButton.set(mActivity.getString(R.string.added_to_cart));
                isFoodInCart.set(true);
            }
        });
        dialogAddToCartBinding.setDialogCartViewModel(mDialogCartViewModel);

        bottomSheetDialog.show();
    }

    public boolean isFoodExists(int foodID){
        List<Food> list = FoodDatabase.getInstance(mActivity).foodDAO().checkFoodExists(foodID);
        return list != null && !list.isEmpty();
    }

    public ObservableField<String> getStrButton(TextView textView){
        if(isFoodExists(mFood.getId())){
            textView.setBackgroundResource(bg_gray_shape_corner_6);
            textView.setTextColor(ContextCompat.getColor(mActivity,R.color.black));
        }else{
            textView.setBackgroundResource(R.drawable.bg_green_shape_corner_6);
            textView.setTextColor(ContextCompat.getColor(mActivity,R.color.white));
        }

        return strButton;
    }
}
