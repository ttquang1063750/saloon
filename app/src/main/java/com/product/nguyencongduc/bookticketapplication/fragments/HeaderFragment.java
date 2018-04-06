package com.product.nguyencongduc.bookticketapplication.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.balysv.materialmenu.MaterialMenuView;
import com.product.nguyencongduc.bookticketapplication.R;
import com.product.nguyencongduc.bookticketapplication.intereface.onFragmentCallback;

import butterknife.BindView;
import butterknife.OnClick;

/**
 */
public class HeaderFragment extends BaseFragment {

    public static final int MENU_CALL_CLOSE_ACTION = 0;
    public static final int MENU_CALL_OPEN_ACTION = 1;

    @BindView(R.id.btn_hamberger)
    protected MaterialMenuView mBtnHamberger;

    @OnClick(R.id.btn_hamberger)
    public void onBtnHambergerListen() {
        if (mBtnHamberger.getIconState().equals(MaterialMenuDrawable.IconState.BURGER)) {
//            mBtnHamberger.animateIconState(MaterialMenuDrawable.IconState.ARROW);
            mCallback.onHeaderCalback(MENU_CALL_OPEN_ACTION);
        } else if (mBtnHamberger.getIconState().equals(MaterialMenuDrawable.IconState.ARROW)) {
//            mBtnHamberger.animateIconState(MaterialMenuDrawable.IconState.BURGER);
            mCallback.onHeaderCalback(MENU_CALL_CLOSE_ACTION);
        }
    }

    public void setHamberger(boolean isOpen) {
        if (isOpen) {
            mBtnHamberger.animateIconState(MaterialMenuDrawable.IconState.ARROW);
        } else {
            mBtnHamberger.animateIconState(MaterialMenuDrawable.IconState.BURGER);
        }
    }

    public void setHamberOffset(float offset) {
        mBtnHamberger.setTransformationOffset(MaterialMenuDrawable.AnimationState.BURGER_ARROW, offset);
    }

    public HeaderFragment() {

    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_header;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState);
    }


}
