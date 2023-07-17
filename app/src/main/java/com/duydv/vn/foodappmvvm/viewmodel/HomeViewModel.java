package com.duydv.vn.foodappmvvm.viewmodel;

import android.app.Activity;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.duydv.vn.foodappmvvm.R;
import com.duydv.vn.foodappmvvm.adapter.FoodGridAdapter;
import com.duydv.vn.foodappmvvm.adapter.FoodPopularAdapter;
import com.duydv.vn.foodappmvvm.model.Food;
import com.duydv.vn.foodappmvvm.utils.StringUtil;
import com.duydv.vn.foodappmvvm.utils.Util;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import me.relex.circleindicator.CircleIndicator3;

public class HomeViewModel extends BaseObservable {
    public ObservableList<Food> mListFood = new ObservableArrayList<>();

    public ObservableList<Food> mListFoodPopular = new ObservableArrayList<>();

    public ObservableField<String> stringHintSearch = new ObservableField<>();

    public void getListFoodGridFromFireBase(String key){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("food");

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Food food = snapshot.getValue(Food.class);
                if(food == null || mListFood == null){
                    return;
                }
                if (StringUtil.isEmpty(key)) {
                    mListFood.add(food);
                } else {
                    if(food.getName().toLowerCase().contains(key.trim().toLowerCase())){
                        mListFood.add(food);
                    }
                }
                getListFoodPopular(mListFood);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void getListFoodPopular(ObservableList<Food> mListFood){
        if (mListFoodPopular != null) {
            mListFoodPopular.clear();
        } else {
            mListFoodPopular = new ObservableArrayList<>();
        }
        if (mListFood == null || mListFood.isEmpty()) {
            return;
        }
        for (Food food : mListFood) {
            if (food.isPopular()) {
                mListFoodPopular.add(food);
            }
        }
    }

    public void onClickButtonSearch(EditText editText, LinearLayout linearLayout) {
        String keyword = editText.getText().toString().trim();
        searchFood(keyword);
        if(StringUtil.isEmpty(keyword)){
            linearLayout.setVisibility(View.VISIBLE);
        }else{
            linearLayout.setVisibility(View.GONE);
        }
        Util.hideSoftKeyboard((Activity) editText.getContext());
    }

    private void searchFood(String key) {
        if (mListFood != null) {
            mListFood.clear();
        }
        getListFoodGridFromFireBase(key);
    }

    public ObservableField<String> setStringHintSearch(EditText editText){
        stringHintSearch.set(editText.getContext().getString(R.string.hint_search_food));

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String strKey = editable.toString().trim();
                if (StringUtil.isEmpty(strKey)) {
                    searchFood("");
                }
            }
        });

        return stringHintSearch;
    }

    @BindingAdapter({"load_list_food"})
    public static void loadListFood(RecyclerView recyclerView, ObservableList<Food> list){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(recyclerView.getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        FoodGridAdapter foodAdapter = new FoodGridAdapter(list);
        recyclerView.setAdapter(foodAdapter);
    }

    @BindingAdapter(value = {"load_list_food_popular","indicator_viewpager2"})
    public static void loadListFoodPopular(ViewPager2 viewPager2,ObservableList<Food> list, CircleIndicator3 indicator3){
        FoodPopularAdapter foodPopularAdapter = new FoodPopularAdapter(list);
        viewPager2.setAdapter(foodPopularAdapter);

        Handler handlerBanner = new Handler();
        Runnable runnable = () -> {
            if (list == null || list.isEmpty()) {
                return;
            }
            if (viewPager2.getCurrentItem() == list.size() - 1) {
                viewPager2.setCurrentItem(0);
                return;
            }
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        };
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handlerBanner.removeCallbacks(runnable);
                handlerBanner.postDelayed(runnable, 3000);
            }
        });

        indicator3.setViewPager(viewPager2);
    }
}
