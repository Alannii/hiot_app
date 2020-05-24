package com.huatec.hiot_cloud.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.huatec.hiot_cloud.injection.ApplicationContext;

import javax.inject.Inject;

/**
 * SharePreferences类
 */

public class SharePreferencesHelper {

    private static final String PREF_FILE_NAME = "userconfig";

    private static final String PREF_KEY_USER_TOKEN = "PREF_KEY_USER_TOKEN";
    SharedPreferences mPref;
    private Context context;

    @Inject
    public SharePreferencesHelper(@ApplicationContext Context context) {
        this.context = context;
        mPref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    public String getUserToken() {
        return mPref.getString(PREF_KEY_USER_TOKEN, "");
    }

    public void setUserToken(String value) {
        mPref.edit().putString(PREF_KEY_USER_TOKEN, value).apply();
    }
}
