package com.duydv.vn.foodappmvvm.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.duydv.vn.foodappmvvm.R;
import com.duydv.vn.foodappmvvm.databinding.FragmentHistoryBinding;
import com.duydv.vn.foodappmvvm.viewmodel.HistoryViewModel;

public class HistoryFragment extends Fragment {
    private HistoryViewModel mHistoryViewModel;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentHistoryBinding fragmentHistoryBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_history,container,false);
        mHistoryViewModel = new HistoryViewModel(getContext());
        fragmentHistoryBinding.setHistoryViewModel(mHistoryViewModel);

        return fragmentHistoryBinding.getRoot();
    }
}