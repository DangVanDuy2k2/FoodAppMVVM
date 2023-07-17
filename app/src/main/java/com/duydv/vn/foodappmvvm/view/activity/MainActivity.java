package com.duydv.vn.foodappmvvm.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.duydv.vn.foodappmvvm.databinding.ActivityMainBinding;
import com.duydv.vn.foodappmvvm.viewmodel.MainViewModel;
import com.duydv.vn.foodappmvvm.R;

public class MainActivity extends AppCompatActivity {
    private MainViewModel mMainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mMainViewModel = new MainViewModel();
        activityMainBinding.setMainViewModel(mMainViewModel);
        setContentView(activityMainBinding.getRoot());
    }
}