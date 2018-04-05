package com.product.nguyencongduc.bookticketapplication.customView.ui.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.product.nguyencongduc.bookticketapplication.DataBase.FeedModel;
import com.product.nguyencongduc.bookticketapplication.R;
import com.product.nguyencongduc.bookticketapplication.customView.ui.Adapter.viewholders.FeedHolder;
import com.product.nguyencongduc.bookticketapplication.intereface.FeedHolderCallback;

import java.util.ArrayList;

/**
 * Created by nguyencongduc on 3/30/18.
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedHolder> {

    private FeedHolderCallback cb;
    private Context ctx;
    private ArrayList<FeedModel> mArrFeeds;

    public FeedAdapter(Context ctx, FeedHolderCallback cb, ArrayList<FeedModel> arr) {
        this.ctx = ctx;
        this.cb = cb;
        this.mArrFeeds = arr;
    }


    public void setData(ArrayList<FeedModel> arr) {
        this.mArrFeeds = arr;
    }

    @NonNull
    @Override
    public FeedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.row_recycle_view_feeds,parent,false);
        return new FeedHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cb.callback(mArrFeeds.get(position));
            }
        });
        holder.holderSetData(mArrFeeds.get(position));
    }

    @Override
    public int getItemCount() {
        return mArrFeeds.size();
    }
}
