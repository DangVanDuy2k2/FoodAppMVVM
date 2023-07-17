package com.duydv.vn.foodappmvvm.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.duydv.vn.foodappmvvm.adapter.HistoryAdapter;
import com.duydv.vn.foodappmvvm.model.Order;
import com.duydv.vn.foodappmvvm.utils.Util;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HistoryViewModel {
    private final Context mContext;
    public ObservableList<Order> mListOrder = new ObservableArrayList<>();

    public HistoryViewModel(Context mContext) {
        this.mContext = mContext;
        getListOrderFromFirebase();
    }

    public void getListOrderFromFirebase(){
        if(mContext == null){
            return;
        }
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("booking");

        myRef.child(Util.getDeviceId(mContext)).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Order order = snapshot.getValue(Order.class);
                if(order == null || mListOrder == null){
                    return;
                }
                mListOrder.add(0,order);
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

    @BindingAdapter({"load_list_order"})
    public static void load_list_order(RecyclerView recyclerView, ObservableList<Order> list){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        HistoryAdapter historyAdapter = new HistoryAdapter(list);
        recyclerView.setAdapter(historyAdapter);
    }
}
