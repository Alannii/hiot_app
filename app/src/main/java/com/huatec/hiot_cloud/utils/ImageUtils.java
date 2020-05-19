package com.huatec.hiot_cloud.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.huatec.hiot_cloud.R;

public class ImageUtils {

    public static void show(Context context, ImageView imageView, String url) {
        Glide.with(context).load(url).apply(getCommonOptions()).into(imageView);
    }

    private static RequestOptions getCommonOptions() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .centerCrop();
        return requestOptions;
    }
}
