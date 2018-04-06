package com.product.nguyencongduc.bookticketapplication.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.product.nguyencongduc.bookticketapplication.R;
import com.product.nguyencongduc.bookticketapplication.intereface.onFragmentCallback;
import com.product.nguyencongduc.uiticketlibrary.ButtonDefault;

import butterknife.BindView;
import butterknife.OnClick;

public class MenuFragment extends BaseFragment {

    public static final int SIGN_OUT_ACTION = 0;

    @BindView(R.id.btn_sign_out)
    protected ButtonDefault mBtnSignOut;

    @OnClick(R.id.btn_sign_out)
    public void onBtnSignOutListen() {
        mCallback.onMenuCallback(SIGN_OUT_ACTION);
    }

    public MenuFragment() {
    }

    public void setCallback(onFragmentCallback cb) {
        this.mCallback = cb;
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_menu;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
