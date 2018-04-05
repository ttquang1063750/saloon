package com.product.nguyencongduc.bookticketapplication.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by user on 4/1/2018.
 */

public final class LocalStorage {

    /**
     * The list share preferences keys
     */
    private static final String KEY_USER_ID = "KEY_USER_ID";
    private static final String KEY_USER_NAME = "KEY_USER_NAME";
    private static final String KEY_PASSWORD = "KEY_PASSWORD";
    private static final String SAVE_LOGIN = "SAVE_LOGIN";
    private static LocalStorage sInstance;
    private final SharedPreferences mSharedPreferences;
    private final SharedPreferences.Editor mEditor;

    public LocalStorage(Context context) {
        mSharedPreferences = context.getSharedPreferences("local", Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }

    public boolean commit() {
        return mEditor.commit();
    }

    public LocalStorage setUserId(Long userId) {
        mEditor.putLong(KEY_USER_ID, userId);
        return this;
    }

    public LocalStorage setLoginSetting(boolean isSave, String userId, String pass) {
        mEditor.putBoolean(SAVE_LOGIN,isSave);
        if (isSave) {
            mEditor.putString(KEY_USER_NAME,userId);
            mEditor.putString(KEY_PASSWORD,pass);
        } else {
            mEditor.putString(KEY_USER_NAME,"");
            mEditor.putString(KEY_PASSWORD,"");
        }
        return this;
    }

    public Boolean getIsSaveLogin(){
        return mSharedPreferences.getBoolean(SAVE_LOGIN, false);
    }

    public String getUserName(){
        return mSharedPreferences.getString(KEY_USER_NAME, "");
    }

    public String getPassword(){
        return mSharedPreferences.getString(KEY_PASSWORD, "");
    }

    public Long getUserId() {
        return mSharedPreferences.getLong(KEY_USER_ID, -1);
    }
}

