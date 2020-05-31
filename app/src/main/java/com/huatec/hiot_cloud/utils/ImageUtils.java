package com.huatec.hiot_cloud.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.huatec.hiot_cloud.R;
import com.huatec.hiot_cloud.data.NetworkService;

public class ImageUtils {

    /**
     * 返回图片全路径
     */
    public static String getFullUrl(String url) {
        return NetworkService.baseUrl + url;
    }

    public static void show(Context context, ImageView imageView, String url) {
        Glide.with(context).load(url).apply(getCommonOptions()).into(imageView);
    }

    public static void showCircle(Context context, ImageView imageView, String url) {
        Glide.with(context).load(url).apply(getCommonOptions()).circleCrop().into(imageView);
    }

    private static RequestOptions getCommonOptions() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .centerCrop();
        return requestOptions;
    }
}
