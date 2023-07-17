package com.duydv.vn.foodappmvvm.viewmodel;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.duydv.vn.foodappmvvm.R;
import com.duydv.vn.foodappmvvm.adapter.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainViewModel extends BaseObservable {
    @BindingAdapter({"selected_btn_nav"})
    public static void setOnSelectedButtomNavigation(BottomNavigationView btn_nav, ViewPager2 viewPager2){
        //khong cho vuot man hin
        viewPager2.setUserInputEnabled(false);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter((FragmentActivity) viewPager2.getContext());
        viewPager2.setAdapter(viewPagerAdapter);

        btn_nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.nav_home){
                    viewPager2.setCurrentItem(0);
                }else if(id == R.id.nav_cart){
                    viewPager2.setCurrentItem(1);
                }else if(id == R.id.nav_contact){
                    viewPager2.setCurrentItem(2);
                }else if(id == R.id.nav_feedback){
                    viewPager2.setCurrentItem(3);
                }else if(id == R.id.nav_history){
                    viewPager2.setCurrentItem(4);
                }
                return true;
            }
        });
    }
}
