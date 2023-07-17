package com.duydv.vn.foodappmvvm.model;

import android.widget.TextView;

import com.duydv.vn.foodappmvvm.R;
import com.duydv.vn.foodappmvvm.utils.DateTimeUtil;

public class Order {
    private long id;
    private String name;
    private String phone;
    private String address;
    private String strSumPrice;
    private String listFoodOrder;
    private int paymentMethod;

    public Order() {
    }

    public Order(long id, String name, String phone, String address, String strSumPrice, String listFoodOrder, int paymentMethod) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.strSumPrice = strSumPrice;
        this.listFoodOrder = listFoodOrder;
        this.paymentMethod = paymentMethod;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStrSumPrice() {
        return strSumPrice;
    }

    public void setStrSumPrice(String strSumPrice) {
        this.strSumPrice = strSumPrice;
    }

    public String getListFoodOrder() {
        return listFoodOrder;
    }

    public void setListFoodOrder(String listFoodOrder) {
        this.listFoodOrder = listFoodOrder;
    }

    public int getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(int paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStringId(){
        return String.valueOf(getId());
    }

    public String getStringDate(){
        return DateTimeUtil.convertTimeStampToDate(getId());
    }

    public String getStringPaymentMethod(TextView textView){
        String strPaymentMethod = "";
        if(getPaymentMethod() == 1){
            strPaymentMethod = textView.getContext().getString(R.string.cash);
        }
        return strPaymentMethod;
    }
}
