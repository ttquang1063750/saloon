package com.product.nguyencongduc.bookticketapplication.utils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.product.nguyencongduc.bookticketapplication.activities.BaseActivity;
import com.product.nguyencongduc.bookticketapplication.activities.LoginActivity;
import com.product.nguyencongduc.bookticketapplication.activities.MainPageActivity;

import java.lang.ref.WeakReference;

/**
 * Created by nguyencongduc on 3/30/18.
 */

public class ActivityManager {

    private final Builder mBuilder;

    public static class BundleKeyCode {
        // CONTANS SOME VALUE
        public static final String KEY_BUNDLE_EVENT_ID = "EVENT_ID";
    }

    public static class RequestCode {
        // CONTANS SOME VALUE
    }

    public static class ResultCode {
        // CONSTANS SOME VALUE
    }

    private ActivityManager(Builder builder) {
        mBuilder = builder;
    }

    public ActivityManager(BaseActivity activity) {
        mBuilder = new Builder(activity);
    }

    private BaseActivity getCurrentActivity() {
        return mBuilder.mCurrentActivityReference.get();
    }

    public void start() {
        if (mBuilder.isClearHistory) {
            getCurrentActivity().finishAffinity();
        }
        getCurrentActivity().startActivity(mBuilder.createIntent());
        if (!mBuilder.isKeepParentActivity) {
            getCurrentActivity().finish();
        }
    }

    public void startForResult() {
        getCurrentActivity().startActivityForResult(mBuilder.createIntent(), mBuilder.mRequestCode);
    }

    public void startForResult(Fragment fragment) {
        fragment.startActivityForResult(mBuilder.createIntent(), mBuilder.mRequestCode);
    }

    public void setResults(int resultCode, Intent data) {
        getCurrentActivity().setResult(resultCode, data);
    }

    /**
     * @return Builder to login Activity
     */
    public Builder getLoginActivity() {
        return new Builder(getCurrentActivity())
                .targetActivity(LoginActivity.class);
    }

    public Builder getMainPageActivity() {
        return new Builder(getCurrentActivity())
                .targetActivity(MainPageActivity.class)
                .keepParentActivity(true);
    }

    /**
     * @return back to page you want going to.
     */
    public void backToPage(Class page){
        Intent intent = new Intent(getCurrentActivity().getApplicationContext(), page);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        getCurrentActivity().startActivity(intent);
    }

    public Bundle getPassedData() {
        return getCurrentActivity().getIntent().getExtras();
    }

    public static class Builder {
        private final WeakReference<BaseActivity> mCurrentActivityReference;
        private Bundle mData;
        private Class mTargetActivity;
        private boolean isKeepParentActivity;
        private int mRequestCode;
        private boolean isClearHistory;
        private String mAction;
        private String mPackageName;
        private String mClassName;
        private String mType;

        public Builder action(String action) {
            mAction = action;
            return this;
        }

        public Builder type(String type) {
            mType = type;
            return this;
        }

        public Builder(BaseActivity activity) {
            mCurrentActivityReference = new WeakReference<>(activity);
        }

        public Builder clearHistory(boolean isClearHistory) {
            this.isClearHistory = isClearHistory;
            return this;
        }

        public Builder data(Bundle data) {
            mData = data;
            return this;
        }


        public Builder targetActivity(Class targetActivity) {
            mTargetActivity = targetActivity;
            return this;
        }

        public ActivityManager build() {
            return new ActivityManager(this);
        }

        public Bundle getData() {
            return mData;
        }

        public Builder requestCode(int requestCode) {
            mRequestCode = requestCode;
            return this;
        }

        public Builder packageName(String packageName) {
            mPackageName = packageName;
            return this;
        }

        public Builder className(String className) {
            mClassName = className;
            return this;
        }

        public Builder keepParentActivity(boolean isKeep) {
            isKeepParentActivity = isKeep;
            return this;
        }

        private Intent createIntent() {
            Intent intent = new Intent();
            if (mClassName == null && mPackageName == null) {
                mPackageName = mCurrentActivityReference.get().getPackageName();
                mClassName = mTargetActivity.getName();
            }
            intent.setClassName(mPackageName, mClassName);
            if (mAction != null) {
                intent.setAction(mAction);
            }
            if (mType != null) {
                intent.setType(mType);
            }
            if (mData != null) {
                intent.putExtras(mData);
            }
            return intent;
        }

        public void start() {
            this.build().start();
        }

        public void startForResult() {
            this.build().startForResult();
        }
    }

}
