package com.product.nguyencongduc.bookticketapplication.fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.product.nguyencongduc.bookticketapplication.DataBase.AgencyModel;
import com.product.nguyencongduc.bookticketapplication.DataBase.TicketModel;
import com.product.nguyencongduc.bookticketapplication.R;
import com.product.nguyencongduc.uiticketlibrary.TextViewDefault;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class AgencyDetailFragment extends BaseFragment {

    private AgencyModel agencyModel;
    private ArrayList<TicketModel> mArrTicket;

    @BindView(R.id.tv_agency_name)
    protected TextViewDefault mTvName;

    @BindView(R.id.tv_agency_description)
    protected TextViewDefault mTvDesc;

    @BindView(R.id.tv_agency_email)
    protected TextViewDefault mTvEmail;

    @BindView(R.id.tv_agency_address)
    protected TextViewDefault mTvAdress;

    @BindView(R.id.tv_agency_phone)
    protected TextViewDefault mTvPhone;

    @BindView(R.id.rlt_title)
    protected RelativeLayout mRltTitle;

    @BindView(R.id.scv_agency)
    protected ScrollView  mScvAgency;

    @BindView(R.id.tv_header_agency_name)
    protected TextViewDefault mTvHeaderName;

    @BindView(R.id.imv_header_avatar)
    protected ImageView mImvHeaderAvatar;

    public AgencyDetailFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_agency_detail;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mArrTicket = new ArrayList<>();
    }

    public void setAgencyData(AgencyModel agency) {
        agencyModel = agency;
        mTvName.setText(agencyModel.getCompanyName());
        mTvHeaderName.setText(agencyModel.getCompanyName());
        mTvEmail.setText(agencyModel.getCompanyEmail());
        mTvPhone.setText(agencyModel.getCompanyPhone());
        mTvDesc.setText(agencyModel.getCompanyDescription());
        mTvAdress.setText(agencyModel.getCompanyAddress());
        onScrollListen();
        instanceExampleData();
    }

    private void instanceExampleData() {
        Random rand = new Random();
        for (int i = 0; i < 20; i++) {
            int ran = rand.nextInt(11);
            TicketModel ticket = new TicketModel.Builder(i,
                                                        i * 10,
                                                        i * ran,
                                                        "VietNam Airfly")
                                                        .build();

            mArrTicket.add(ticket);
        }
    }

    private void onScrollListen() {
        final int headerLength = this.getResources().getDimensionPixelSize(R.dimen._150sdp);
        final int imageSizeBase = this.getResources().getDimensionPixelSize(R.dimen._27sdp);
        final int marginLeftPlus = this.getResources().getDimensionPixelOffset(R.dimen._1sdp);
        final Drawable bgRltTitle = mRltTitle.getBackground();
        final RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mScvAgency.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                // Set top margin
                int scrollY = mScvAgency.getScrollY(); // For ScrollView
                int top = Math.max((headerLength - scrollY) * 15 / 19, 0);

                // Set alpha
                float alpha = (float)(headerLength - top * 19/15)/ (float)(headerLength);
                mTvName.setAlpha(1 - alpha);
                mTvHeaderName.setAlpha(alpha);
                bgRltTitle.setAlpha((int) (255 * alpha));

                // Set margin relativelayout
                lp.setMargins(0, top, 0, 0);
                mRltTitle.setLayoutParams(lp);
                mRltTitle.setBackground(bgRltTitle);

                // Set image view params
                float imageSize = imageSizeBase * alpha;
                float marginLeft = (imageSizeBase - imageSize)/2 + marginLeftPlus;
                RelativeLayout.LayoutParams lpImv = new RelativeLayout.LayoutParams((int) imageSize, (int) imageSize);
                lpImv.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
                lpImv.setMargins((int)marginLeft, 0, 0, 0);
                mImvHeaderAvatar.setLayoutParams(lpImv);
            }
        });
        mScvAgency.smoothScrollTo(0,0);
    }
}
