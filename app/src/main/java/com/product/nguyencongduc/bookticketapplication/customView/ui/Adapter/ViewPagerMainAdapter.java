package com.product.nguyencongduc.bookticketapplication.customView.ui.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.product.nguyencongduc.bookticketapplication.DataBase.AgencyModel;
import com.product.nguyencongduc.bookticketapplication.fragments.AgencyDetailFragment;
import com.product.nguyencongduc.bookticketapplication.fragments.BaseFragment;
import com.product.nguyencongduc.bookticketapplication.fragments.FeedFragment;
import com.product.nguyencongduc.bookticketapplication.intereface.onFragmentCallback;

import java.util.List;
import java.util.Vector;

/**
 * Created by user on 4/1/2018.
 */

public class ViewPagerMainAdapter  extends FragmentStatePagerAdapter {

    public static final int POSITION_FEED_FRAGMENT = 0;
    public static final int POSITION_AGENCY_DETAIL_FRAGMENT = 1;

    private List<Fragment> mArrFg;
    private onFragmentCallback callback;

    public ViewPagerMainAdapter(FragmentManager fm, Vector<Fragment> arr, onFragmentCallback cb) {
        super(fm);
        this.mArrFg = arr;
        this.callback = cb;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fg = mArrFg.get(position);
        ((BaseFragment)fg).setCallBack(this.callback);
        return fg;
    }

    @Override
    public int getCount() {
        return mArrFg.size();
    }

//    int id, String name, String email, String desc, String phone
    public void setDataAgencyDetail(int id) {
        AgencyModel agencyModel = new AgencyModel.Builder(id,
                                                    "Title " + id,
                                                    "email" + id + "@gmail.com",
                                                    "The history of Vietnam Airlines dates back to January 1956, when the Vietnam Civil Aviation Department was established by the Government, marking the birth of the civil aviation industry in Vietnam. At that time, the fleet was small with only five aircraft of IL-14, AN-2, Aero-45… which started to serve domestic flights in September 1956. The history of Vietnam Airlines dates back to January 1956, when the Vietnam Civil Aviation Department was established by the Government, marking the birth of the civil aviation industry in Vietnam. At that time, the fleet was small with only five aircraft of IL-14, AN-2, Aero-45… which started to serve domestic flights in September 1956. The history of Vietnam Airlines dates back to January 1956, when the Vietnam Civil Aviation Department was established by the Government, marking the birth of the civil aviation industry in Vietnam. At that time, the fleet was small with only five aircraft of IL-14, AN-2, Aero-45… which started to serve domestic flights in September 1956. The history of Vietnam Airlines dates back to January 1956, when the Vietnam Civil Aviation Department was established by the Government, marking the birth of the civil aviation industry in Vietnam. At that time, the fleet was small with only five aircraft of IL-14, AN-2, Aero-45… which started to serve domestic flights in September 1956. ",
                                                    "000000000"+ id,
                                                    "address" + id)
                                                    .build();

        ((AgencyDetailFragment)mArrFg.get(POSITION_AGENCY_DETAIL_FRAGMENT)).setAgencyData(agencyModel);
    }
}
