package com.duydv.vn.foodappmvvm.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.duydv.vn.foodappmvvm.R;
import com.duydv.vn.foodappmvvm.databinding.ActivityFoodDetailBinding;
import com.duydv.vn.foodappmvvm.model.Food;
import com.duydv.vn.foodappmvvm.viewmodel.FoodDetailViewModel;

public class FoodDetailActivity extends AppCompatActivity {
    private FoodDetailViewModel mFoodDetailViewModel;
    private Food mFood;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityFoodDetailBinding activityFoodDetailBinding = DataBindingUtil.setContentView(this,R.layout.activity_food_detail);

        getDataIntent();
        mFoodDetailViewModel = new FoodDetailViewModel(mFood,this);
        activityFoodDetailBinding.setFoodDetailViewModel(mFoodDetailViewModel);

        setContentView(activityFoodDetailBinding.getRoot());
    }

    private void getDataIntent() {
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            mFood = (Food) bundle.get(getString(R.string.key));
        }
    }
}