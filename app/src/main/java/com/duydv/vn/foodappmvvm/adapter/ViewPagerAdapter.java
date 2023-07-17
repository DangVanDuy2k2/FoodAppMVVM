package com.duydv.vn.foodappmvvm.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.duydv.vn.foodappmvvm.view.fragment.CartFragment;
import com.duydv.vn.foodappmvvm.view.fragment.ContactFragment;
import com.duydv.vn.foodappmvvm.view.fragment.FeedbackFragment;
import com.duydv.vn.foodappmvvm.view.fragment.HistoryFragment;
import com.duydv.vn.foodappmvvm.view.fragment.HomeFragment;


public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new HomeFragment();
            case 1:
                return new CartFragment();
            case 2:
                return new ContactFragment();
            case 3:
                return new FeedbackFragment();
            case 4:
                return new HistoryFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
