package com.duydv.vn.foodappmvvm.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.duydv.vn.foodappmvvm.R;
import com.duydv.vn.foodappmvvm.databinding.ItemHistoryBinding;
import com.duydv.vn.foodappmvvm.model.Order;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    private final List<Order> mListOrder;

    public HistoryAdapter(List<Order> mListOrder) {
        this.mListOrder = mListOrder;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHistoryBinding itemHistoryBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_history,parent,false);
        return new HistoryViewHolder(itemHistoryBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        Order order = mListOrder.get(position);
        if(order == null){
            return;
        }
        holder.itemHistoryBinding.setOrder(order);
    }

    @Override
    public int getItemCount() {
        if(mListOrder != null){
            return mListOrder.size();
        }
        return 0;
    }

    public static class HistoryViewHolder extends RecyclerView.ViewHolder {
        private final ItemHistoryBinding itemHistoryBinding;
        public HistoryViewHolder(@NonNull ItemHistoryBinding itemHistoryBinding) {
            super(itemHistoryBinding.getRoot());
            this.itemHistoryBinding = itemHistoryBinding;
        }
    }
}
