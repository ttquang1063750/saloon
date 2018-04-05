package com.product.nguyencongduc.bookticketapplication.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.product.nguyencongduc.bookticketapplication.DataBase.FeedModel;
import com.product.nguyencongduc.bookticketapplication.R;
import com.product.nguyencongduc.bookticketapplication.customView.ui.Adapter.ViewPagerMainAdapter;
import com.product.nguyencongduc.bookticketapplication.fragments.AgencyDetailFragment;
import com.product.nguyencongduc.bookticketapplication.fragments.BaseFragment;
import com.product.nguyencongduc.bookticketapplication.fragments.FeedFragment;
import com.product.nguyencongduc.bookticketapplication.intereface.onFragmentCallback;

import java.util.Vector;

import butterknife.BindView;

import static com.product.nguyencongduc.bookticketapplication.customView.ui.Adapter.ViewPagerMainAdapter.POSITION_AGENCY_DETAIL_FRAGMENT;
import static com.product.nguyencongduc.bookticketapplication.customView.ui.Adapter.ViewPagerMainAdapter.POSITION_FEED_FRAGMENT;

/**
 * Created by nguyencongduc on 3/30/18.
 */

public class MainPageActivity extends BaseActivity implements onFragmentCallback {


    @BindView(R.id.vp_feeds)
    protected ViewPager mVpFeeds;

    protected Vector<Fragment> mVector;
    protected ViewPagerMainAdapter mPagerAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_main_page;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instanceViewPager();
    }

    private void instanceViewPager() {
        mVector = new Vector<Fragment>();
        mVector.add(BaseFragment.instantiate(this,FeedFragment.class.getName()));
        mVector.add(BaseFragment.instantiate(this,AgencyDetailFragment.class.getName()));
        mPagerAdapter = new ViewPagerMainAdapter(this.getSupportFragmentManager(),mVector, this);
        mVpFeeds.setAdapter(mPagerAdapter);
    }

    @Override
    public void onFgCallback(Object object, int fromPage, int toPage) {
        if (fromPage == POSITION_FEED_FRAGMENT && toPage == POSITION_AGENCY_DETAIL_FRAGMENT) {
            mPagerAdapter.setDataAgencyDetail(((FeedModel)object).getId());
            mVpFeeds.setCurrentItem(POSITION_AGENCY_DETAIL_FRAGMENT, true);
        }
    }
}
