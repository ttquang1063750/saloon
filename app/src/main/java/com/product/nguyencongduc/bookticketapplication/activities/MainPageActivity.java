package com.product.nguyencongduc.bookticketapplication.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.product.nguyencongduc.bookticketapplication.DataBase.FeedModel;
import com.product.nguyencongduc.bookticketapplication.R;
import com.product.nguyencongduc.bookticketapplication.customView.ui.Adapter.ViewPagerMainAdapter;
import com.product.nguyencongduc.bookticketapplication.fragments.AgencyDetailFragment;
import com.product.nguyencongduc.bookticketapplication.fragments.BaseFragment;
import com.product.nguyencongduc.bookticketapplication.fragments.FeedFragment;
import com.product.nguyencongduc.bookticketapplication.fragments.HeaderFragment;
import com.product.nguyencongduc.bookticketapplication.fragments.MenuFragment;
import com.product.nguyencongduc.bookticketapplication.intereface.onFragmentCallback;
import com.product.nguyencongduc.bookticketapplication.utils.ActivityManager;
import com.product.nguyencongduc.bookticketapplication.utils.LocalStorage;
import com.product.nguyencongduc.uiticketlibrary.DrawerBuilderCustom;

import java.util.Vector;

import butterknife.BindView;

import static com.product.nguyencongduc.bookticketapplication.customView.ui.Adapter.ViewPagerMainAdapter.POSITION_AGENCY_DETAIL_FRAGMENT;
import static com.product.nguyencongduc.bookticketapplication.customView.ui.Adapter.ViewPagerMainAdapter.POSITION_FEED_FRAGMENT;
import static com.product.nguyencongduc.bookticketapplication.fragments.HeaderFragment.MENU_CALL_CLOSE_ACTION;
import static com.product.nguyencongduc.bookticketapplication.fragments.HeaderFragment.MENU_CALL_OPEN_ACTION;
import static com.product.nguyencongduc.bookticketapplication.fragments.MenuFragment.SIGN_OUT_ACTION;

/**
 * Created by nguyencongduc on 3/30/18.
 */

public class MainPageActivity extends BaseActivity implements onFragmentCallback {


    @BindView(R.id.vp_feeds)
    protected ViewPager mVpFeeds;

    protected Drawer mDrawer;
    protected HeaderFragment mFgHeader;
//    protected MenuFragment mFgMenu;
    private ActivityManager mActivityManager;
    private LocalStorage mLocalStorage;
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
        mLocalStorage = new LocalStorage(this );
        mActivityManager = new ActivityManager(this);
        setupFragment();
        setMaterialMenu();
    }

    /**
     * If your view have menu, call this.
     */
    protected void setupFragment(){
        this.mFgHeader = (HeaderFragment) this.getSupportFragmentManager().findFragmentById(R.id.fragment_header);
//        this.mFgMenu = (MenuFragment) this.getSupportFragmentManager().findFragmentById(R.id.fragment_menu);
        mFgHeader.setCallBack(this);
//        mFgMenu.setCallBack(this);
    }

    protected void setMaterialMenu() {
        //if you want to update the items at a later time it is recommended to keep it in a variable
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Feed (On build)");
        SecondaryDrawerItem item2 = new SecondaryDrawerItem().withIdentifier(2).withName("Favorite (On Build)");

        //create the drawer and remember the `Drawer` result object
        mDrawer = new DrawerBuilder()
                .withActivity(this)
                .withRootView(R.id.drawer_container)
                .withGenerateMiniDrawer(true)
                .withTranslucentStatusBar(true)
                .withDrawerGravity(GravityCompat.END)
                .withActionBarDrawerToggleAnimated(true)
                .addDrawerItems(
                        item1,
                        new DividerDrawerItem(),
                        item2,
                        new SecondaryDrawerItem().withName("SIGNOUT")
                )
                .withOnDrawerListener(new Drawer.OnDrawerListener() {
                    @Override
                    public void onDrawerOpened(View drawerView) {
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                    }

                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        Log.d("slideOffset", slideOffset + "");
                        mFgHeader.setHamberOffset(slideOffset);
                    }
                })
                .withOnDrawerNavigationListener(new Drawer.OnDrawerNavigationListener() {
                    @Override
                    public boolean onNavigationClickListener(View clickedView) {
                        return false;
                    }
                })
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D
                        if (drawerItem.getClass().getSimpleName().toString().equals("SecondaryDrawerItem")) {
                            mLocalStorage.resetToken().commit();
                            mActivityManager.getLoginActivity().keepParentActivity(false).build().start();
                        }
                        return false;
                    }
                })
                .build();

        final int marginTop = this.getResources().getDimensionPixelSize(R.dimen._150sdp);
        RecyclerView recyclerView = mDrawer.getRecyclerView();
        ViewGroup.MarginLayoutParams marginLayoutParams =
                (ViewGroup.MarginLayoutParams) recyclerView.getLayoutParams();
        marginLayoutParams.setMargins(0, marginTop, 0, 0);
        recyclerView.setLayoutParams(marginLayoutParams);

    }

    private void instanceViewPager() {
        mVector = new Vector<Fragment>();
        mVector.add(BaseFragment.instantiate(this,FeedFragment.class.getName()));
        mVector.add(BaseFragment.instantiate(this,AgencyDetailFragment.class.getName()));
        mPagerAdapter = new ViewPagerMainAdapter(this.getSupportFragmentManager(),mVector, this);
        mVpFeeds.setAdapter(mPagerAdapter);
        mVpFeeds.setEnabled(false);
    }

    @Override
    public void onFgCallback(Object object, int fromPage, int toPage) {
        if (fromPage == POSITION_FEED_FRAGMENT && toPage == POSITION_AGENCY_DETAIL_FRAGMENT) {
            mPagerAdapter.setDataAgencyDetail(((FeedModel)object).getId());
            mVpFeeds.setCurrentItem(POSITION_AGENCY_DETAIL_FRAGMENT, true);
        }
    }

    @Override
    public void onMenuCallback(int action) {
        if (action == SIGN_OUT_ACTION) {
            mLocalStorage.resetToken().commit();
            mActivityManager.getLoginActivity().keepParentActivity(false).build().start();
        }
    }

    @Override
    public void onHeaderCalback(int action) {
        if (action == MENU_CALL_OPEN_ACTION) {
            mDrawer.openDrawer();
        } else if (action == MENU_CALL_CLOSE_ACTION) {
            mDrawer.closeDrawer();
        }
    }

    @Override
    public void onBackPressed() {
        int pageId = mVpFeeds.getCurrentItem();
        int backPage = (pageId % 10) - 1 + (pageId - (pageId%10));
        if (backPage < 0) {
            super.onBackPressed();
        } else {
            mVpFeeds.setCurrentItem(backPage, true);
        }
    }
}
