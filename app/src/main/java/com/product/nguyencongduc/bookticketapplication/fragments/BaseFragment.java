package com.product.nguyencongduc.bookticketapplication.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.product.nguyencongduc.bookticketapplication.intereface.onFragmentCallback;

import butterknife.ButterKnife;

/**
 * Created by user on 4/1/2018.
 */

public abstract class BaseFragment extends Fragment {

    protected onFragmentCallback mCallback;

    protected abstract int getContentView();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getContentView(), container, false);
        ButterKnife.bind(this, view);
        Log.d("fragment name ", this.getActivity().toString());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void setCallBack(onFragmentCallback cb) {
        this.mCallback = cb;
    }



}