package com.product.nguyencongduc.uiticketlibrary;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;

/**
 * Created by nguyencongduc on 4/6/18.
 */

public class DrawerBuilderCustom extends DrawerBuilder {

    public DrawerBuilderCustom setMarginTop(int marginTop) {

        return this;
    }

    public DrawerBuilderCustom withActivity(@NonNull Activity activity, int viewContent) {
        super.withActivity(activity);
        return this;
    }

}
