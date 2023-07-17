package com.duydv.vn.foodappmvvm.model;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.duydv.vn.foodappmvvm.BR;
import com.duydv.vn.foodappmvvm.R;
import com.duydv.vn.foodappmvvm.utils.DateTimeUtil;
import com.duydv.vn.foodappmvvm.utils.StringUtil;
import com.duydv.vn.foodappmvvm.utils.Util;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class Feedback extends BaseObservable {
    private String name;
    private String phone;
    private String email;
    private String comment;

    public Feedback() {
    }

    public Feedback(String name, String phone, String email, String comment) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.comment = comment;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }
    @Bindable
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        notifyPropertyChanged(BR.phone);
    }
    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }
    @Bindable
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
        notifyPropertyChanged(BR.comment);
    }

    public void onLickSendFeedback(TextView textView){
        Context context = textView.getContext();
        if(StringUtil.isEmpty(name)){
            Toast.makeText(context, context.getString(R.string.feedback_toat_name), Toast.LENGTH_LONG).show();
        } else if (StringUtil.isEmpty(comment)) {
            Toast.makeText(context, context.getString(R.string.feedback_toat_comment), Toast.LENGTH_LONG).show();
        }else {
            Feedback feedback = new Feedback(name,phone, email,comment);

            long id = System.currentTimeMillis();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("feedback");
            myRef.child(String.valueOf(id))
                    .setValue(feedback);
            Toast.makeText(context, context.getString(R.string.send_feedback_success), Toast.LENGTH_LONG).show();
            Util.hideSoftKeyboard((Activity) context);
            setName("");
            setPhone("");
            setEmail("");
            setComment("");
        }
    }
}
