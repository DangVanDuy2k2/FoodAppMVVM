package com.duydv.vn.foodappmvvm.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.duydv.vn.foodappmvvm.databinding.FragmentHomeBinding;
import com.duydv.vn.foodappmvvm.viewmodel.HomeViewModel;

public class HomeFragment extends Fragment {
    private HomeViewModel mHomeViewModel;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentHomeBinding fragmentHomeBinding = FragmentHomeBinding.inflate(inflater,container,false);
        mHomeViewModel = new HomeViewModel();
        mHomeViewModel.getListFoodGridFromFireBase("");
        fragmentHomeBinding.setHomeViewModel(mHomeViewModel);

        return fragmentHomeBinding.getRoot();
    }
}