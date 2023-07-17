package com.duydv.vn.foodappmvvm.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.duydv.vn.foodappmvvm.R;

public class GlideUtil {
    public static void loadUrl(String url, ImageView imageView) {
        if (StringUtil.isEmpty(url)) {
            imageView.setImageResource(R.drawable.img_no_image);
            return;
        }
        Glide.with(imageView.getContext())
                .load(url)
                .error(R.drawable.img_no_image)
                .into(imageView);
    }
}
