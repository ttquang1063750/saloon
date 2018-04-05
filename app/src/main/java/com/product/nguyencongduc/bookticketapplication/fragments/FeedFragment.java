package com.product.nguyencongduc.bookticketapplication.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.product.nguyencongduc.bookticketapplication.DataBase.FeedModel;
import com.product.nguyencongduc.bookticketapplication.R;
import com.product.nguyencongduc.bookticketapplication.customView.ui.Adapter.FeedAdapter;
import com.product.nguyencongduc.bookticketapplication.intereface.FeedHolderCallback;
import java.util.ArrayList;
import butterknife.BindView;

import static com.product.nguyencongduc.bookticketapplication.customView.ui.Adapter.ViewPagerMainAdapter.POSITION_AGENCY_DETAIL_FRAGMENT;
import static com.product.nguyencongduc.bookticketapplication.customView.ui.Adapter.ViewPagerMainAdapter.POSITION_FEED_FRAGMENT;

public class FeedFragment extends BaseFragment implements FeedHolderCallback {

    @BindView(R.id.rcv_feeds)
    protected RecyclerView mRcvFeeds;

    private FeedAdapter mFeedAdapter;
    private ArrayList<FeedModel> mArrFeedModel;

    @Override
    protected int getContentView() {
        return R.layout.fragment_feed;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mArrFeedModel = new ArrayList<>();
        instanceExampleData();
        instanceRecycleViewFeed();
    }

    private void instanceExampleData() {
        for (int i = 0; i < 20; i ++) {
            FeedModel feed = new FeedModel.Builder(i,"title " + i, "Description " + i).build();
            mArrFeedModel.add(feed);
        }
    }

    private void instanceRecycleViewFeed() {
        mFeedAdapter = new FeedAdapter(this.getContext(), this, mArrFeedModel);
        LinearLayoutManager lmanager = new LinearLayoutManager(this.getContext());
        mRcvFeeds.setLayoutManager(lmanager);
        mRcvFeeds.setItemAnimator(new DefaultItemAnimator());
        mRcvFeeds.setAdapter(mFeedAdapter);
    }

    @Override
    public void callback(FeedModel feedModel) {
        this.mCallback.onFgCallback(feedModel, POSITION_FEED_FRAGMENT, POSITION_AGENCY_DETAIL_FRAGMENT);
    }
}
