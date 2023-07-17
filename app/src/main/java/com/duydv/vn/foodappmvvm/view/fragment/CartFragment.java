package com.duydv.vn.foodappmvvm.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.duydv.vn.foodappmvvm.R;
import com.duydv.vn.foodappmvvm.databinding.FragmentCartBinding;
import com.duydv.vn.foodappmvvm.viewmodel.CartViewModel;

public class CartFragment extends Fragment {
    private CartViewModel mCartViewModel;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentCartBinding fragmentCartBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_cart,container,false);
        mCartViewModel = new CartViewModel(getContext());
        fragmentCartBinding.setCartViewModel(mCartViewModel);

        return fragmentCartBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        if(mCartViewModel != null){
            mCartViewModel.getListFoodCart();
        }
    }
}