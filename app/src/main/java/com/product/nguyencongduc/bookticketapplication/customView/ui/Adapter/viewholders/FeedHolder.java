package com.product.nguyencongduc.bookticketapplication.customView.ui.Adapter.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.product.nguyencongduc.bookticketapplication.DataBase.FeedModel;
import com.product.nguyencongduc.bookticketapplication.R;
import com.product.nguyencongduc.uiticketlibrary.TextViewDefault;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by nguyencongduc on 3/30/18.
 */

public class FeedHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_title)
    protected TextViewDefault mTvTitle;

    @BindView(R.id.tv_description)
    protected TextViewDefault mTvDescription;

    @BindView(R.id.imv_content)
    protected ImageView mImvContent;


    public void holderSetData(FeedModel feedModel) {
        mTvDescription.setText(feedModel.getDescription());
        mTvTitle.setText(feedModel.getTitle());
    }

    public FeedHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

}



