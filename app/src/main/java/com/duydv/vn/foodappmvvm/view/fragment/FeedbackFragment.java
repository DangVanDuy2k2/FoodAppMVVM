package com.duydv.vn.foodappmvvm.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.duydv.vn.foodappmvvm.R;
import com.duydv.vn.foodappmvvm.databinding.FragmentFeedbackBinding;
import com.duydv.vn.foodappmvvm.model.Feedback;

public class FeedbackFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentFeedbackBinding fragmentFeedbackBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_feedback,container,false);
        Feedback feedback = new Feedback();
        fragmentFeedbackBinding.setFeedback(feedback);

        return fragmentFeedbackBinding.getRoot();
    }
}