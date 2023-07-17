package com.duydv.vn.foodappmvvm.model;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.duydv.vn.foodappmvvm.BR;
import com.duydv.vn.foodappmvvm.R;
import com.duydv.vn.foodappmvvm.listener.IOnClickCartItem;
import com.duydv.vn.foodappmvvm.utils.GlideUtil;
import com.duydv.vn.foodappmvvm.view.activity.FoodDetailActivity;

import java.io.Serializable;

@Entity(tableName = "food")
public class Food extends BaseObservable implements Serializable {
    @PrimaryKey
    private int id;
    private String name;
    private String image;
    private boolean popular;
    private int price;
    private int sale;

    private int count;
    private int sumPrice;

    @Ignore
    private String description;

    @Ignore
    private IOnClickCartItem mIOnClickCartItem;

    private int position;

    public Food() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isPopular() {
        return popular;
    }

    public void setPopular(boolean popular) {
        this.popular = popular;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Bindable
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
        notifyPropertyChanged(BR.count);
    }

    @Bindable
    public int getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(int sumPrice) {
        this.sumPrice = sumPrice;
        notifyPropertyChanged(BR.sumPrice);
    }

    public IOnClickCartItem getmIOnClickCartItem() {
        return mIOnClickCartItem;
    }

    public void setmIOnClickCartItem(IOnClickCartItem mIOnClickCartItem) {
        this.mIOnClickCartItem = mIOnClickCartItem;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @BindingAdapter({"load_image_food"})
    public static void loadImage(ImageView imageView, String url){
        GlideUtil.loadUrl(url,imageView);
    }

    public boolean isSaleOff(){
        return getSale() > 0;
    }

    public String getStringSale(){
        return "Giảm " + getSale() + "%";
    }

    public int getRealPrice() {
        if (sale <= 0) {
            return price;
        }
        return price - (price * sale / 100);
    }

    public String getStringOldPrice(TextView textview) {
        textview.setPaintFlags(textview.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        return getPrice() + ".000 VND";
    }

    public String getStringRealPrice() {
        if (isSaleOff()) {
            return getRealPrice() + ".000 VND";
        } else {
            return getPrice() + ".000 VND";
        }
    }

    public void onClickItemFoodGrid(View view){
        Intent intent = new Intent(view.getContext(),FoodDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(view.getContext().getString(R.string.key),this);
        intent.putExtras(bundle);
        view.getContext().startActivity(intent);
    }

    public String getStringCount(){
        return String.valueOf(getCount());
    }

    public void onClickPlusFood(TextView textView){
        int newCount = getCount() + 1;
        textView.setText(String.valueOf(newCount));
        int sumPrice = getRealPrice() * newCount;

        setCount(newCount);
        setSumPrice(sumPrice);

        mIOnClickCartItem.updateFood(textView.getContext(),this,getPosition());
    }

    public void onClickMinusFood(TextView textView){
        int count = getCount();
        if (count <= 1) {
            return;
        }
        int newCount = count - 1;
        textView.setText(String.valueOf(newCount));
        int sumPrice = getRealPrice() * newCount;

        setCount(newCount);
        setSumPrice(sumPrice);

        mIOnClickCartItem.updateFood(textView.getContext(),this,getPosition());
    }

    public void onClickDeleteFood(TextView textView){
        mIOnClickCartItem.deleteFood(textView.getContext(),this,getPosition());
    }
}
